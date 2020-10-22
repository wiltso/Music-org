package backend;

import java.util.ArrayList;
import java.util.List;

public class Folder extends FolderABS {

	private String name;
	private FolderABS parent;
	private List<Folder> subFolders;
	private List<SoundClip> songList;
	
	public Folder(String folderName, FolderABS parent) {
		super(folderName, parent);
		this.name = folderName;
		this.parent = parent;
		this.subFolders = new ArrayList<Folder>();
		this.songList = new ArrayList<SoundClip>();
	}
	
	/*
	 * Adds a folder to be this folders subfolder
	 * Needs to be a folder can't be null
	 */
	public void addChild(Folder child) {
		assert child != null;
		subFolders.add(child);
	}
	
	/*
	 * Changes the name of this folder
	 * The name can't be null
	 */
	public void changeName(String newName) {
		assert newName != null;
		name = newName;
	}
	
	/*
	 * Deletes a subfolder from this folder
	 * Requires index to be inside of the array
	 */
	public void deleteSubfolder(int index) {
		assert (int) 0 <= index && index < subFolders.size();
		subFolders.remove(index);
	}
	
	/*
	 * Deletes a subfolder from this folder with a folder object
	 */
	public void deleteSubfolder(Folder object) {
		assert object != null;
		subFolders.remove(object);
	}
	
	/*
	 * Checks if this folder has a subfolder
	 * @return Checks if there are any subfolder
	 */
	public boolean hasChildren() {
		return !subFolders.isEmpty();
	}
	
	/*
	 * Checks if this folder has a parent
	 * @return True or False if this folder has a parent
	 */
	public boolean hasParent() {
		return parent != null;
	}
	
	/*
	 * Gets this folders subfolders
	 * @return List of this folder's children
	 */
	public List<Folder> getChildren() {
		return subFolders;
	}
	
	/*
	 * Gets all subfolders of this folder
	 */
	public List<Folder> getAllChildren() {
		List<Folder> allChildrenFolders = new ArrayList<Folder>();
		for(Folder childFolder: this.subFolders) {
			allChildrenFolders.add(childFolder);
		}
		
		for(Folder subFolder: this.subFolders) {
			for(Folder childFolder: subFolder.getAllChildren()) {
				allChildrenFolders.add(childFolder);
			}
		}
		return allChildrenFolders;
	}
}
