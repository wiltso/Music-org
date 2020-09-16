package backend;

import java.util.List;

import backend.interfaces.AlbumIF;

public class Album implements AlbumIF<SoundClip>{
	
	private List<SoundClip> songs;
	private String name;
	private String content;
	

	public Album(String albumName, String contentText, List<SoundClip> songList) {
		songs = songList;
		name = albumName;
		content = contentText;
	}
	
	public String getName() {
		return name;
	}
	public String getContent() {
		return content;
	}
	public void setName(String newName) {
		name = newName;
	}
	public void setContent(String newContent) {
		content = newContent;
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
