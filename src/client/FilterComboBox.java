package client;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

@SuppressWarnings({ "serial", "rawtypes" })
public class FilterComboBox extends JComboBox {
	private List<String> array;

	public FilterComboBox(List<String> array) {
		super(array.toArray());
		this.array = array;
		this.setEditable(true);
		final JTextField textfield = (JTextField) this.getEditor().getEditorComponent();
		textfield.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				SwingUtilities.invokeLater(() -> comboFilter(textfield.getText()));
			}
		});
	}

	public FilterComboBox(String[] array) {
		super(array);
		List<String> arr = null;
		for (String k : array) {
			arr.add(k);
		}
		this.array = arr;
		this.setEditable(true);
		final JTextField textfield = (JTextField) this.getEditor().getEditorComponent();
		textfield.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				SwingUtilities.invokeLater(() -> comboFilter(textfield.getText()));
			}
		});
	}

	public void comboFilter(String enteredText) {
		if (!this.isPopupVisible()) {
			this.showPopup();
		}
		List<String> filterArray = new ArrayList<>();
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i).toLowerCase().contains(enteredText.toLowerCase())) {
				filterArray.add(array.get(i));
			}
		}
		if (!filterArray.isEmpty()) {
			DefaultComboBoxModel model = (DefaultComboBoxModel) this.getModel();
			model.removeAllElements();
			for (String s : filterArray)
				model.addElement(s);

			JTextField textfield = (JTextField) this.getEditor().getEditorComponent();
			textfield.setText(enteredText);
		}
	}
}