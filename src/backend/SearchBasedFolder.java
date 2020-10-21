package backend;

import java.util.List;

import backend.Observer;

public abstract class SearchBasedFolder extends FolderABS implements Observer {

	private String name;
	private FolderABS parent;
	private List<SoundClip> songList;
	
	public SearchBasedFolder(String folderName, FolderABS parent) {
		super(folderName, parent);
	}
	
	public abstract void update(SoundClip sc);
	
	boolean isFlaggedSong(SoundClip sc) {
		return false;
	}
	
	boolean isGreatSong(SoundClip sc) {
		return false;
	}
	
}
