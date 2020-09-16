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
		this.albums = albumList;
		this.subFolders = subFoldersList;
		
		if (this.hasParent()) {
			parent.addChild(this);
		}
	}


	/*
	 * Adds a Album to this folder
	 */
	public void addAlbum(Album newAlbum) {
		albums.add(newAlbum);
	}
	/*
	 * Adds a folder to be this folders subfolder
	 */
	public void addChild(FolderABS child) {
		subFolders.add(child);
	}
	/*
	 * Changes the name of this folder
	 */
	public void changeName(String newName) {
		name = newName;
	}
	/*
	 * Deletes a album from this folder
	 */
	public void deleteAlbum(int index) {
		this.albums.remove(index);
	}
	public void deleteSubfolder(int index) {
		this.subFolders.remove(index);
	}
	public void deleteSubfolder(FolderABS object) {
		this.subFolders.remove(object);
	}
	public boolean hasChildren() {
		return !this.subFolders.isEmpty();
	}
	public boolean hasParent() {
		return this.parent != null;
	}

	
	public List<FolderABS> getChildren(){
		return this.subFolders;
	};
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
	public List<FolderABS> getSiblings(){
		if (this.hasParent() == true) {
			List<FolderABS> children = this.parent.getChildren();
			children.remove(this);
			return children;
		} else {
			return null;
		}
	};


	public Album getAlbum(int index) {
		return this.albums.get(index);
	}
	public FolderABS getParent() {
		return this.parent;
	}
	public String getName() {
		return name;
	}		
	
	public abstract boolean changeParent(FolderABS newParent);
	public abstract boolean delete();
	public abstract FolderABS getRoot();

	public abstract List<Album> getAlbums();
}
