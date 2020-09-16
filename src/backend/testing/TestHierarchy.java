package backend.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
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
		testAlbums.add(new Album(new ArrayList<SoundClip>()));
		testAlbums.add(new Album(new ArrayList<SoundClip>()));
		testAlbums.add(new Album(new ArrayList<SoundClip>()));
		testAlbums.add(new Album(new ArrayList<SoundClip>()));
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
		assertEquals(child2Return, true);
		assertEquals(child3Return, true);
		assertEquals(rootReturn, false);
		assertEquals(child2.getParent(), rootFolder);
		assertEquals(child3.getParent(), rootFolder);
		assertEquals(rootFolder.getParent(), null);
		
	}
	
	@Test
	void testGetSiblings() {
		child2.changeParent(rootFolder);
		child3.changeParent(rootFolder);
		System.out.print(child2.getSiblings());
	}
	


}
