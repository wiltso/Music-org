package front;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

import backend.Folder;
import backend.History;
import backend.SoundClip;

public class MusicOrganizerController {

	private MusicOrganizerWindow view;
	private SoundClipBlockingQueue queue;
	private Folder root;
	
	public MusicOrganizerController() {
		// Create the root album for all sound clips
		root = new Folder("All Sound Clips", null, this);
		
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
			root.addSong(sc);
		}
		
		return clips;
	}
	
	/**
	 * Returns the root album
	 */
	public Folder getRootAlbum(){
		return root;
	}
	
	/**
	 * Adds an album to the Music Organizer
	 */
	public void addNewAlbum(){

		if(view.getSelectedAlbum() != null) {
			Folder parent = view.getSelectedAlbum();
			String name = view.promptForAlbumName();
			if(name != null) {
				Folder folder = new Folder(name, parent, this);
				parent.addChild(folder);
			}
		}
	}
	
	/**
	 * Removes an album from the Music Organizer
	 */
	public void deleteAlbum(){ 

		if(view.getSelectedAlbum() != null && view.getSelectedAlbum().hasParent()) {
			Folder toBeDeleted = view.getSelectedAlbum();
			toBeDeleted.getParent().deleteSubfolder(toBeDeleted);
		}
	}
	
	/**
	 * Adds sound clips to an album
	 */
	public void addSoundClips(){ 

		List<SoundClip> selectedSoundClips = view.getSelectedSoundClips();
		Folder folder = view.getSelectedAlbum();
		for(SoundClip sc: selectedSoundClips) {
			folder.addSong(sc);
		}
		view.onClipsUpdated();
	}
	
	/**
	 * Removes sound clips from an album
	 */
	public void removeSoundClips(){

		List<SoundClip> selectedSoundClips = view.getSelectedSoundClips();
		Folder folder = view.getSelectedAlbum();
		for(SoundClip sc: selectedSoundClips) {
			folder.deleteSong(sc);
		}
		view.onClipsUpdated();
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
	
	/*
	 * Performs the undo action when users click undo button
	 */
	public void undo() {
		History<Folder> history = History.getInstance();
		try {
			history.undo();
			view.onClipsUpdated();
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Performs the redo action when users click redo button
	 */
	public void redo() {
		History<Folder> history = History.getInstance();
		try {
			history.redo();
			view.onClipsUpdated();
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Delegate a method to the view
	 */
	public void onAlbumAdded(Folder instance) {
		view.onAlbumAdded(instance);
	}
	public void onAlbumRemoved(Folder instance) {
		view.onAlbumRemoved(instance);
	}
}
