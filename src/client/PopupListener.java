package client;

import javax.swing.JComboBox;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class PopupListener implements PopupMenuListener {
	
	protected JComboBox<String> box;
	
	public PopupListener(JComboBox<String> box) {
		this.box = box;
	}

	@Override
	public void popupMenuCanceled(PopupMenuEvent e) {

	}

	@Override
	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {

	}

	@Override
	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {

	}

}
