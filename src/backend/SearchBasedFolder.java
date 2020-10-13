package backend;

import java.util.List;

public class SearchBasedFolder extends FolderABS {

	private String name;
	private FolderABS parent;
	private List<SoundClip> songList;
	
	public SearchBasedFolder(String folderName, FolderABS parent) {
		super(folderName, parent);
	}
}
