package backend.interfaces;

import java.util.List;

public interface FolderIF<E> {
	
	public void addAlbum(E newAlbum);
	public E getAlbum(int index);
	public void deleteAlbum(int index);
	public List<E> getAlbums();
	public String getName();
	public void changeName(String newName);

}
