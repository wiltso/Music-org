package command;

import backend.Folder;

public class AddFolderCommand implements Command {
	
	private Folder selectedFolder;
	private Folder child;
	
	public AddFolderCommand(Folder selectedFolder, Folder child) {
		this.selectedFolder = selectedFolder;
		this.child = child;
	}

	@Override
	public void execute() {
		selectedFolder.addChild(child);
	}

	@Override
	public void undo() {
		selectedFolder.deleteSubfolder(child);

	}

	@Override
	public void redo() {
		execute();

	}

	@Override
	public Folder getSelectedFolder() {
		return child;
	}
	
}
