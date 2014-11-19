package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestHelper {
	TestHelper(){
		
	}
	public static String fileToString(String file){
		String ret = "";
		try {
			FileInputStream fs = new FileInputStream(file);
			int c;
			while ((c = fs.read()) != -1) {
				ret = ret + (char) c;
			}
			fs.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
}
