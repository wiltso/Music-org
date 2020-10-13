package command;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import backend.Folder;
import backend.FolderABS;

public class DeleteFolderCommand implements Command {
	
	private Folder selectedFolder;
	private Folder parent;
	private List<Folder> subFolders;
	
	public DeleteFolderCommand(Folder selectedFolder) {
		this.selectedFolder = selectedFolder;
		this.parent = (Folder) selectedFolder.getParent();
		this.subFolders = selectedFolder.getAllChildren();
	}
	
	public List<Folder> getSubfolders() {
		return subFolders;
	}
	

	@Override
	public void execute() {
		parent.deleteSubfolder(selectedFolder);

	}

	@Override
	public void undo() {
		parent.addChild(selectedFolder);

	}

	@Override
	public void redo() {
		execute();

	}

	@Override
	public Folder getSelectedFolder() {
		return selectedFolder;
	}

}
