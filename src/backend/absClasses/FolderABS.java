package backend.absClasses;

import java.util.ArrayList;
import java.util.List;

import backend.SoundClip;
import backend.interfaces.HierarchyIF;

public abstract class FolderABS implements HierarchyIF<FolderABS> {

	private String name;
	protected FolderABS parent;
	protected List<FolderABS> subFolders;
	protected List<SoundClip> songList;
	
	public FolderABS(String folderName, FolderABS parent, List<FolderABS> subFoldersList, List<SoundClip> songList) {
		assert folderName != null;
		assert subFoldersList != null;
		assert songList != null;
		this.parent = parent;
		this.name = folderName;
		this.subFolders = new ArrayList<>(subFoldersList);
		this.songList = new ArrayList<>(songList);
		
		if(this.hasParent()) {
			parent.addChild(this);
		}
	}
	/*
	 * Adds a folder to be this folders subfolder
	 * Needs to be a folder can't be null
	 */
	public void addChild(FolderABS child) {
		assert child != null;
		subFolders.add(child);
	}
	/*
	 * Changes the name of this folder
	 * The name can't be null
	 */
	public void changeName(String newName) {
		assert newName != null;
		name = newName;
	}
	/*
	 * Deletes a subfolder from this folder
	 * Requires index to be inside of the array
	 */
	public void deleteSubfolder(int index) {
		assert (int) 0 <= index && index < subFolders.size();
		subFolders.remove(index);
	}
	/*
	 * Deletes a subfolder from this folder with a folder object
	 */
	public void deleteSubfolder(FolderABS object) {
		assert object != null;
		subFolders.remove(object);
	}
	/*
	 * Checks if this folder has a subfolder
	 * 
	 * @return Checks if there are any subfolder
	 */
	public boolean hasChildren() {
		return !subFolders.isEmpty();
	}
	/*
	 * Checks if this folder has a parent
	 * 
	 * @return True or False if this folder has a parent
	 */
	public boolean hasParent() {
		return parent != null;
	}
	/*
	 * Gets this folders subfolders
	 * 
	 * @return List of this folder's children
	 */
	public List<FolderABS> getChildren() {
		return subFolders;
	}
	/*
	 * Gets all subfolders that are under this folder in the tree structure
	 * 
	 * @return List of all folders below this folder
	 */
	public List<FolderABS> getAllChildren() {
		List<FolderABS> allChildrenFolders = new ArrayList<FolderABS>();
		for(FolderABS childFolder: this.subFolders) {
			allChildrenFolders.add(childFolder);
		}
		
		for(FolderABS subFolder: this.subFolders) {
			for(FolderABS childFolder: subFolder.getAllChildren()) {
				allChildrenFolders.add(childFolder);
			}
		}
		
		return allChildrenFolders;
	}
	/*
	 * Gets all folders that are on the same level as this folder in the folder tree
	 * 
	 * @return List of all folder siblings
	 */
	public List<FolderABS> getSiblings() {
		if(this.hasParent() == true) {
			List<FolderABS> children = this.parent.getChildren();
			children.remove(this);
			return children;
		} else {
			return null;
		}
	}
	/*
	 * Gets this folders parent
	 * 
	 * @return type FolderABS that is this folders parent
	 */
	public FolderABS getParent() {
		return parent;
	}
	/*
	 * Gets the folder name
	 * 
	 * @return name of this folder
	 */
	public String getName() {
		return name;
	}
	/*
	 * Gets all SoundClips in this folder
	 * 
	 * @return List of all SoundClips in this folder
	 */
	public List<SoundClip> getSongs() {
		return songList;
	}
	/*
	 * Adds a SoundClip to this folder and to all of it's parent Folders
	 * Needs to be a SoundClip can't be null
	 */
	public void addSong(SoundClip song) {
		assert song != null;
		songList.add(song);
		FolderABS parentFolder = this.getParent();
		while(parentFolder != null) {
			parentFolder.songList.add(song);
			parentFolder = parentFolder.getParent();
		}
	}
	/*
	 * Get SoundClip from this folder by the index
	 * Requires index to be inside of the array
	 * 
	 * @return SoundClip object by the index
	 */
	public SoundClip getSong(int index) {
		assert 0 <= index && index < songList.size();
		return songList.get(index);
	}
	/*
	 * Deletes a SoundClip from this folder and from all of it's child Folders by index
	 * Requires index to be inside the array
	 */
	public void deleteSong(int index) {
		assert 0 <= index && index < songList.size();
		SoundClip temp = songList.get(index);
		songList.remove(index);
		if(this.hasChildren()) {
			for(FolderABS child: subFolders) {
				child.songList.remove(temp);
			}
		}
	}
	/*
	 * Deletes a SoundClip from this folder and from all of it's child Folders by a SoundClip object
	 */
	public void deleteSong(SoundClip object) {
		assert object != null;
		songList.remove(object);
		if(this.hasChildren()) {
			for(FolderABS child: subFolders) {
				child.songList.remove(object);
			}
		}
	}
	
	public abstract boolean delete();
	public abstract FolderABS getRoot();

}
