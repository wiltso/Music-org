package command;

import java.util.ArrayList;
import java.util.List;

public class CommandHistory {

	private volatile static CommandHistory instance;
	private List<Command> history = new ArrayList<Command>();
	private List<Command> future = new ArrayList<Command>();
	
	private CommandHistory() {}
	
	public static CommandHistory getInstance() {
		if(instance == null) {
			synchronized (CommandHistory.class) {
				if(instance == null) {
					instance = new CommandHistory();
				}
			}
		}
		return instance;
	}
	
	public void addExecutedCommand(Command c) {
		history.add(c);
	}
	
	public Command getLastCommand() {
		Command c = history.get(history.size()-1);
		return c;
	}
	
	public Command getLastUndo() {
		Command c = future.get(future.size()-1);
		return c;
	}
	
	public void undoCommand() {
		future.add(getLastCommand());
		history.remove(getLastCommand());
	}
	
	public void redoCommand() {
		history.add(getLastUndo());
		future.remove(getLastUndo());
	}
	
	public boolean undoEnabled() {
		return !history.isEmpty();
	}
	
	public boolean redoEnabled() {
		return !future.isEmpty();
	}
	
	public void clearFuture() {
		future.clear();
	}
	
}
