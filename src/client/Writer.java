package client;

import java.io.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.json.*;

/**
 * Die Klasse Writer ist die Schnittstelle zwischen Programm und Datein. Sie
 * dient dem Speichnern von Programminhalten entweder als .txt Datei oder .json.
 * Desweiternen liest sie diese Dateien ein überprüft sie auf Fehler und gibt
 * sie an das Programm weiter, um das wiederherstellen von vorherigen
 * einstellungen zu ermöglichen. Es wird generell nicht empfohlen Dateien
 * manuell zu verändern um Fehler oder gar Programm abstürze zu vermeiden.
 * 
 * @author yannick Dreher
 *
 */
public class Writer {

	protected static void print(String datei, String name, char[] text) {
		PrintWriter pWriter = null;
		try {
			pWriter = new PrintWriter(new BufferedWriter(new FileWriter(datei + ".txt", true)), true);
			pWriter.print(name + ":");
			for (int i = 0; i < text.length; i++) {
				pWriter.print(text[i]);
			}
			pWriter.println();
		} catch (IOException ioe) {
			Manage.msgboxerr("Da ist was schief gelaufen   Code:PKD-CWp-1" + "\n" + ioe.toString());
		}
	}

	protected static void print(String datei, String name, String[] text) {
		PrintWriter pWriter = null;
		try {
			pWriter = new PrintWriter(new BufferedWriter(new FileWriter(datei + ".txt", true)), true);
			pWriter.print(name + ":");
			for (int i = 0; i < text.length; i++) {
				pWriter.print(text[i]);
			}
			pWriter.println();
		} catch (IOException ioe) {
			Manage.msgboxerr("Da ist was schief gelaufen   Code:PKD-CWp-2" + "\n" + ioe.toString());
		}
	}

	protected static String[][] read(String datei) {
		FileReader fr;
		String line;
		String[] zeile;
		String[][] list;
		String[][] buffer = new String[50][];

		for (int rewrite = 0; rewrite <= 1; rewrite++) {
			try {
				fr = new FileReader(datei + ".txt");
				try {
					// List Zeile für Zeile die Datei und buffert diese
					BufferedReader br = new BufferedReader(fr);
					int i = 0;
					do {
						line = br.readLine();
						if (line != null) {
							zeile = line.split(":");
							buffer[i] = zeile;
							i++;
						}
					} while (line != null && i < 50);
					list = new String[i][];
					for (int k = 0; k < i; k++) {
						list[k] = buffer[k];

					}
					br.close();
					return list;
				} catch (Exception e) {
					Manage.msgboxerr("Da ist was schief gelaufen   Code:PKD-CWr-1" + "\n" + e.toString());
//					e.printStackTrace();
				}
				rewrite++;
			} catch (FileNotFoundException e) {
				PrintWriter pWriter = null;
				try {
					pWriter = new PrintWriter(new BufferedWriter(new FileWriter(datei + ".txt", true)), true);
					pWriter.close();
				} catch (IOException ioe) {
					if (rewrite != 0) {
						Manage.msgboxerr("Da ist was schief gelaufen   Code:PKD-CWr-3" + "\n" + ioe.toString());
//						ioe.printStackTrace();
					}
				}
			}
		}

		return null;
	}

