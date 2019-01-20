//NAME: JAD HAMDAN
//STUDENT ID: 260870558

//Necessary packages: Random, Scanner, ArrayList.
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
public class BattleGame {
	//our BattleGame method has 1 static attribute: a random to generate a seed that will control mostly everything.
	private static Random random = new Random();
	
	//the play game method will allows us to play the game, linking all methods we made: 
	//it takes as input the file names for the two characters that will be created and the spells available.
	public static void playGame(String playerFileName, String monsterFileName, String spellsFileName) {
		//Firstly, we call our readCharacter method twice to create the character of the player and the opponent.
		//Also, we create an if loop to check for valid inputs
		if (!((FileIO.readCharacter(playerFileName) instanceof Character)||(FileIO.readCharacter(monsterFileName) instanceof Character))){
			System.out.println("The game cannot be played...");
			return;
		}
		
		Character player = FileIO.readCharacter(playerFileName);
		Character monster = FileIO.readCharacter(monsterFileName);
		
				
		//Next, we try to use our readSpells method to create all the spells based on the info on the input file.
		ArrayList<Spell> spellsFromFile = FileIO.readSpells(spellsFileName);
		//if the file has no info, no spells are created and so the game will be played without spells: 
		if (spellsFromFile.isEmpty()) {
			System.out.println("The game will be played without spells");
		}
		
		//we also call our setSpells method, this time in a static way so on the entire class Character, with the previously created ArrayList of spells as input.
		Character.setSpells(spellsFromFile);
		
		//using our helper method, characterIntro, we print our introduction text that has information about the characters in the game.
		System.out.println(player.characterIntro());
		System.out.println(monster.characterIntro());
				
		//displaySpells will allow us to display information about all the available spells.
		Character.displaySpells();
		
		//A scanner will be used to take the user's input.
		Scanner input = new Scanner(System.in);
		
		//next, we will create a String called listofSpells that is basically a continuous string with all the available spell's names attached to each other.
		//to do so, we create an empty string and add the names to to it using a for loop that iterates through the ArrayList.
		//This array list will be used to check if a player wants to cast a spell: if the player's input is contained in the string (ignoring case), the spell will be 
		//casted.
		String listOfSpells = "";
		for (int i = 0; i<spellsFromFile.size(); i++) {
			listOfSpells += spellsFromFile.get(i).getName();
		}
		
		//The game will happen inside of an "infinite" while loop. It will keep on iterating until break statements are reached inside.
		while (true) {
			//I decided to create an integer called switcher. Whenever the player or opponent does something it goes up by one. 
			int switcher = 0;
			//This will allow me to switch between both depending on switcher's parity.
			
			System.out.println("Enter a command:");
			//the user's input will be reduced to lower case to avoid naming conflicts and stored into a string variable called "command".
			String command = input.nextLine().toLowerCase();
			
			//if the user input is "quit", say goodbye and break the loop:
			if (command.equalsIgnoreCase("quit")) {
				System.out.println("Goodbye!");
				break;
			}

			//if the user's input is attack and switcher is even, the player will attack:
			if (command.equalsIgnoreCase("attack")&&(switcher%2==0)) {
				//we add 1 to the switcher, so after the loop ends it'll be the opponent's turn.
				switcher++;
				
				//using the BattleGame's random generator, we create a random int that will serve as a seed to get Attack Damage.
				int seed = random.nextInt();
				double playerDamage = player.getAttackDamage(seed);
				//we will format that damage nicely and store it in attackStr, a string variable.
				String attackStr = String.format("%1$.2f", playerDamage);
				//we then announce that damage and make the opponent take that damage.
				System.out.println(player.getName() + " attacks for " + attackStr + " damage!");
				monster.takeDamage(playerDamage);
				//an if else statement to display the opponent's health after the damage and break the loop, announcing that he is defeated, in case that health goes below zero.
				if (monster.getCurrHealth()>0) {
					System.out.println(monster);
				} else {
					System.out.println("Congratulations! " + monster.getName() + " has been defeated!");
					player.increaseWins();
					FileIO.writeCharacter(player, playerFileName);
					break;
				}
			}
			
			//if the user's input is not attack, but instead a spell (and switcher is even), the player will try to cast that spell:
			if (listOfSpells.contains(command)&&!(command.equals("attack"))&&(switcher%2==0)) {
				//again, make the switcher odd
				switcher++;
				//use our castSpell method with a random integer created with the BattleGame's random attribute to get the spell's damage.
				double damageDoneBySpell = player.castSpell(command, random.nextInt());
				//an if loop to tell us if the casting failed, so if the damage is inferior to 0.
				if (damageDoneBySpell<=0) {
					System.out.println(player.getName() + " tried but failed to cast the spell " + command);
				} else {
					//if the damage is positive, make the opponent take it and display his health + if he has been defeated like in the previous method.
					System.out.println(player.getName() + " casts " + command + " dealing " + damageDoneBySpell + " damage!");
					monster.takeDamage(damageDoneBySpell);
					if (monster.getCurrHealth()>0) {
						System.out.println(monster);
					} else {
						System.out.println("Congratulations! " + monster.getName() + " has been defeated!");
						player.increaseWins();
						FileIO.writeCharacter(player, playerFileName);
						break;
					}	
				}
				//if the input is not attack nor a valid spell, tell the user that the character doesn't know that spell.
			} else if (!(command.equals("attack"))) {
				switcher++;
				System.out.println(player.getName() + " tried to cast " + command + ", but they don't know that spell.");
				
			}
			//when the switcher is odd, the opponent attacks:
			if (switcher%2!=0) {
				//we make the switcher even again:
				switcher++;
				
				//create, yet again. a random integer that will be used as a seed.
				int seed = random.nextInt();

				//exactly like for the player, we get the opponent's attack damage and use it against the player, display his health if it is above 0 or that he has been defeated
				//if it isn't.
				double monsterDamage = monster.getAttackDamage(seed);
				String attackStr = String.format("%1$.2f", monsterDamage);
				System.out.println(monster.getName() + " attacks for " + attackStr + " damage!");
				player.takeDamage(monsterDamage);
				if (player.getCurrHealth()>0) {
					System.out.println(player);
				} else {
					System.out.println(player.getName() + " has been defeated! You lost.");
					monster.increaseWins();
					FileIO.writeCharacter(monster, monsterFileName);
					break;
				}
			}
		}
		//close the scanner when the while loop is done.
		input.close();
	}
	
	
	
	
}
