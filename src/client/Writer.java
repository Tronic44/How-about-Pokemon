package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Writer {

	public static void print(String datei, String name, char[] text) {
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
//			ioe.printStackTrace();
		}
	}

	public static void print(String datei, String name, String[] text) {
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
//			ioe.printStackTrace();
		}
	}

	public static String[][] read(String datei) {
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
					} while (line != null);

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
}