	protected static int safeasjson(String name, JFrame frame) {
		try {
			JSONArray listname;
			JSONObject drafts = readjson(frame);
			listname = drafts.getJSONArray("name");
			drafts.remove("name");
			if (listname.toList().contains(name)) {
				Manage.msgbox("Der Name existiert schon", frame);
				return 0;
			}
			listname.put(name);
			drafts.put("name", listname);

			JSONObject aktuell = new JSONObject();
			aktuell.put("poketier", Data.getTierlist());
			aktuell.put("poketierclone", Data.getTierlistclone());
			aktuell.put("team", MainMenu.getwindow().getteam());
			aktuell.put("settings", MainMenu.getwindow().getsettings());

			drafts.put(name, aktuell);

			try (FileWriter file = new FileWriter("Draft.json")) {
				System.out.println("Datei:" + drafts.toString(4));
				file.write(drafts.toString(4));
				return 1;
			} catch (IOException e) {
				Manage.msgbox("Beim schreiben der json-Datei ist ein Fehler aufgetreten    Code:PKD-safeasjson-1" + "\n"
						+ e.toString(), frame);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Object[] options = { "Datei Überschreiben, Daten gehen verloren!",
					"Nichts tun, die Daten werden nicht verändert" };
			switch (JOptionPane.showOptionDialog(null, "Es wurden Fehler in der 'Draft.json' Datei enteckt",
					"Es sind noch Dinge ungeklärt", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, options, options[1])) {
			case 0:
				PrintWriter pWriter = null;
				try {
					pWriter = new PrintWriter(new BufferedWriter(new FileWriter("Draft.json")), true);
					pWriter.print("{" + "\n" + "\"" + "name" + "\"" + ": [" + "\n" + "]" + "}");
					safeasjson(name, frame);
				} catch (IOException g) {
					Manage.msgbox("Beim Lesen der json-Datei ist ein Fehler aufgetreten    Code:PKD-safeasjson-2" + "\n"
							+ g.toString(), frame);
					return 0;
				}
				pWriter.close();
			case 1:
				return 0;
			}
		}
		return 0;

	}

	protected static void loadjson(JFrame frame) {
		JSONObject file = readjson(frame);
	}

	protected static JSONObject readjson(JFrame frame) {
		try {
			File file = new File("./Draft.json");
			return new JSONObject(FileUtils.readFileToString(file, "utf-8"));
		} catch (FileNotFoundException e) {
			PrintWriter pWriter = null;
			try {
				pWriter = new PrintWriter(new BufferedWriter(new FileWriter("Draft.json")), true);
				pWriter.print("{" + "\n" + "\"" + "name" + "\"" + ": [" + "\n" + "]" + "}");
			} catch (IOException g) {
				Manage.msgbox("Beim Lesen der json-Datei ist ein Fehler aufgetreten    Code:PKD-readjson-1" + "\n"
						+ g.toString(), frame);
				return null;
			}
			pWriter.close();
			try {
				return readjson(frame);
			} catch (Exception f) {
				Manage.msgbox("Beim Lesen der json-Datei ist ein Fehler aufgetreten    Code:PKD-readjson-2" + "\n"
						+ f.toString(), frame);
				return null;
			}
		} catch (JSONException e) {
			try {
				Object[] options = { "Datei Überschreiben, Daten gehen verloren!",
						"Nichts tun, die Daten werden nicht verändert" };
				switch (JOptionPane.showOptionDialog(frame, "Es wurden Fehler in der 'Draft.json' Datei enteckt",
						"Es sind noch Dinge ungeklärt", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
						null, options, options[1])) {
				case 0:
					PrintWriter pWriter = null;
					try {
						pWriter = new PrintWriter(new BufferedWriter(new FileWriter("Draft.json")), true);
						pWriter.print("{" + "\n" + "\"" + "name" + "\"" + ": [" + "\n" + "]" + "}");
					} catch (IOException g) {
						Manage.msgbox("Beim Lesen der json-Datei ist ein Fehler aufgetreten    Code:PKD-readjson-3"
								+ "\n" + g.toString(), frame);
						return null;
					}
					pWriter.close();
					try {
						return readjson(frame);
					} catch (Exception f) {
						Manage.msgbox("Beim Lesen der json-Datei ist ein Fehler aufgetreten    Code:PKD-readjson-4"
								+ "\n" + f.toString(), frame);
						return null;
					}
				case 1:
					return null;
				}
				return null;
			} catch (Exception f) {
				Manage.msgbox("Beim Lesen der json-Datei ist ein Fehler aufgetreten    Code:PKD-readjson-5" + "\n"
						+ f.toString(), frame);
				return null;
			}
		} catch (IOException e) {
			Manage.msgbox(
					"Beim Lesen der json-Datei ist ein Fehler aufgetreten    Code:PKD-readjson-6" + "\n" + e.toString(),
					frame);
			return null;
		}
	}
}