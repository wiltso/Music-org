package backend;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class History {
	
	private volatile static History instance;
	private List<Action> historyLog;
	private int placeInHistory;

	private History() {
		historyLog = new ArrayList<Action>();
		placeInHistory = -1;
	}
	
	public static History getInstance() {
		if (instance == null) {
			synchronized (History.class) {
				if (instance == null) {
					instance = new History();
				}
			}
		}
		return instance;
	}

	public void addHistory(Action action) {
		while (placeInHistory + 1 != historyLog.size()) {
			historyLog.remove(historyLog.size() - 1);
		}
		historyLog.add(action);
		placeInHistory = placeInHistory + 1;
	}
	
	public boolean canUndo() {
		return (boolean) (0 < placeInHistory && placeInHistory < historyLog.size());
	}
	
	public boolean canRedo() {
		return (boolean) (placeInHistory < historyLog.size() - 1);
	}
	
	public void undo() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		assert placeInHistory < historyLog.size();
		assert placeInHistory > 0;
		Action action = historyLog.get(placeInHistory);
		System.out.println("Action being undon place: " + placeInHistory);
		System.out.println("Action size of actions: " + historyLog.size());
		System.out.println("Action being undon: " + action);
		action.undo();
		placeInHistory = placeInHistory - 1;
	}
	
	public void redo() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		assert placeInHistory < historyLog.size() - 2;
		placeInHistory = placeInHistory + 1;

		Action action = historyLog.get(placeInHistory);
		action.execute();
	}
	
}
