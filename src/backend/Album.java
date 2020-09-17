package backend;

import java.util.ArrayList;
import java.util.List;

import backend.interfaces.AlbumIF;

public class Album implements AlbumIF<SoundClip>{
	
	private List<SoundClip> songs;
	private String name;
	private String content;
	

	/*
	 * Requires name and song list the content text is not required
	 */
	public Album(String albumName, String contentText, List<SoundClip> songList) {
		assert albumName != null;
		assert songList != null;
		songs = new ArrayList<>(songList);
		name = albumName;
		content = contentText;
	}
	
	/*
	 * Gets albums name
	 * 
	 * @return String with albums name
	 */
	public String getName() {
		return name;
	}
	/*
	 * Gets albums content text
	 * 
	 * @return Content text of this album
	 */
	public String getContent() {
		return content;
	}
	
	/*
	 * Allows you to change the name of this folder
	 * 
	 * Requires a string to be the new name, null can't be the name
	 */
	public void setName(String newName) {
		assert newName != null;
		name = newName;
	}
	/*
	 * Allows you to change the content text of this album
	 */
	public void setContent(String newContent) {
		content = newContent;
	}
	
	/*
	 * Return all the song in this album
	 */
	public List<SoundClip> getSongs() {
		return songs;
	}
	/*
	 * Adds a song to this album
	 * 
	 * Requires that the song dosen't exist in this album already
	 */
	public void addSong(SoundClip song) {
		assert !songs.contains(song);
		songs.add(song);
	}
	
	/*
	 * Gets a song from this album
	 * 
	 * Requires that the index is a valid index in the List
	 */
	public SoundClip getSong(int index) {
		assert 0 <= index && index < songs.size();
		return songs.get(index);
	}
	/*
	 * Removes a song from this album
	 * 
	 * Requires that the index is a valid index in the List
	 */
	public void deleteSong(int index) {
		assert 0 <= index && index < songs.size();
		songs.remove(index);
	}
	/*
	 * Removes a song from this album
	 */
	public void deleteSong(SoundClip song) {
		songs.remove(song);
	}
}
