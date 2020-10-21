import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import backend.Folder;
import backend.RootFolder;
import backend.SearchBasedFolder;
import backend.SoundClip;
import command.AddFolderCommand;
import command.AddSoundClipsCommand;
import command.Command;
import command.CommandManager;
import command.DeleteFolderCommand;
import command.DeleteSoundClipsCommand;

public class MusicOrganizerController {

	private MusicOrganizerWindow view;
	private SoundClipBlockingQueue queue;
	private RootFolder root;
	private Folder allSoundClips;
	private SearchBasedFolder greatSoundClips;
	private SearchBasedFolder flaggedSoundClips;
	private CommandManager commandManager;
	
	public MusicOrganizerController() {
		
		// Create the RootFolder in JTree
		root = new RootFolder("Root Folder", null);
		
		// Get the root folder for regular folders
		allSoundClips = root.getAllSoundClipsFolder();
		
		// Get the SearchBasedFolder for Great Sound Clips
		greatSoundClips = root.getGreatSoundClipsFolder();
		
		// Get the SearchBasedFolder for Flagged Sound Clips
		flaggedSoundClips = root.getFlaggedSoundClipsFolder();
		
		// Create the CommandManager
		commandManager = new CommandManager();
		
		// Create the View in Model-View-Controller
		view = new MusicOrganizerWindow(this);
		
		// Create the blocking queue
		queue = new SoundClipBlockingQueue();
		
		// Create a separate thread for the sound clip player and start it
		(new Thread(new SoundClipPlayer(queue))).start();
	}

	/**
	 * Load the sound clips found in all subfolders of a path on disk. If path is not
	 * an actual folder on disk, has no effect.
	 */
	public Set<SoundClip> loadSoundClips(String path) {
		Set<SoundClip> clips = SoundClipLoader.loadSoundClips(path);
		for(SoundClip sc: clips) {
			root.getAllSoundClipsFolder().addSong(sc);
		}
		
		return clips;
	}
	
	/**
	 * Returns the root album
	 */
	public RootFolder getRootAlbum(){
		return root;
	}
	
	/**
	 * Adds an album to the Music Organizer
	 */
	public void addNewAlbum(){ 
		
		if(view.getSelectedAlbum() != null && view.getSelectedAlbum().getClass() == backend.Folder.class) {
			Folder parent = (Folder) view.getSelectedAlbum();
			String name = view.promptForAlbumName();
			if(name != null) {
				Folder child = new Folder(name, parent);
				commandManager.executeCommand(new AddFolderCommand(parent, child));
				view.onAlbumAdded(child);
				view.setEnabledUndoButton(commandManager.undoEnabled());
				view.setEnabledRedoButton(commandManager.redoEnabled());
			}
		}
	}
	
	/**
	 * Removes an album from the Music Organizer
	 */
	public void deleteAlbum(){ 
		
		if (view.getSelectedAlbum() != null && view.getSelectedAlbum().getClass() == backend.Folder.class) {
			Folder selectedFolder = (Folder) view.getSelectedAlbum();
			if(selectedFolder.hasParent() && selectedFolder.getParent().getClass() == backend.Folder.class) {
				commandManager.executeCommand(new DeleteFolderCommand(selectedFolder));
				view.onAlbumRemoved(selectedFolder);
				view.setEnabledUndoButton(commandManager.undoEnabled());
				view.setEnabledRedoButton(commandManager.redoEnabled());
			}
		}
	}
	
	/**
	 * Adds sound clips to an album
	 */
	public void addSoundClips() { 
		
		if (view.getSelectedAlbum() != null && view.getSelectedAlbum().getClass() == backend.Folder.class) {
			List<SoundClip> selectedSoundClips = view.getSelectedSoundClips();
			if(!selectedSoundClips.isEmpty()) {
				Folder folder = (Folder) view.getSelectedAlbum();
				commandManager.executeCommand(new AddSoundClipsCommand(folder, selectedSoundClips));
				view.onClipsUpdated();
				view.setEnabledUndoButton(commandManager.undoEnabled());
				view.setEnabledRedoButton(commandManager.redoEnabled());
			}
		}
	}
	
