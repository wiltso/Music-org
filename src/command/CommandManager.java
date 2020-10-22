package command;

public class CommandManager {
	
	/**
	 * CommandManager handles Commands
	 */
	
	private CommandHistory history;
	
	public CommandManager() {
		this.history = CommandHistory.getInstance();
	}

	/**
	 * Calls execute() for Command c
	 * @param c
	 */
	public void executeCommand(Command c) {
		c.execute();
		history.addExecutedCommand(c);
		history.clearFuture();
	}
	
	/**
	 * Gets last Command from History
	 * Calls undo() on the last Command
	 */
	public void undo() {
		history.getLastCommand().undo();
		history.undoCommand();
	}
	
	/**
	 * Gets last undo() Command from History
	 * Calls redo() on the last undo() Command
	 */
	public void redo() {
		history.getLastUndo().redo();
		history.redoCommand();
	}
	
	/**
	 * Gets the last Command from History
	 * @return Command
	 */
	public Command getLastCommand() {
		return history.getLastCommand();
	}
	
	/**
	 * Gets the last undo() Command from History
	 * @return Command
	 */
	public Command getLastUndo() {
		return history.getLastUndo();
	}
	
	/**
	 * Checks if undo() can be performed
	 * @return boolean
	 */
	public boolean undoEnabled() {
		return history.undoEnabled();
	}
	
	/**
	 * Checks if redo() can be performed
	 * @return boolean
	 */
	public boolean redoEnabled() {
		return history.redoEnabled();
	}
	
}
