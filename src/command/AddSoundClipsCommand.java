package command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import backend.Folder;
import backend.FolderABS;
import backend.SoundClip;

public class AddSoundClipsCommand implements Command {
	
	private Folder selectedFolder;
	private List<SoundClip> songList;
	private List<Folder> folders;
	private Map<Folder, List<SoundClip>> foldersNotified;
	
	public AddSoundClipsCommand(Folder selectedFolder, List<SoundClip> songList) {
		this.selectedFolder = selectedFolder;
		this.songList = songList;
		this.folders = new ArrayList<Folder>();
		folders.add(selectedFolder);
		Folder parent = (Folder) selectedFolder.getParent();
		while(parent.getParent().getClass() != backend.RootFolder.class) {
			folders.add(parent);
			parent = (Folder) parent.getParent();
		}
		foldersNotified = new HashMap<Folder, List<SoundClip>>();
	}
	
	public Map<Folder, List<SoundClip>> getAllChanges() {
		return foldersNotified;
	}

	@Override
	public void execute() {
		for(Folder f: folders) {
			List<SoundClip> addedSongs = new ArrayList<SoundClip>();
			for(SoundClip sc: songList) {
				if(!f.getSongs().contains(sc)) {
					f.addSong(sc);
					addedSongs.add(sc);
				}
			}
			foldersNotified.put(f, addedSongs);
		}
	}

	@Override
	public void undo() {
		for(Folder key: foldersNotified.keySet()) {
			for(SoundClip sc: foldersNotified.get(key)) {
				key.deleteSong(sc);
			}
		}
	}

	@Override
	public void redo() {
		execute();

	}

	@Override
	public Folder getSelectedFolder() {
		return selectedFolder;
	}

}
