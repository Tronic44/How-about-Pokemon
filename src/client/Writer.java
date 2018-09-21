package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Writer {

	public static void printtierlist(String name, char[] text) {
		PrintWriter pWriter = null;
		try {
			pWriter = new PrintWriter(new BufferedWriter(new FileWriter("tierlist.txt", true)), true);
			pWriter.print(name + ":");
			for (int i = 0; i < text.length; i++) {
				pWriter.print(text[i]);
			}
			pWriter.println();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static String[][] readtierlist() {
		String[][] list = new String[100][2];
		String[][] tierlist;
		String linesp[];
		String line;
		FileReader fr;
		try {
			fr = new FileReader("tierlist.txt");
			try {
				BufferedReader br = new BufferedReader(fr);

				try {
					int i = 0;
					do {
						line = br.readLine();
						linesp = line.split(":");
						list[i][0] = linesp[0];
						list[i][1] = linesp[1];
						i++;
					} while (line != null);
				} catch (Exception e) {

//					e.printStackTrace();
				}
				int count = 0;
				do {
					count++;
				} while (list[count][0] != null);
				tierlist = new String[count][2];
				for (int i = 0; i < tierlist.length; i++) {
					tierlist[i][0] = list[i][0];
					tierlist[i][1] = list[i][1];
				}
				br.close();
				return tierlist;
			} catch (IOException e) {
				
			}
		} catch (FileNotFoundException e) {
			PrintWriter pWriter = null;
			try {
				pWriter = new PrintWriter(new BufferedWriter(new FileWriter("tierlist.txt", true)), true);					
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			
//			e.printStackTrace();
		}
		return null;
	}

}
