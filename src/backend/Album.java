package backend;

import java.util.List;

import backend.interfaces.AlbumIF;

public class Album implements AlbumIF<SoundClip>{
	
	private List<SoundClip> songs;
	
	public List<SoundClip> getSongs() {
		return this.songs;
	}
	
	public void addSong(SoundClip song) {
		this.songs.add(song);
	}
	
	public SoundClip getSong(int index) {
		return this.songs.get(index);
	}
	
	public void deleteSong(int index) {
		this.songs.remove(index);
	}
}
