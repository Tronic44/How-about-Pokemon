package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Writer {

	public static void print(String name, String text) {
		PrintWriter pWriter = null;
		if (read(name) == null) {
			try {
				pWriter = new PrintWriter(new BufferedWriter(new FileWriter(name.toLowerCase() + ".txt")));
				pWriter.println(text);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				if (pWriter != null) {
					pWriter.flush();
					pWriter.close();
				}
			}
		}else {
			try {
				pWriter = new PrintWriter(new BufferedWriter(new FileWriter(name.toLowerCase() + ".txt")), true);
				pWriter.println(text);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				if (pWriter != null) {
					pWriter.flush();
					pWriter.close();
				}
			}
		}
	}

	public static String read(String name) {
		FileReader fr;
		try {
			fr = new FileReader(name.toLowerCase() + ".txt");
			try {
				BufferedReader br = new BufferedReader(fr);

				String line = br.readLine();

				br.close();
				return line;
			} catch (IOException e) {
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
