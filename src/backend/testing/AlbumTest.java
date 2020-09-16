package backend.testing;

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

}
