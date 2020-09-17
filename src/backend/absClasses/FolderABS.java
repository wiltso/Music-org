package backend.absClasses;

import java.util.ArrayList;
import java.util.List;

import backend.interfaces.HierarchyIF;
import backend.Album;
import backend.interfaces.FolderIF;

public abstract class FolderABS implements FolderIF<Album>, HierarchyIF<FolderABS>  {

	private String name;
	protected FolderABS parent;
	protected List<Album> albums;
	protected List<FolderABS> subFolders;
	
	public FolderABS(String folderName, FolderABS parent, List<Album> albumList, List<FolderABS> subFoldersList) {
		assert albumList != null;
		assert folderName != null;
		assert subFoldersList != null;
		this.parent = parent;
		this.name = folderName;
		this.albums = new ArrayList<>(albumList);
		this.subFolders = new ArrayList<>(subFoldersList);
		
		if (this.hasParent()) {
			parent.addChild(this);
		}
	}


	/*
	 * Adds a Album to this folder
	 * Needs to be a Album can't be null
	 */
	public void addAlbum(Album newAlbum) {
		assert newAlbum != null;
		albums.add(newAlbum);
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
	 * Deletes a album from this folder
	 * Index most exist albums list
	 */
	public void deleteAlbum(int index) {
		assert 0 <= index && index < albums.size();
		albums.remove(index);
	}
	/*
	 * Deletes album by the album instance
	 */
	public void deleteAlbum(Album song) {
		albums.remove(song);
	}
	/*
	 * Deletes a subfolder from this subFolder
	 * Requires index to be inside of the array
	 */
	public void deleteSubfolder(int index) {
		assert (int) 0 <= index && index < subFolders.size();
		subFolders.remove(index);
	}
	/*
	 * Deletes a subfolder from this subFolder with a folder object
	 */
	public void deleteSubfolder(FolderABS object) {
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
	 * Checks if folder has a parent
	 * 
	 * @return True of false if this folder has a parent
	 */
	public boolean hasParent() {
		return parent != null;
	}

	/*
	 * Gets this folders subfolders
	 * 
	 * @return List of this folder children
	 */
	public List<FolderABS> getChildren(){
		return subFolders;
	};
	/*
	 * Gets all subfolders that are under this folder in the tree structure
	 * 
	 * @return List of all folders belows this folder
	 */
	public List<FolderABS> getAllChildren(){
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
	 * Gets all folders that are on the same level as this folder is in the folder tree
	 * 
	 * @return List of all folder siblings
	 */
	public List<FolderABS> getSiblings(){
		if (this.hasParent() == true) {
			List<FolderABS> children = this.parent.getChildren();
			children.remove(this);
			return children;
		} else {
			return null;
		}
	};


	/*
	 * Get album from this folder by the index
	 * The index needs to be larger the zero and smaller the size of album
	 * 
	 * @return Album object from the index
	 */
	public Album getAlbum(int index) {
		assert (int) 0 <= index && index < albums.size();
		return albums.get(index);
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


	public abstract boolean changeParent(FolderABS newParent);
	public abstract boolean delete();
	public abstract FolderABS getRoot();
	public abstract List<Album> getAlbums();
}
