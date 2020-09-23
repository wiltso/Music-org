package backend;

import java.util.ArrayList;
import java.util.List;

import backend.absClasses.FolderABS;

public class Folder extends FolderABS {
	
	public Folder(String folderName, FolderABS parent) {
		super(folderName, parent, new ArrayList<FolderABS>(), new ArrayList<SoundClip>());
	}
	
	@Override
	public boolean delete() {
		FolderABS parent = this.getParent();
		parent.deleteSubfolder(this);
		return true;
	}
	
	@Override
	public FolderABS getRoot() {
		return parent.getRoot();
	}

}
