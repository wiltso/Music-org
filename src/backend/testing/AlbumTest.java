package backend.testing;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import backend.Album;
import backend.SoundClip;

class AlbumTest {
	private Album album;
	private static List<File> testFiles;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		testFiles = new ArrayList<File>();
		testFiles.add(new File("src/backend/testing/testingFiles/test1"));
		testFiles.add(new File("src/backend/testing/testingFiles/test2"));
		testFiles.add(new File("src/backend/testing/testingFiles/test3"));
		testFiles.add(new File("src/backend/testing/testingFiles/test4"));
	}
	
	@BeforeEach
	void setUp() throws Exception {
		album = new Album("Album1", "Album1 content", new ArrayList<SoundClip>());
	}

	@Test
	void testCreation() {
		boolean errorThrown = false;
		try {
			album = new Album("Album1", null, new ArrayList<SoundClip>());
		} catch (AssertionError e) {
			errorThrown = true;
		}
		assertFalse(errorThrown);
		try {
			errorThrown = false;
			album = new Album(null, null, new ArrayList<SoundClip>());
		} catch (AssertionError e) {
			errorThrown = true;
		}
		assertTrue(errorThrown);
		try {
			errorThrown = false;
			album = new Album("Album1", null, null);
		} catch (AssertionError e) {
			errorThrown = true;
		}
		assertTrue(errorThrown);
	}
	
	@Test
	void testNameAndContent() {
		boolean errorThrown = false;
		try {
			album.setName("test");
		} catch (AssertionError e) {
			errorThrown = true;
		}
		assertFalse(errorThrown);
		assertTrue(album.getName().equals("test"));
		assertFalse(album.getName().equals("Test"));
		try {
			errorThrown = false;
			album.setName(null);
		} catch (AssertionError e) {
			errorThrown = true;
		}
		assertTrue(errorThrown);
		try {
			errorThrown = false;
			album.setContent("Test2");
		} catch (AssertionError e) {
			errorThrown = true;
		}
		assertFalse(errorThrown);
		assertTrue(album.getContent().equals("Test2"));
		assertFalse(album.getContent().equals("test2"));
		try {
			errorThrown = false;
			album.setContent(null);
		} catch (AssertionError e) {
			errorThrown = true;
		}
		assertFalse(errorThrown);
		assertFalse("Test2".equals(album.getContent()));
		assert album.getContent() == null;
	}
	
	@Test
	void testSongAddition() {
		SoundClip sound1 = new SoundClip(testFiles.get(0));
		SoundClip sound2 = new SoundClip(testFiles.get(1));
		SoundClip sound3 = new SoundClip(testFiles.get(2));
		SoundClip sound4 = new SoundClip(testFiles.get(3));
		album.addSong(sound1);
		assertEquals((int) album.getSongs().size(), (int) 1);
		album.addSong(sound2);
		assertEquals((int) album.getSongs().size(), (int) 2);
		album.addSong(sound3);
		assertEquals((int) album.getSongs().size(), (int) 3);
		album.addSong(sound4);
		assertEquals((int) album.getSongs().size(), (int) 4);
	}

	@Test
	void testSongOrder() {
		SoundClip sound1 = new SoundClip(testFiles.get(0));
		SoundClip sound2 = new SoundClip(testFiles.get(1));
		SoundClip sound3 = new SoundClip(testFiles.get(2));
		SoundClip sound4 = new SoundClip(testFiles.get(3));
		album.addSong(sound1);
		assertEquals(album.getSong(0), sound1);
		album.addSong(sound2);
		assertEquals(album.getSong(1), sound2);
		album.addSong(sound3);
		assertEquals(album.getSong(2), sound3);
		album.addSong(sound4);
		assertEquals(album.getSong(3), sound4);
		assertEquals(album.getSong(2), sound3);
		assertNotEquals(album.getSong(3), sound3);

	}
	
	@Test
	void testAfterDeleteOrder() {
		SoundClip sound1 = new SoundClip(testFiles.get(0));
		SoundClip sound2 = new SoundClip(testFiles.get(1));
		SoundClip sound3 = new SoundClip(testFiles.get(2));
		SoundClip sound4 = new SoundClip(testFiles.get(3));
		album.addSong(sound1);
		assertEquals(album.getSong(0), sound1);
		album.addSong(sound2);
		assertEquals(album.getSong(1), sound2);
		album.addSong(sound3);
		assertEquals(album.getSong(2), sound3);
		album.addSong(sound4);
		assertEquals(album.getSong(3), sound4);
		assertEquals(album.getSong(2), sound3);
		assertNotEquals(album.getSong(3), sound3);
		
		album.deleteSong(2);
		assertEquals((int) album.getSongs().size(), (int) 3);
		assertEquals(album.getSong(0), sound1);
		assertEquals(album.getSong(1), sound2);
		assertEquals(album.getSong(2), sound4);

	}
	
	@Test
	void testDuplicateSong() {
		SoundClip sound1 = new SoundClip(testFiles.get(0));
		SoundClip sound2 = new SoundClip(testFiles.get(0));
		album.addSong(sound1);
		boolean errorThrown = false;
		try {
			album.addSong(sound2);
		} catch (AssertionError e) {
			errorThrown = true;
		}
		assertTrue(errorThrown);
	}
	
	@Test
	void testSongGetting() {
		SoundClip sound1 = new SoundClip(testFiles.get(0));
		album.addSong(sound1);
		boolean errorThrown = false;
		try {
			album.getSong(-1);
		} catch (AssertionError e) {
			errorThrown = true;
		}
		assertTrue(errorThrown);
		try {
			errorThrown = false;
			album.getSong(1);
		} catch (AssertionError e) {
			errorThrown = true;
		}
		assertTrue(errorThrown);
		try {
			errorThrown = false;
			album.getSong(0);
		} catch (AssertionError e) {
			errorThrown = true;
		}
		assertFalse(errorThrown);
	}
	
	@Test
	void testSongDelete() {
		SoundClip sound1 = new SoundClip(testFiles.get(0));
		album.addSong(sound1);
		boolean errorThrown = false;
		try {
			album.deleteSong(-1);
		} catch (AssertionError e) {
			errorThrown = true;
		}
		assertTrue(errorThrown);
		try {
			errorThrown = false;
			album.deleteSong(1);
		} catch (AssertionError e) {
			errorThrown = true;
		}
		assertTrue(errorThrown);
		try {
			errorThrown = false;
			album.deleteSong(0);
		} catch (AssertionError e) {
			errorThrown = true;
		}
		assertFalse(errorThrown);
	}
	

}
