package command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import backend.Folder;
import backend.SoundClip;

public class DeleteSoundClipsCommand implements Command {
	
	private Folder selectedFolder;
	private List<SoundClip> songList;
	private List<Folder> folders;
	private Map<Folder, List<SoundClip>> foldersNotified;
	
	public DeleteSoundClipsCommand(Folder selectedFolder, List<SoundClip> songList) {
		this.selectedFolder = selectedFolder;
		this.songList = songList;
		this.folders = new ArrayList<Folder>();
		folders.add(selectedFolder);
		for(Folder f: selectedFolder.getAllChildren()) {
			folders.add(f);
		}
		foldersNotified = new HashMap<Folder, List<SoundClip>>();
	}
	
	public Map<Folder, List<SoundClip>> getAllChanges() {
		return foldersNotified;
	}

	@Override
	public void execute() {
		for(Folder f: folders) {
			List<SoundClip> deletedSongs = new ArrayList<SoundClip>();
			for(SoundClip sc: songList) {
				if(f.getSongs().contains(sc)) {
					deletedSongs.add(sc);
					f.deleteSong(sc);
				}
			}
			foldersNotified.put(f, deletedSongs);
		}
	}

	@Override
	public void undo() {
		for(Folder key: foldersNotified.keySet()) {
			for(SoundClip sc: foldersNotified.get(key)) {
				key.addSong(sc);
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
