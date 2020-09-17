package backend;

import java.util.ArrayList;
import java.util.List;

import backend.absClasses.FolderABS;

public class RootFolder extends FolderABS{
	public RootFolder() {
		super("All Sound Clips", null, new ArrayList<Album>(), new ArrayList<FolderABS>());
	}

	/*
	 * Gets all the Albums that have been created
	 * 
	 * @return List with all the albums
	 */
	@Override
	public List<Album> getAlbums() {
		List<FolderABS> allSubFolders = getAllChildren();
		List<Album> allAlbums = new ArrayList<Album>();
		
		for(FolderABS subFolder: allSubFolders) {
			for(Album subAlbum: subFolder.getAlbums()) {
				allAlbums.add(subAlbum);
			}
		}
		return allAlbums;
	}
	
	/*
	 * Changes the parent. As this is the root folder this action is not allowed
	 * 
	 * @return false
	 */
	@Override
	public boolean changeParent(FolderABS newParent) {
		return false;
	}
	/*
	 * Deletes this folder. As this is the root folder we can't do it
	 * 
	 * @return false
	 */
	@Override
	public boolean delete() {
		return false;
	}

	/*
	 * Gets this instance for sub folders to use
	 * 
	 * @return this
	 */
	@Override
	public FolderABS getRoot() {
		return this;
	}
}
