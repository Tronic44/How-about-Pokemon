package client;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

@SuppressWarnings({ "serial", "rawtypes" })
public class FilterComboBox extends JComboBox {
	private List<String> array;
	JTextField textfield;

	@SuppressWarnings("unchecked")
	public FilterComboBox(List<String> array) {
		super(array.toArray());
		this.array = array;
		this.setEditable(true);
		textfield = (JTextField) this.getEditor().getEditorComponent();
		textfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent ke) {
				SwingUtilities.invokeLater(() -> comboFilter(textfield.getText()));
			}
		});
	}

	public FilterComboBox() {
		super();
		textfield = null;
	}

	public void setList(List<String> array) {
		this.array = array;
	}

	public void setList(String[] array) {
		this.array = new ArrayList<>();
		for (String k : array) {
			this.array.add(k);
		}
		textfield = (JTextField) this.getEditor().getEditorComponent();
		textfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent ke) {
				SwingUtilities.invokeLater(() -> comboFilter(textfield.getText()));
			}
		});
	}

	@SuppressWarnings("unchecked")
	public FilterComboBox(String[] array) {
		super(array);
		List<String> arr = new ArrayList<>();
		for (String k : array) {
			arr.add(k);
		}
		this.array = arr;
		this.setEditable(true);
		textfield = (JTextField) this.getEditor().getEditorComponent();
		textfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent ke) {
				SwingUtilities.invokeLater(() -> comboFilter(textfield.getText()));
			}
		});
	}

	@SuppressWarnings("unchecked")
	private void comboFilter(String enteredText) {
		if (!this.isPopupVisible()) {
			this.showPopup();
		}
		List<String> filterArray = new ArrayList<>();
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i).toLowerCase(Locale.ENGLISH).contains(enteredText.toLowerCase(Locale.ENGLISH))) {
				filterArray.add(array.get(i));
			}
		}
		if (!filterArray.isEmpty()) {
			DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) this.getModel();
			model.removeAllElements();
			for (String s : filterArray) {
				model.addElement(s);
			}
			JTextField textfield = (JTextField) this.getEditor().getEditorComponent();
			textfield.setText(enteredText);
		}
	}
}