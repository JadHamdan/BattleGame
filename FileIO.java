//NAME: JAD HAMDAN
//STUDENT ID: 260870558

//We import our necessary packages: io, ArrayList

import java.io.*;
import java.util.ArrayList;
public class FileIO {
	//the readCharacter method will create a new character based on a text file whose name we use as input. So the method will return a character.
	public static Character readCharacter(String fileName) {
		//my idea was to create an ArrayList that will contain the character's attributes in order, so when we create the character
		//we will call the constructor with the 1st, 2nd, 3rd and 4th elements of the ArrayList as input.
		ArrayList<String> characterInfo = new ArrayList<String>();
		//The following code inside the try/catch block is generic for reading files, nothing special.
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			//for every line encountered, the string will be stored in the array list created earlier using a while loop
			//so the first line's string will be the first element in the ArrayList, etc.
			String line;
            while((line=br.readLine()) != null) {
                characterInfo.add(line);

            }
			br.close();
			fr.close();
			//not to forget that FileReader and BufferedReader throw exceptions, which we handle with two catch blocks.
		} catch (FileNotFoundException e){
			System.out.println("Unexisting file");
		} catch (IOException e) {
			System.out.println("Something went wrong");
		}

		//As described earlier, we want to use the ArrayList's elements as inputs for the constructor.
		//for readability and for the inputs to be of the correct type, i stored each element of the ArrayList in a variable with the corresponding
		//attribute as a name.
		String name = characterInfo.get(0);
		double attackValue = Double.parseDouble(characterInfo.get(1));
		double maxHealth = Double.parseDouble(characterInfo.get(2));
		int numWins = Integer.parseInt(characterInfo.get(3));
		//after plugging all the variables in a constructor to create a new character, we finally return that character.
		Character newCharacter = new Character(name, attackValue, maxHealth, numWins);
		return newCharacter;
	}
	
	//Next, a very similar method to create spells from a text file.
	public static ArrayList<Spell> readSpells(String fileName){
		//Again, i will use an ArrayList. This time, it'll be a list of spells.
		ArrayList<Spell> spells = new ArrayList<Spell>();
		//Again, try/catch blocks to read the file.
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String line;
			//All the action happens in the while loop:
            while((line=br.readLine()) != null) {
            	//for each line, as all of a spell's attributes are on the same line and seperated by tab, i use the split method with "	" as a separator.
            	//The resulting String Array of length 4 will be stored in a variable called spellInfo.
            	String[] spellInfo = line.split("	");
            	//For each line to create a character, I used the elements in the spellInfo array (attributes of the spells) as inputs in the spell constructor.
            	//I then add the created spell to our spell array.
                spells.add(new Spell(spellInfo[0],Double.parseDouble(spellInfo[1]),Double.parseDouble(spellInfo[2]),Double.parseDouble(spellInfo[3])));
            }
			br.close();
			fr.close();
			//Again, catch blocks for the FileNotFoundException and IOException thrown previously.
		} catch (FileNotFoundException e){
			System.out.println("Unexisting file");
		} catch (IOException e) {
			System.out.println("Something went wrong");
		}
		//the method returns the ArrayList of spells.
		return spells;
	}
	
	//Finally, a writeCharacter method to raise a character's number of wins.
	public static void writeCharacter(Character charToWrite, String fileName) {
		//FileWriter and BufferedWriter also cause an exception, so we put it all in a try block.
		try {
			FileWriter fw =new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(fw);
			//Since the writer will overwrite the file, we want it to print everything all over again.
			bw.write(charToWrite.getName());
			bw.newLine();
			bw.write(charToWrite.getAttackValue()+"");
			bw.newLine();
			bw.write(charToWrite.getMaxHealth()+"");
			bw.newLine();
			//everything is identical, except for the number of wins which we will proceed to raise by 1.
			bw.write(charToWrite.getNumWins()+1+"");
			//a print statement to confirm that the number of wins has been updated successfully:
			System.out.println("Successfully wrote to file: " + fileName);
			bw.close();
			fw.close();
			//A catch block for the IOException thrown by the method used previously.
		} catch (IOException e) {
			System.out.println("Something went wrong");
		}
		
	}
	//public static void main (String[] args) {
	//BattleGame.playGame("/Users/jadhamdan/eclipse-workspace/Assignment5/bin/player.txt", "/Users/jadhamdan/eclipse-workspace/Assignment5/bin/monster.txt", "/Users/jadhamdan/eclipse-workspace/Assignment5/bin/spells.txt" );
	//}
}
