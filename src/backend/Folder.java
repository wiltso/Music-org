package backend;

import java.util.List;

import backend.absClasses.FolderABS;

public class Folder extends FolderABS{

	public Folder(String folderName, FolderABS parent, List<Album> albumList, List<FolderABS> subFoldersList) {
		super(folderName, parent, albumList, subFoldersList);
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
		this.parent.deleteSubfolder(this);
		this.parent = newParent;
		this.parent.addChild(this);
		return true;
	}
	@Override
	public FolderABS getRoot() {
		return parent.getRoot();
	}

}
