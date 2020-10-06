package backend;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import backend.Factory.ActionFactory;
import backend.interfaces.HierarchyIF;
import front.MusicOrganizerWindow;

public class Folder implements HierarchyIF<Folder> {

	private String name;
	protected Folder parent;
	protected List<Folder> subFolders;
	protected List<SoundClip> songList;
	private MusicOrganizerWindow view;
	private final ActionFactory actionFactory;

	public Folder(String folderName, Folder parent, MusicOrganizerWindow view) {
		assert folderName != null;
		this.parent = parent;
		this.name = folderName;
		this.view = view;
		this.subFolders = new ArrayList<Folder>();
		this.songList = new ArrayList<SoundClip>();
		actionFactory = new ActionFactory();
	}
	
	public void setView(MusicOrganizerWindow view) {
		this.view = view;
	}
	
	/*
	 * Adds a folder to be this folders subfolder
	 * Needs to be a folder can't be null
	 */
	public void addChild(Folder child) {
		assert child != null;
		subFolders.add(child);
		actionFactory.createAction(this, child, "addChild", "deleteSubfolder");
		view.onAlbumAdded(child);

	}

	/*
	 * Changes the name of this folder
	 * The name can't be null
	 */
	public void changeName(String newName) {
		assert newName != null;
		name = newName;
		// There is no way for the user to change the name
		// If this is added remember to make a action for it	
	}
	/*
	 * Deletes a subfolder from this folder
	 * Requires index to be inside of the array
	 */
	public void deleteSubfolder(int index) {
		assert (int) 0 <= index && index < subFolders.size();
		Folder subfolder = subFolders.get(index);
		subFolders.remove(index);
		actionFactory.createAction(this, subfolder, "deleteSubfolder", "addChild");
		view.onAlbumRemoved(subfolder);
	}
	/*
	 * Deletes a subfolder from this folder with a folder object
	 */
	public void deleteSubfolder(Folder object) {
		assert object != null;
		subFolders.remove(object);
		actionFactory.createAction(this, object, "deleteSubfolder", "addChild");
		view.onAlbumRemoved(object);
	}

	/*
	 * Checks if this folder has a subfolder
	 * 
	 * @return Checks if there are any subfolder
	 */
	public boolean hasChildren() {
		return !subFolders.isEmpty();
	}
	/*
	 * Checks if this folder has a parent
	 * 
	 * @return True or False if this folder has a parent
	 */
	public boolean hasParent() {
		return parent != null;
	}
	/*
	 * Gets this folders subfolders
	 * 
	 * @return List of this folder's children
	 */
	public List<Folder> getChildren() {
		return subFolders;
	}
	/*
	 * Gets all subfolders that are under this folder in the tree structure
	 * 
	 * @return List of all folders below this folder
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
	/*
	 * Gets all folders that are on the same level as this folder in the folder tree
	 * 
	 * @return List of all folder siblings
	 */
	public List<Folder> getSiblings() {
		if(this.hasParent() == true) {
			List<Folder> children = this.parent.getChildren();
			children.remove(this);
			return children;
		} else {
			return null;
		}
	}
	/*
	 * Gets this folders parent
	 * 
	 * @return type FolderABS that is this folders parent
	 */
	public Folder getParent() {
		return parent;
	}
	/*
	 * Gets the folder name
	 * 
	 * @return name of this folder
	 */
	public String getName() {
		return name;
	}
	/*
	 * Gets all SoundClips in this folder
	 * 
	 * @return List of all SoundClips in this folder
	 */
	public List<SoundClip> getSongs() {
		return songList;
	}
	/*
	 * Adds a SoundClip to this folder and to all of it's parent Folders
	 * Needs to be a SoundClip can't be null
	 */
	public void addSong(SoundClip song) {
		assert song != null;
		songList.add(song);
		Folder parentFolder = this.getParent();
		Set<Folder> parents = new HashSet<Folder>();
		while(parentFolder != null) {
			if(!parentFolder.songList.contains(song)) {
				parents.add(parentFolder);
				parentFolder.songList.add(song);
				parentFolder = parentFolder.getParent();
			} else {
				parentFolder = parentFolder.getParent();
			}
		}
		actionFactory.createAction(this, parents, song, "addSong", "deleteSong");
	}
	/*
	 * Get SoundClip from this folder by the index
	 * Requires index to be inside of the array
	 * 
	 * @return SoundClip object by the index
	 */
	public SoundClip getSong(int index) {
		assert 0 <= index && index < songList.size();
		return songList.get(index);
	}
	/*
	 * Deletes a SoundClip from this folder and from all of it's child Folders by index
	 * Requires index to be inside the array
	 */
	public void deleteSong(int index) {
		assert 0 <= index && index < songList.size();
		SoundClip song = songList.get(index);
		Set<Folder> subfolders = new HashSet<Folder>(getAllChildren());
		actionFactory.createAction(this, subfolders, song, "deleteSong", "addSong");
		songList.remove(index);
		if(this.hasChildren()) {
			for(Folder child: subFolders) {
				child.deleteSong(song);
			}
		}
	}
	/*
	 * Deletes a SoundClip from this folder and from all of it's child Folders by a SoundClip object
	 */
	public void deleteSong(SoundClip object) {
		assert object != null;
		Set<Folder> subfolders = new HashSet<Folder>(getAllChildren());
		actionFactory.createAction(this, subfolders, object, "deleteSong", "addSong");
		songList.remove(object);
		if(this.hasChildren()) {
			for(Folder child: subFolders) {
				child.deleteSong(object);
			}
		}
	}
	
	public String toString() {
		return name;
	}
}
