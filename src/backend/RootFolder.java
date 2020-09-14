package backend;

import java.util.ArrayList;
import java.util.List;

import backend.absClasses.FolderABS;

public class RootFolder extends FolderABS{
	public RootFolder() {
		super(null);
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
		this.parent = newParent;
		return true;
	}

	@Override
	public boolean delete() {
		return false;
	}
}
