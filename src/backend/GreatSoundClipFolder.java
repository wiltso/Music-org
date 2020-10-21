package backend;

import java.util.ArrayList;
import java.util.List;

public class GreatSoundClipFolder extends SearchBasedFolder {
	
	private String name;
	private FolderABS parent;
	private List<SoundClip> songList;

	public GreatSoundClipFolder(String folderName, FolderABS parent) {
		super(folderName, parent);
		this.name = folderName;
		this.parent = parent;
		this.songList = new ArrayList<SoundClip>();
	}
	
	@Override
	void updateSongs(SoundClip sc) {
		int rating = sc.getRating();
		if(rating < 4) {
			if(this.getSongs().contains(sc)) {
				this.deleteSong(sc);
			}
		} else {
			if(!this.getSongs().contains(sc)) {
				this.addSong(sc);
			}
		}
	}

}
