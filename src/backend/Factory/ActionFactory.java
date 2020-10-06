package backend.Factory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import backend.Action;
import backend.Folder;
import backend.History;
import backend.Settings;

public class ActionFactory {
	/*
	 * Creates a action then runs addAction
	 */
	public void createAction(Folder folder, Set<Folder> objects, Object parameter, String functionName, String undoFunctionName) {
		Action newAction = new Action(folder, objects, parameter, functionName, undoFunctionName);
		addAction(newAction);
	}

	/*
	 * Creates a action then runs addAction
	 */
	public void createAction(Folder folder,Object parameter, String functionName, String undoFunctionName) {
		Action newAction = new Action(folder, new HashSet<Folder>(), parameter, functionName, undoFunctionName);
		addAction(newAction);
	}
	
	/*
	 * Checks with the the settings if this action should be added to the history or
	 * if this action was created due to a undo or redo action
	 */
	private void addAction(Action newAction) {
		if (Settings.getInstance().canCreateActions) {
			History history = History.getInstance();
			history.addHistory(newAction);
		}
	}
}
