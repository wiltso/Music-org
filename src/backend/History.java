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
		placeInHistory = 0;
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
		historyLog.add(action);
		placeInHistory =+ 1;
	}
	
	public void undo() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		assert placeInHistory < historyLog.size();
		assert placeInHistory > 0;
		
		Action action = historyLog.get(placeInHistory);
		action.undo();
		placeInHistory =- 1;
	}
	
	public void redo() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		assert placeInHistory < historyLog.size() - 1;

		Action action = historyLog.get(placeInHistory);
		action.execute();
		placeInHistory =+ 1;
	}
	
}