	/**
	 * Removes sound clips from an album
	 * Requires:
	 * Selected folder not null
	 * Selected folder is a regular Folder (class Folder)
	 * Selected folder is not the root folder for regular folders
	 */
	public void removeSoundClips() {
		
		if (view.getSelectedAlbum() != null && view.getSelectedAlbum().getClass() == backend.Folder.class && view.getSelectedAlbum().getParent().getClass() != backend.RootFolder.class) {
			List<SoundClip> selectedSoundClips = view.getSelectedSoundClips();
			if(!selectedSoundClips.isEmpty()) {
				Folder folder = (Folder) view.getSelectedAlbum();
				DeleteSoundClipsCommand dscc = new DeleteSoundClipsCommand(folder, selectedSoundClips);
				commandManager.executeCommand(dscc);
				view.onClipsUpdated();
				view.setEnabledUndoButton(commandManager.undoEnabled());
				view.setEnabledRedoButton(commandManager.redoEnabled());
			}
		}
	}
	
	/**
	 * Puts the selected sound clips on the queue and lets
	 * the sound clip player thread play them. Essentially, when
	 * this method is called, the selected sound clips in the 
	 * SoundClipTable are played.
	 */
	public void playSoundClips(){
		List<SoundClip> l = view.getSelectedSoundClips();
		for(int i=0;i<l.size();i++)
			queue.enqueue(l.get(i));
	}
	
	/**
	 * Calls undo() on the last Command if undo is available
	 */
	public void undoLastAction() {
		if(commandManager.undoEnabled()) {
			Command c = commandManager.getLastCommand();
			if (c.getClass() == command.AddFolderCommand.class) {
				commandManager.undo();
				view.onAlbumRemoved(c.getSelectedFolder());
			} else if (c.getClass() == command.DeleteFolderCommand.class) {
				DeleteFolderCommand dfc = (DeleteFolderCommand) commandManager.getLastCommand();
				commandManager.undo();
				view.onAlbumAdded(c.getSelectedFolder());
				if (!dfc.getSubfolders().isEmpty()) {
					for(Folder f: dfc.getSubfolders()) {
						view.onAlbumAdded(f);
					}
				}
			} else {
				commandManager.undo();
				view.onClipsUpdated();
			}
		}
		view.setEnabledUndoButton(commandManager.undoEnabled());
		view.setEnabledRedoButton(commandManager.redoEnabled());
	}
	
	/**
	 * Calls redo() on the last Command if redo is available
	 */
	public void redoLastAction() {
		if(commandManager.redoEnabled()) {
			Command c = commandManager.getLastUndo();
			if (c.getClass() == command.AddFolderCommand.class) {
				commandManager.redo();
				view.onAlbumAdded(c.getSelectedFolder());
			} else if (c.getClass() == command.DeleteFolderCommand.class) {
				commandManager.redo();
				view.onAlbumRemoved(c.getSelectedFolder());
			} else {
				commandManager.redo();
				view.onClipsUpdated();
			}
		}
		view.setEnabledRedoButton(commandManager.redoEnabled());
		view.setEnabledUndoButton(commandManager.undoEnabled());
	}
	
	/**
	 * Flags selected SoundClips
	 * Adds flagged SoundClips to Flagged Sound Clips album
	 */
	public void flagSoundClips() {
		List<SoundClip> selectedSoundClips = view.getSelectedSoundClips();
		if (!selectedSoundClips.isEmpty()) {
			for(SoundClip sc: selectedSoundClips) {
				sc.flagSoundClip();
				flaggedSoundClips.update(sc);
			}
			view.onClipsUpdated();
		}
	}
	
	/**
	 * Unflags selected SoundClips
	 * Removes unflagged SoundClips from Flagged Sound Clips album
	 */
	public void unflagSoundClips() {
		List<SoundClip> selectedSoundClips = view.getSelectedSoundClips();
		if (!selectedSoundClips.isEmpty()) {
			for(SoundClip sc: selectedSoundClips) {
				sc.unflagSoundClip();
				flaggedSoundClips.update(sc);
			}
			view.onClipsUpdated();
		}
	}
	
	/**
	 * Rates selected SoundClips
	 * Updates the SoundClips in Great Sound Clips album
	 * by chosen rating
	 */
	public void rateSoundClips() {
		List<SoundClip> selectedSoundClips = view.getSelectedSoundClips();
		if (!selectedSoundClips.isEmpty()) {
			String[] options = {"0", "1", "2", "3", "4", "5"};
			ImageIcon icon = new ImageIcon("icons/favourites_32.png");
			String ratingstring = (String) JOptionPane.showInputDialog(null, "Choose rating for selected SoundClips", "Rate SoundClips", JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);
			int rating = Integer.parseInt(ratingstring);
			for(SoundClip sc: selectedSoundClips) {
				sc.soundClipRated();
				sc.setRating(rating);
				greatSoundClips.update(sc);
			}
			
			view.onClipsUpdated();
		}
	}
}
