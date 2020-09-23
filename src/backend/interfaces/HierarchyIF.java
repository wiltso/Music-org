package backend.interfaces;

import java.util.List;

public interface HierarchyIF<E> {

	public E getParent();
	public List<E> getChildren();
	public List<E> getAllChildren();
	public List<E> getSiblings();
	public void addChild(E newChild);
	public boolean hasChildren();
	public boolean hasParent();
	
}
