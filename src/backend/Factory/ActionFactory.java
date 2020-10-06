package backend.Factory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import backend.Action;
import backend.Folder;
import backend.History;

public class ActionFactory {
	private volatile static ActionFactory instance;
	private boolean canCreateActions;
	
	private ActionFactory() {
		canCreateActions = true;
	}
	
	public static ActionFactory getInstance() {
		if (instance == null) {
			synchronized (History.class) {
				if (instance == null) {
					instance = new ActionFactory();
				}
			}
		}
		return instance;
	}
	
	public void setCanCreateAction(boolean state) {
		canCreateActions = state;
	}
	
	public void createAction(Folder folder, Set<Folder> objects, Object parameter, String functionName, String undoFunctionName) {
		Action newAction = new Action(folder, objects, parameter, functionName, undoFunctionName);
		addAction(newAction);
	}
	
	public void createAction(Folder folder,Object parameter, String functionName, String undoFunctionName) {
		Action newAction = new Action(folder, new HashSet<Folder>(), parameter, functionName, undoFunctionName);
		addAction(newAction);
	}
	
	private void addAction(Action newAction) {
		if (canCreateActions) {
			System.out.println("Action: " + newAction + " Was added");
			System.out.println();
			History history = History.getInstance();
			history.addHistory(newAction);
		}
	}
}
