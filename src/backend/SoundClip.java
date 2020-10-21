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
	private boolean ratedSoundClip;
	private int rating;
	
	/**
	 * Make a SoundClip from a file.
	 * Requires file != null.
	 */
	public SoundClip(File file) {
		assert file != null;
		this.file = file;
		this.flaggedSoundClip = false;
		this.ratedSoundClip = false;
		this.rating = 0;
	}

	/**
	 * @return the file containing this sound clip.
	 */
	public File getFile() {
		return file;
	}
	
	public String toString(){
		if(flaggedSoundClip && ratedSoundClip) {
			return file.getName() + " F " + rating;
		} else if (flaggedSoundClip) {
			return file.getName() + " F";
		} else if (ratedSoundClip) {
			return file.getName() + " " + rating;
		} else {
			return file.getName();
		}
	}
	
	public boolean isFlagged() {
		return flaggedSoundClip;
	}
	
	public void flagSoundClip() {
		flaggedSoundClip = true;
	}
	
	public void unflagSoundClip() {
		flaggedSoundClip = false;
	}
	
	public boolean isRated() {
		return ratedSoundClip;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
		soundClipRated();
	}
	
	public void soundClipRated() {
		this.ratedSoundClip = true;
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
