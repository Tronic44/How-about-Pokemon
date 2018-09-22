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
			gui.Manage.msgbox("Da ist was schief gelaufen   Code:PKD-CWp-1" + "\n" + ioe.toString());
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
			gui.Manage.msgbox("Da ist was schief gelaufen   Code:PKD-CWp-2" + "\n" + ioe.toString());
//			ioe.printStackTrace();
		}
	}

	public static String[][] read(String datei) {
		String[][] list = new String[100][2];
		String[][] tlist;
		String linesp[] = new String[1];
		String line;
		FileReader fr;
		try {
			fr = new FileReader(datei + ".txt");
			try {
				BufferedReader br = new BufferedReader(fr);
				try {
					int i = 0;
					do {
						line = br.readLine();
						if (line == null||line.trim().length()<2) {
							break;							
						}else {
							linesp = line.split(":");
							for (int k = 0; k < linesp.length; k++) {
								list[i][k] = linesp[k];
							}
							i++;
						}
					} while (line != null);

				} catch (Exception e) {
					gui.Manage.msgbox("Da ist was schief gelaufen   Code:PKD-CWr-1" + "\n" + e.toString());
					e.printStackTrace();
				}
				int count = 0;
				while (list[count][0] != null) {
					count++;
				}
				tlist = new String[count][list[0].length];
				for (int i = 0; i < tlist.length; i++) {
					for (int k = 0; k < tlist[0].length;) {
						tlist[i][k] = list[i][k];
					}
				}
				br.close();
				return tlist;
			} catch (IOException e) {
				gui.Manage.msgbox("Da ist was schief gelaufen   Code:PKD-CWr-2" + "\n" + e.toString());
//				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			PrintWriter pWriter = null;
			try {
				pWriter = new PrintWriter(new BufferedWriter(new FileWriter(datei + ".txt", true)), true);
//				pWriter.close();
			} catch (IOException ioe) {
				gui.Manage.msgbox("Da ist was schief gelaufen   Code:PKD-CWr-3" + "\n" + ioe.toString());
//				ioe.printStackTrace();
			}
			gui.Manage.msgbox("Da ist was schief gelaufen   Code:PKD-CWr-4"+"   "+datei + "\n" + e.toString());
			e.printStackTrace();
		}
		return null;
	}

}
