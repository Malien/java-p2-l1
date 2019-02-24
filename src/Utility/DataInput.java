package Utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class DataInput {

	private static void writeText(String wr){
		if (wr == null)
			System.out.print("Введіть дані: ");
		else 
			System.out.print(wr);
	}
	
	public static Long getLong() throws IOException{
		String s = getString();
		Long value = Long.valueOf(s);
		return value;
	}
	
	public static char getChar(){
		String s = getString();
		return s.charAt(0);
	}

	//getInt with handled FormatException
	public static Integer getInt(String wr){
		writeText(wr);
		String s;
		while (true){
				s = getString();
			Integer value;
			try {
				value = Integer.valueOf(s);
			}catch (NumberFormatException e){
				System.out.println("You can only enter integer numbers.");
				continue;
			}
			return value;
		}
	}
	
	public static String getString() {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s;
		try {
			s = br.readLine();
		}catch(IOException e){
			return "Bad input";
		}
		return s;
	}

	public static String getString(String str){
		try{
			System.out.print(str);
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			String s = br.readLine();
			return s;
		} catch (IOException e){
			System.out.println(e.getStackTrace());
			return "";
		}
	}
}
