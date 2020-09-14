package backend;

import java.util.List;

import backend.absClasses.FolderABS;

public class Folder extends FolderABS{

	public Folder(FolderABS parent) {
		super(parent);
	}

	@Override
	public List<Album> getAlbums() {
		return this.albums;
	}

	@Override
	public boolean delete() {
		FolderABS parent = this.getParent();
		parent.deleteSubfolder(this);
		return true;
	}

	@Override
	public boolean changeParent(FolderABS newParent) {
		this.parent = newParent;
		return false;
	}

}
