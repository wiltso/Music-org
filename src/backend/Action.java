package backend;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Action<E> {
	private E object;
	private Set<E> objects;
	private Object parameter;
	private String functionName;
	private String undoFunctionName;
	
	public Action (E folderObject, Set<E> folderObjects, Object parameter, String function, String undoFunction) {
		object = folderObject;
		objects = folderObjects;
		this.parameter = parameter;
		functionName = function;
		undoFunctionName = undoFunction;
	}
	
	/*
	 * Gets the iterator for the objects that the action or the undo action need to be done on
	 */
	public Iterator<E> getObjects() {
		return objects.iterator(); 
	}
	/*
	 * Gets a iterator for all the folder in the action
	 * This means it concatenates the objects and object and creates a iterator on the combined set
	 */
	public Iterator<E> getAllObjects() {
		Set<E> objectsCopy = new HashSet<E>(objects);
		objectsCopy.add(object);
		return objectsCopy.iterator(); 
	}
	
	/*
	 * Toggles if the action factory can create actions and add them to the history
	 */
	private void toggleActionCreator(boolean state) {
		Settings settings = Settings.getInstance();
		settings.canCreateActions = state;
	}
	
	/*
	 * Dose the undo function on all the objects that it need to do it on
	 */
	public void undo() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		toggleActionCreator(false);
		Iterator<E> itr = getAllObjects();
		E objectToRun;
	    while(itr.hasNext()) {
	    	objectToRun = itr.next();
	    	Method setNameMethod = objectToRun.getClass().getMethod(undoFunctionName, parameter.getClass());
	        if (parameter != null) {
	        	setNameMethod.invoke(objectToRun, parameter);
	        } else {
	        	setNameMethod.invoke(objectToRun);
	        }
	    }
	    toggleActionCreator(true);
	}
	
	/*
	 * Executes the action only on the object because the folder object dose the reset when a action is executed
	 */
	public void execute() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		toggleActionCreator(false);
		Method setNameMethod = object.getClass().getMethod(functionName, parameter.getClass());
        if (parameter != null) {
        	setNameMethod.invoke(object, parameter);
        } else {
        	setNameMethod.invoke(object);
        }
        toggleActionCreator(true);
	}
}
