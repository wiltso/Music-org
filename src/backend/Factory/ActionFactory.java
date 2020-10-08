package backend.Factory;

import java.util.HashSet;
import java.util.Set;

import backend.Action;
import backend.History;
import backend.Settings;

public class ActionFactory<E> {
	/*
	 * Creates a action then runs addAction
	 */
	public void createAction(E folder, Set<E> objects, Object parameter, String functionName, String undoFunctionName) {
		Action<E> newAction = new Action<E>(folder, objects, parameter, functionName, undoFunctionName);
		addAction(newAction);
	}

	/*
	 * Creates a action then runs addAction
	 */
	public void createAction(E folder,Object parameter, String functionName, String undoFunctionName) {
		Action<E> newAction = new Action<E>(folder, new HashSet<E>(), parameter, functionName, undoFunctionName);
		addAction(newAction);
	}
	
	/*
	 * Checks with the the settings if this action should be added to the history or
	 * if this action was created due to a undo or redo action
	 */
	private void addAction(Action<E> newAction) {
		if (Settings.getInstance().canCreateActions) {
			@SuppressWarnings("unchecked")
			History<E> history = (History<E>) History.getInstance();
			history.addHistory(newAction);
		}
	}
}
