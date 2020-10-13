package backend;

import java.util.ArrayList;
import java.util.List;

public abstract class FolderABS {
	
	
	private String name;
	private FolderABS parent;
	private List<FolderABS> subFolders;
	private List<SoundClip> songList;
	
	public FolderABS(String folderName, FolderABS parent) {
		this.name = folderName;
		this.parent = parent;
		this.subFolders = new ArrayList<FolderABS>();
		this.songList = new ArrayList<SoundClip>();
	}
	
	/*
	 * Gets the parent
	 * @return this FolderABS parent
	 */
	public FolderABS getParent() {
		return parent;
	}
	
	/*
	 * Gets the folder name
	 * @return name of this folder
	 */
	public String getName() {
		return name;
	}
	/*
	 * Gets all SoundClips in this folder
	 * @return List of all SoundClips in this folder
	 */
	
	public List<SoundClip> getSongs() {
		return songList;
	}
	/*
	 * Adds a SoundClip to this folder
	 */
	
	public void addSong(SoundClip song) {
		assert song != null;
		songList.add(song);
	}
	/*
	 * Deletes a SoundClip from this folder
	 */
	public void deleteSong(SoundClip song) {
		assert song != null;
		songList.remove(song);
	}
	
	public String toString() {
		return name;
	}
	
}
