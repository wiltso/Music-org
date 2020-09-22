package backend;

import java.util.ArrayList;
import java.util.List;

import backend.absClasses.FolderABS;

public class RootFolder extends FolderABS {
	
	public RootFolder() {
		super("All Sound Clips", null, new ArrayList<FolderABS>(), new ArrayList<SoundClip>());
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
