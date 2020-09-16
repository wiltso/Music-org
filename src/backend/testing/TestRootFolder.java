package backend.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
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

class TestRootFolder {

	private RootFolder rootFolder;
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
	}
	
	@Test
	void testRootFolderName() {
		assertEquals(rootFolder.getName(), "All Sound Clips");
		Folder child2 = new Folder("Child2", rootFolder, new ArrayList<Album>(), new ArrayList<FolderABS>());
		assertEquals(child2.getName(), "Child2");
		rootFolder.changeName("Name");
		assertEquals(rootFolder.getName(), "Name");
	}
	
	@Test
	void testRootFolderWithSubFolder() {
		new Folder("Child1", rootFolder, new ArrayList<Album>(), new ArrayList<FolderABS>());
		assertEquals(rootFolder.getChildren().size(), (int) 1);
		Folder child2 = new Folder("Child2", rootFolder, new ArrayList<Album>(), new ArrayList<FolderABS>());
		assertEquals(rootFolder.getChildren().size(), (int) 2);
	}
	
	@Test
	void testRootFolderGetSubFolder() {
		Folder child1 = new Folder("Child1", rootFolder, new ArrayList<Album>(), new ArrayList<FolderABS>());
		Folder child2 = new Folder("Child2", rootFolder, new ArrayList<Album>(), new ArrayList<FolderABS>());
		assertEquals(rootFolder.getChildren().get(0), child1);
		assertEquals(rootFolder.getChildren().get(1), child2);
	}
	
	@Test
	void testRootFolderGettingAlbums() {
		Folder child1 = new Folder("Child1", rootFolder, new ArrayList<Album>(), new ArrayList<FolderABS>());
		Folder child2 = new Folder("Child2", rootFolder, new ArrayList<Album>(), new ArrayList<FolderABS>());
		assertEquals(rootFolder.getAlbums().size(), 0);
		child2.addAlbum(testAlbums.get(0));
		child2.addAlbum(testAlbums.get(1));
		child2.addAlbum(testAlbums.get(2));
		assertEquals(rootFolder.getAlbums().size(), 3);
		child1.addAlbum(testAlbums.get(3));
		child2.addAlbum(testAlbums.get(0));
		child2.addAlbum(testAlbums.get(1));
		assertEquals(rootFolder.getAlbums().size(), 6);
	}
	

}
