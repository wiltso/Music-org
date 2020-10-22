import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class MusicOrganizerButtonPanel extends JPanel {

	private MusicOrganizerController controller;
	private MusicOrganizerWindow view;
	
	private JButton newAlbumButton;
	private JButton deleteAlbumButton;
	private JButton addSoundClipsButton;
	private JButton removeSoundClipsButton;	
	private JButton playButton;
	private JButton undoButton;
	private JButton redoButton;
	private JButton flagButton;
	private JButton unflagButton;
	private JButton ratingButton;

	
	public MusicOrganizerButtonPanel(MusicOrganizerController contr, MusicOrganizerWindow view){
		super(new BorderLayout());

		controller = contr;
		
		this.view = view;
		
		JToolBar toolbar = new JToolBar();
		
		newAlbumButton = createNewAlbumButton();
		toolbar.add(newAlbumButton);

		deleteAlbumButton = createDeleteAlbumButton();
		toolbar.add(deleteAlbumButton);

		addSoundClipsButton = createAddSoundClipsButton();
		toolbar.add(addSoundClipsButton);

		removeSoundClipsButton = createRemoveSoundClipsButton();
		toolbar.add(removeSoundClipsButton);

		playButton = createPlayButton();
		toolbar.add(playButton);
		
		undoButton = createUndoButton();
		toolbar.add(undoButton);
		
		redoButton = createRedoButton();
		toolbar.add(redoButton);
		
		flagButton = createFlagButton();
		toolbar.add(flagButton);
		
		unflagButton = createUnflagButton();
		toolbar.add(unflagButton);
		
		ratingButton = createRatingButton();
		toolbar.add(ratingButton);
		
		this.add(toolbar);

	}
	
	private JButton createNewAlbumButton() {
		JButton newAlbumButton = new JButton("New Album");
		newAlbumButton.setToolTipText("New Album");
		newAlbumButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.addNewAlbum();
			}
		});
		return newAlbumButton;
	}
	
	private JButton createDeleteAlbumButton() {
		JButton deleteAlbumButton = new JButton("Remove Album");
		deleteAlbumButton.setToolTipText("Delete Selected Album");
		deleteAlbumButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.deleteAlbum();
			}
		});
		return deleteAlbumButton;
	}

	private JButton createAddSoundClipsButton() {
		JButton addSoundClipButton = new JButton("Add Sound Clips");
		addSoundClipButton.setToolTipText("Add Selected Sound Clips To Selected Album");
		addSoundClipButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				controller.addSoundClips();

			}
		});
		return addSoundClipButton;
	}
	
	private JButton createRemoveSoundClipsButton() {
		JButton removeSoundClipsButton = new JButton("Remove Sound Clips");
		removeSoundClipsButton.setToolTipText("Remove Selected Sound Clips From Selected Album");
		removeSoundClipsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.removeSoundClips();
			}
		});
		return removeSoundClipsButton;
	}
	
	private JButton createPlayButton() {
		JButton playButton = new JButton("Play");
		playButton.setToolTipText("Play Selected Sound Clip");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.playSoundClips();
			}
		});
		return playButton;
	}
	
	private JButton createUndoButton() {
		JButton undoButton = new JButton("Undo");
		undoButton.setToolTipText("Undo the Last Action");
		undoButton.setEnabled(false);
		undoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.undoLastAction();
			}
		});
		return undoButton;
	}
	
	private JButton createRedoButton() {
		JButton redoButton = new JButton("Redo");
		redoButton.setToolTipText("Redo the Last Action");
		redoButton.setEnabled(false);
		redoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redoLastAction();
			}
		});
		return redoButton;
	}
	
	private JButton createFlagButton() {
		JButton flagButton = new JButton("Flag");
		flagButton.setToolTipText("Flag the selected SoundClip(s)");
		flagButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.flagSoundClips();
			}
		});
		return flagButton;
	}
	
	private JButton createUnflagButton() {
		JButton unflagButton = new JButton("Unflag");
		unflagButton.setToolTipText("Unflag the selected SoundClip(s)");
		unflagButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.unflagSoundClips();
			}
		});
		return unflagButton;
	}
	
	private JButton createRatingButton() {
		JButton ratingButton = new JButton("Rate");
		ratingButton.setToolTipText("Set rating for the selected SoundClip(s)");
		ratingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.rateSoundClips();
			}
		});
		return ratingButton;
	}
	
	public void setEnabledNewAlbumButton(boolean state) {
		newAlbumButton.setEnabled(state);
	}
	
	public void setEnabledDeleteAlbumButton(boolean state) {
		deleteAlbumButton.setEnabled(state);
	}
	
	public void setEnabledAddSoundClipsButton(boolean state) {
		addSoundClipsButton.setEnabled(state);
	}
	
	public void setEnabledRemoveSoundClipsButton(boolean state) {
		removeSoundClipsButton.setEnabled(state);
	}
	
	public void setEnabledUndoButton(boolean state) {
		undoButton.setEnabled(state);
	}
	
	public void setEnabledRedoButton(boolean state) {
		redoButton.setEnabled(state);
	}

}
