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
	
	public String getName() {
		return name;
	}
	public void changeName(String newName) {
		name = newName;
	}
		
	public FolderABS getParent() {
		return this.parent;
	};
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
	public boolean hasChildren() {
		return !this.subFolders.isEmpty();
	};
	public boolean hasParent() {
		return this.parent != null;
	};
	public void addAlbum(Album newAlbum) {
		this.albums.add(newAlbum);
	}
	public Album getAlbum(int index) {
		return this.albums.get(index);
	}
	public void addChild(FolderABS child) {
		this.subFolders.add(child);
	}
	public void deleteAlbum(int index) {
		this.albums.remove(index);
	}
	
	public abstract boolean changeParent(FolderABS newParent);
	public abstract List<Album> getAlbums();
	public abstract boolean delete();
	public abstract FolderABS getRoot();

	
	public void deleteSubfolder(int index) {
		this.subFolders.remove(index);
	}
	public void deleteSubfolder(FolderABS object) {
		this.subFolders.remove(object);
	}
	
}
