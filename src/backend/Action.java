package backend;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
		System.out.println("New action");
		System.out.println("Folder: " + folderObject);
		System.out.println("Folder objects: " + folderObjects);
		System.out.println("Parameter: " + parameter);
		System.out.println("Function: " + function);
		System.out.println("Undo Function: " + undoFunction);
		System.out.println("This: " + this);
		System.out.println("New action end\n");
	}

	public Folder getFolder(){
		return object;
	}
	
	public Iterator<Folder> getObjects() {
		return objects.iterator(); 
	}
	
	public Iterator<Folder> getAllObjects() {
		Set<Folder> objectsCopy = new HashSet<Folder>();
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
		Folder objectToRun;
	    while(itr.hasNext()) {
	    	objectToRun = itr.next();
	    	Method setNameMethod = objectToRun.getClass().getMethod(getUndoFunction(), parameter.getClass());
	        if (parameter != null) {
	        	setNameMethod.invoke(objectToRun, parameter);
	        } else {
	        	setNameMethod.invoke(objectToRun);
	        }
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
