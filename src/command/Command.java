package command;

import backend.Folder;

public interface Command {
	
	public void execute();
	public void undo();
	public void redo();
	public Folder getSelectedFolder();
	
}
