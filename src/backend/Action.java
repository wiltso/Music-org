package backend;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Action {
	private Folder object;
	private Set<Folder> objects;
	private Object parameter;
	private String functionName;
	private String undoFunctionName;
	
	public Action (Folder folderObject, Set<Folder> folderObjects, Object parameter, String function, String undoFunction) {
		object = folderObject;
		objects = folderObjects;
		this.parameter = parameter;
		functionName = function;
		undoFunctionName = undoFunction;
	}
	
	public Iterator<Folder> getObjects() {
		return objects.iterator(); 
	}
	
	public Iterator<Folder> getAllObjects() {
		Set<Folder> objectsCopy = new TreeSet<Folder>(objects);
		objectsCopy.add(object);
		return objectsCopy.iterator(); 
	}
	
	public String getFunction() {
		return functionName;
	}
	public String getUndoFunction() {
		return undoFunctionName;
	}
	
	public void undo() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Iterator<Folder> itr = getAllObjects();
		Folder objectToRun = itr.next();
	    while(itr.hasNext()) {
	        Method setNameMethod = objectToRun.getClass().getMethod(getUndoFunction(), parameter.getClass());
	        if (parameter != null) {
	        	setNameMethod.invoke(objectToRun, parameter);
	        } else {
	        	setNameMethod.invoke(objectToRun);
	        }
	    	objectToRun = itr.next();
	    }
	}
	
	public void execute() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method setNameMethod = object.getClass().getMethod(getUndoFunction(), parameter.getClass());
        if (parameter != null) {
        	setNameMethod.invoke(object, parameter);
        } else {
        	setNameMethod.invoke(object);
        }
	}

	public Object getParameterType() {
		return parameter.getClass();
	}
	
	public Object getParameter() {
		return parameter;
	}
}
