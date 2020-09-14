package backend.interfaces;

import java.util.List;

import backend.SoundClip;

public interface AlbumIF<E> {
	
	public List<E> getSongs();
	public void addSong(E song);
	public SoundClip getSong(int index);
	public void deleteSong(int index);
}
