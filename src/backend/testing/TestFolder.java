package backend.testing;

import static org.junit.Assert.assertTrue;
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

class TestFolder {

	private RootFolder rootFolder;
	private Folder child1;
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
		child1 = new Folder("Child3", rootFolder, testAlbums, new ArrayList<FolderABS>());

	}

	@Test
	void testGetAlbum() {
		boolean errorThrown = false;
		Album testAlbum = null;
		try {
			testAlbum = child1.getAlbum(-1);
		} catch (AssertionError e) {
			errorThrown = true;
		}
		assertTrue(errorThrown);
		assertNull(testAlbum);
		try {
			errorThrown = false;
			testAlbum = child1.getAlbum(4);
		} catch (AssertionError e) {
			errorThrown = true;
		}
		assertTrue(errorThrown);
		assertNull(testAlbum);
		try {
			errorThrown = false;
			testAlbum = child1.getAlbum(3);
		} catch (AssertionError e) {
			errorThrown = true;
		}
		assertFalse(errorThrown);
		assertEquals(testAlbum, testAlbums.get(3));
	}
	
	@Test
	void testDeleteAlbum() {
		boolean errorThrown = false;
		try {
			child1.deleteAlbum(-1);
		} catch (AssertionError e) {
			errorThrown = true;
		}
		assertTrue(errorThrown);
		try {
			errorThrown = false;
			child1.deleteAlbum(4);
		} catch (AssertionError e) {
			errorThrown = true;
		}
		assertTrue(errorThrown);
		assertEquals(child1.getAlbums().size(), 4);
		try {
			errorThrown = false;
			child1.deleteAlbum(3);
		} catch (AssertionError e) {
			errorThrown = true;
		}
		assertFalse(errorThrown);
		assertEquals(child1.getAlbums().size(), 3);
		try {
			errorThrown = false;
			child1.deleteAlbum(testAlbums.get(3));
		} catch (AssertionError e) {
			errorThrown = true;
		}
		assertFalse(errorThrown);
		assertEquals(child1.getAlbums().size(), 3);
		
		try {
			errorThrown = false;
			child1.deleteAlbum(testAlbums.get(0));
		} catch (AssertionError e) {
			errorThrown = true;
		}
		assertFalse(errorThrown);
		assertEquals(child1.getAlbums().size(), 2);
	}

}
