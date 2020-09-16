package backend;

import java.util.ArrayList;
import java.util.List;

import backend.interfaces.AlbumIF;

public class Album implements AlbumIF<SoundClip>{
	
	private List<SoundClip> songs;
	
	public Album() {
		songs = new ArrayList<SoundClip>();
	}
	public Album(List<SoundClip> songList) {
		songs = songList;
	}
	
	public List<SoundClip> getSongs() {
		return songs;
	}
	
	public void addSong(SoundClip song) {
		songs.add(song);
	}
	
	public SoundClip getSong(int index) {
		return songs.get(index);
	}
	
	public void deleteSong(int index) {
		songs.remove(index);
	}
}
