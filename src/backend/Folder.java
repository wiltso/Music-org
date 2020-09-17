package backend;

import java.util.List;

import backend.absClasses.FolderABS;

public class Folder extends FolderABS{

	public Folder(String folderName, FolderABS parent, List<Album> albumList, List<FolderABS> subFoldersList) {
		super(folderName, parent, albumList, subFoldersList);
	}
	
	/*
	 * Gets all albums from this folder
	 * 
	 * @return List of folders albums
	 */
	@Override
	public List<Album> getAlbums() {
		return this.albums;
	}

	/*
	 * Deletes this folder by removing it from the parent subfolder list
	 * 
	 * @return true as the action was allowed
	 */
	@Override
	public boolean delete() {
		FolderABS parent = this.getParent();
		parent.deleteSubfolder(this);
		return true;
	}

	/*
	 * Changes the parent folder this is so folder can be moved
	 * Requires that the new parent is a folder you can't have folders as the root folder
	 * 
	 * @return true as the action was allowed
	 */
	@Override
	public boolean changeParent(FolderABS newParent) {
		assert newParent != null;
		this.parent.deleteSubfolder(this);
		this.parent = newParent;
		this.parent.addChild(this);
		return true;
	}
	
	/*
	 * Gets this folder root folders parent
	 * 
	 * @return this folders parents parent
	 */
	@Override
	public FolderABS getRoot() {
		return parent.getRoot();
	}

}
