package backend;
import java.io.File;

import javax.swing.ImageIcon;

/**
 * SoundClip is a class representing a digital
 * sound clip file on disk.
 */
public class SoundClip {

	private final File file;
	private boolean flaggedSoundClip;
	private int rating;
	
	/**
	 * Make a SoundClip from a file.
	 * Requires file != null.
	 */
	public SoundClip(File file) {
		assert file != null;
		this.file = file;
		this.flaggedSoundClip = false;
		this.rating = 3;
	}

	/**
	 * @return the file containing this sound clip.
	 */
	public File getFile() {
		return file;
	}
	
	public String toString(){
		if(flaggedSoundClip) {
			return file.getName() + " F";
		} else {
			return file.getName();
		}
	}
	
	public void flagSoundClip() {
		flaggedSoundClip = true;
	}
	
	public void unflagSoundClip() {
		flaggedSoundClip = false;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public int getRating() {
		return rating;
	}
	
	@Override
	public boolean equals(Object obj) {
		return 
			obj instanceof SoundClip
			&& ((SoundClip)obj).file.equals(file);
	}
	
	@Override
	public int hashCode() {
		return file.hashCode();
	}
}
