package logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Record {
	
	private static int TEST_RECORD; 
	private static int EASY_RECORD;
	private static int HARD_RECORD;
	private static int ADVANCED_RECORD;

	public static void readRecord(Game game) throws FileNotFoundException, IOException{
		try(FileReader read = new FileReader("record.txt"); 
			BufferedReader bufferedRead = new BufferedReader(read)){
			String data; 
			data = bufferedRead.readLine();
			while(data != null){
				if (data.startsWith("T")) {
					data = data.replace("TEST:", "");  //i do this beacuse i only want the numbers, so that i can put them into the atributes for later use
					TEST_RECORD = Integer.parseInt(data);
				}
				else if (data.startsWith("E")) {
					data = data.replaceAll("EASY:", "");
					EASY_RECORD = Integer.parseInt(data);
				}
				else if (data.startsWith("H")) {
					data = data.replaceAll("HARD:", "");
					HARD_RECORD = Integer.parseInt(data);
				}
				else if (data.startsWith("A")) {
					data = data.replaceAll("ADVANCED:", "");
					ADVANCED_RECORD = Integer.parseInt(data);
				}
				data = bufferedRead.readLine();
			}
		}
	}
	
	public static void writeRecord(Game game) throws IOException{
		try(FileWriter write = new FileWriter("record.txt"); 
			BufferedWriter bufferedWrite = new BufferedWriter(write)){
			StringBuilder data = new StringBuilder();
			data.append("TEST:");
			data.append(TEST_RECORD);
			data.append("\nEASY:");
			data.append(EASY_RECORD);
			data.append("\nHARD:");
			data.append(HARD_RECORD);
			data.append("\nADVANCED:");
			data.append(ADVANCED_RECORD);
			bufferedWrite.write(data.toString());
		}
	}

	public static Integer giveRecord(String name) {
		if (name.equalsIgnoreCase("test")) {
			return TEST_RECORD;
		}
		else if (name.equalsIgnoreCase("easy")) {
			return EASY_RECORD;
		}
		else if (name.equalsIgnoreCase("hard")) {
			return HARD_RECORD;
		}
		else {
			return ADVANCED_RECORD;
		}
	}
	
	public static void overWriteRecord(long time, String name){
		if (name.equalsIgnoreCase("test")) {
			TEST_RECORD = (int) time;
		}
		else if (name.equalsIgnoreCase("easy")) {
			EASY_RECORD = (int) time;
		}
		else if (name.equalsIgnoreCase("hard")) {
			HARD_RECORD = (int) time;
		}
		else {
			ADVANCED_RECORD = (int) time;
		}
	}

	public static void createFile() throws IOException {
		try(FileWriter write = new FileWriter("record.txt"); 
			BufferedWriter bufferedWrite = new BufferedWriter(write)){
			StringBuilder data = new StringBuilder();
			data.append("TEST:");
			data.append("\nEASY:");
			data.append("\nHARD:");
			data.append("\nADVANCED:");
			bufferedWrite.write(data.toString());
		}
	}
}