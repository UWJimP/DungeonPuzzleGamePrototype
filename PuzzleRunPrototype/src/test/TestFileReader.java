package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class TestFileReader {

	public static void main(String[] args) {
		try {			
			FileReader file = new FileReader("src/game/levels/test/test1.txt");
			BufferedReader reader = new BufferedReader(file);

			//Read the board size from the file.
			String size = reader.readLine();
			System.out.println(size);
			
			//Once the board size has been determined, draw it out.
			String line;
			while((line = reader.readLine()) != null) {
				char[] characters = line.toCharArray();
				for(int i = 0; i < characters.length; i++) {
					System.out.print(characters[i]);
				}
				System.out.print("\n");
			}
			
			reader.close();
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
