package backend.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import backend.Album;
import backend.Folder;
import backend.RootFolder;
import backend.SoundClip;
import backend.absClasses.FolderABS;

class TestHierarchy {

	private RootFolder rootFolder;
	private Folder child1;
	private Folder child2;
	private Folder child3;
	private static List<Album> testAlbums;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		testAlbums = new ArrayList<Album>();
		testAlbums.add(new Album("Album1", "Album1 content", new ArrayList<SoundClip>()));
		testAlbums.add(new Album("Album2", "Album2 content", new ArrayList<SoundClip>()));
		testAlbums.add(new Album("Album3", "Album3 content", new ArrayList<SoundClip>()));
		testAlbums.add(new Album("Album4", "Album4 content", new ArrayList<SoundClip>()));
	}
	
	@BeforeEach
	void setUp() throws Exception {
		rootFolder = new RootFolder();
		child1 = new Folder("Child1", rootFolder, new ArrayList<Album>(), new ArrayList<FolderABS>());
		child2 = new Folder("Child2", child1, new ArrayList<Album>(), new ArrayList<FolderABS>());
		child3 = new Folder("Child3", child2, new ArrayList<Album>(), new ArrayList<FolderABS>());

	}
	
	@Test
	void testGettingRootFolder() {
		assertEquals(child1.getRoot(), rootFolder);
		assertEquals(child2.getRoot(), rootFolder);
		assertEquals(child3.getRoot(), rootFolder);
	}

	@Test
	void testGettingParent() {
		assertEquals(child1.getParent(), rootFolder);
		assertEquals(child2.getParent(), child1);
		assertEquals(child3.getParent(), child2);
	}
	
	@Test
	void testChangeParent() {
		boolean child2Return = child2.changeParent(rootFolder);
		boolean child3Return = child3.changeParent(rootFolder);
		boolean rootReturn = rootFolder.changeParent(child3);
		assertTrue(child2Return);
		assertTrue(child3Return);
		assertFalse(rootReturn);
		assertEquals(child2.getParent(), rootFolder);
		assertEquals(child3.getParent(), rootFolder);
		assertNull(rootFolder.getParent());
		assertEquals(child2.getChildren(), new ArrayList<FolderABS>());
		assertEquals(child3.getChildren(), new ArrayList<FolderABS>());
		
	}
	
	@Test
	void testGetSiblings() {
		child2.changeParent(rootFolder);
		child3.changeParent(rootFolder);
		
		List<FolderABS> siblings = child3.getSiblings();
		
		assertFalse(siblings.contains(child3));
		assertTrue(siblings.contains(child1));
		assertTrue(siblings.contains(child2));
	}
	
	@Test
	void testChildrenAndParent() {
		assertFalse(rootFolder.hasParent());
		assertTrue(child1.hasParent());
		assertTrue(child2.hasParent());
		assertTrue(child3.hasParent());
		
		assertTrue(rootFolder.hasChildren());
		assertTrue(child1.hasChildren());
		assertTrue(child2.hasChildren());
		assertFalse(child3.hasChildren());
	}
	
	@Test
	void testGetAllChildren() {
		List<FolderABS> siblings = rootFolder.getAllChildren();
		assertFalse(siblings.contains(rootFolder));
		assertTrue(siblings.contains(child3));
		assertTrue(siblings.contains(child1));
		assertTrue(siblings.contains(child2));
	}
	
	@Test
	void testDelete() {
		List<FolderABS> siblings = rootFolder.getAllChildren();
		assertTrue(siblings.contains(child1));
		assertTrue(siblings.contains(child2));
		assertTrue(siblings.contains(child3));

		child3.delete();
		assertEquals(child2.getChildren(), new ArrayList<FolderABS>());
		child1.delete();
		List<FolderABS> subFolders = rootFolder.getAllChildren();
		assertFalse(subFolders.contains(child1));
		assertFalse(subFolders.contains(child2));
		assertFalse(subFolders.contains(child3));
	}
}
