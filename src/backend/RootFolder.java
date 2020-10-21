package backend;

import java.util.ArrayList;
import java.util.List;

public class RootFolder extends FolderABS {
	
	private String name;
	private FolderABS parent;
	private List<FolderABS> subFolders;
	private List<SoundClip> songList;

	/*
	 * RootFolder contains three folders
	 * Folder "All Sound Clips"
	 * SearchBasedFolder "Great Sound Clips"
	 * SearchBasedFolder "Flagged Sound Clips"
	 */
	public RootFolder(String folderName, FolderABS parent) {
		super(folderName, parent);
		this.subFolders = new ArrayList<FolderABS>();
		this.addChild(new Folder("All Sound Clips", this));
		this.addChild(new GreatSoundClipFolder("Great Sound Clips", this));
		this.addChild(new FlaggedSoundClipFolder("Flagged Sound Clips", this));
	}
	
	/*
	 * Adds a subfolder to this RootFolder
	 * Subfolders can't be added to RootFolder outside RootFolder class
	 */
	private void addChild(FolderABS child) {
		subFolders.add(child);
	}
	
	/*
	 * Gets the root folder for regular folders
	 * @return Folder
	 */
	public Folder getAllSoundClipsFolder() {
		return (Folder) subFolders.get(0);
	}
	
	/*
	 * Gets the Great Sound Clips folder
	 * @return SearchBasedFolder
	 */
	public SearchBasedFolder getGreatSoundClipsFolder() {
		return (SearchBasedFolder) subFolders.get(1);
	}
	
	/*
	 * Gets the Flagged Sound Clips folder
	 * @SearchBasedFolder
	 */
	public SearchBasedFolder getFlaggedSoundClipsFolder() {
		return (SearchBasedFolder) subFolders.get(2);
	}

}
