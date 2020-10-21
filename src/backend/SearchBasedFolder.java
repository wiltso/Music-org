package backend;

import java.util.List;

public abstract class SearchBasedFolder extends FolderABS {

	private String name;
	private FolderABS parent;
	private List<SoundClip> songList;
	
	public SearchBasedFolder(String folderName, FolderABS parent) {
		super(folderName, parent);
	}
	
	public final void update(SoundClip sc) {
		updateSongs(sc);
	}
	
	abstract void updateSongs(SoundClip sc);
	
	boolean isFlaggedSong(SoundClip sc) {
		return false;
	}
	
	boolean isGreatSong(SoundClip sc) {
		return false;
	}
	
}
