package backend;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class History<E> {
	
	private volatile static History<Folder> instance;
	private List<Action<E>> historyLog;
	private int placeInHistory;

	// Change this int to change the amount of actions stored in the history
	private final int maxUndoActions = 20;

	private History() {
		historyLog = new ArrayList<Action<E>>();
		placeInHistory = -1;
	}
	
	/*
	 * Insures that there can only be one history
	 */
	public static History<Folder> getInstance() {
		if (instance == null) {
			synchronized (History.class) {
				if (instance == null) {
					instance = new History<Folder>();
				}
			}
		}
		return instance;
	}

	/*
	 * Adds a action to the history.
	 * If you have done a undo action and the do some other action
	 * like add a sub album the redo for that action goes away.
	 * Also insures that there is a limit on the amount of actions in the history
	 */
	public void addHistory(Action<E> action) {
		while (placeInHistory + 1 != historyLog.size()) {
			historyLog.remove(historyLog.size() - 1);
		}
		while (historyLog.size() >= maxUndoActions) {
			historyLog.remove(0);
		}
		historyLog.add(action);
		placeInHistory = historyLog.size() - 1;
	}
	
	/*
	 * Is it possible to undo a action
	 */
	public boolean canUndo() {
		return (boolean) (0 <= placeInHistory && placeInHistory < historyLog.size());
	}
	
	/*
	 * Is it possible to redo a action
	 * For this to be true the user needs to do a undo action first and nothing else
	 */
	public boolean canRedo() {
		return (boolean) (placeInHistory < historyLog.size() - 1);
	}
	
	/*
	 * Goes backwards one step in the log
	 * The execution is delegated down to the action
	 */
	public void undo() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		assert placeInHistory < historyLog.size();
		assert placeInHistory > 0;

		Action<E> action = historyLog.get(placeInHistory);
		action.undo();
		placeInHistory = placeInHistory - 1;
	}
	
	/*
	 * Goes forward one step in the log
	 * The execution is delegated down to the action
	 */
	public void redo() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		assert placeInHistory < historyLog.size() - 2;

		placeInHistory = placeInHistory + 1;
		Action<E> action = historyLog.get(placeInHistory);
		action.execute();
	}
	
}
