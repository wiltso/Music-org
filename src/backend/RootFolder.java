package backend;

import java.util.ArrayList;
import java.util.List;

import backend.absClasses.FolderABS;

public class RootFolder extends FolderABS{
	public RootFolder() {
		super("All Sound Clips", null, new ArrayList<Album>(), new ArrayList<FolderABS>());
	}

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

	@Override
	public boolean changeParent(FolderABS newParent) {
		return false;
	}

	@Override
	public boolean delete() {
		return false;
	}

	@Override
	public FolderABS getRoot() {
		return this;
	}
}
