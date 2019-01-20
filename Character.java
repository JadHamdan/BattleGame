//NAME: JAD HAMDAN
//STUDENT ID: 260870558

//we import our necessary packages: Random, ArrayList.
import java.util.Random;
import java.util.ArrayList;
public class Character {
	//We need our 6 attributes, all private and initialized as indicated in the instructions.
	private String name;
	private double attackValue;
	private double maxHealth;
	private double currHealth;
	private int numWins;
	//Spells will be our only static attribute.
	private static ArrayList<Spell> spells;
	
	//As usual, our constructor creates an object of type character with the desires attributes using
	//the "this" keyword. We have a a string for the character's name, and 3 doubles (his attack value, health and number of wins).
	public Character(String name, double attackValue, double maxHealth, int numWins) {
		this.name = name;
		this.attackValue = attackValue;
		this.maxHealth = maxHealth;
		this.numWins = numWins;
		this.currHealth = maxHealth;
	}
	//Our set method to set the character's spells attribute:
	public static void setSpells(ArrayList<Spell> spells) {
		//in order to not allow anyone to change up a character's spells,
		//we first copy the input array list then make the copy the character's attribute.
		ArrayList<Spell> copy = new ArrayList<Spell>();
		for (int i = 0; i<spells.size(); i++) {
			copy.add(spells.get(i));
		}
		Character.spells = copy;
	}
	
	//To display the available spells, we can simply use a for loop:
	public static void displaySpells() {
		System.out.println("Here are the available spells:");
		//the loop will iterate through each element of the ArrayList of spells, and print out
		//the spell's information using the toString method from the Spells class (see the latter for more detail)
		for (int i=0; i<Character.spells.size(); i++) {
			System.out.println(Character.spells.get(i).toString());
		}
	}
	
	//Our cast spell method:
	public double castSpell(String name, int seed) {
		//using a for loop, we will iterate through  a character's list of spells.
		for (int i = 0; i<Character.spells.size(); i++) {
			//when we reach the spell we want (not case sensitive, so we use equalsIgnoreCase), the castSpell
			//method will use the getMagicDamage method to get the casted spell's damage, with the integer used
			//as input to castSpell as a seed. 
			if (Character.spells.get(i).getName().equalsIgnoreCase(name)) {
				return Character.spells.get(i).getMagicDamage(seed);
			} 
		}
		//if no spell is recognized, return -1.
		return -1;
	}
	
	//Next, a series of generic accessor methods using the this keyword,
	//they will allows us to get all of a character's non static attributes (so all but his/her spells).
	public String getName() {
		return this.name;
	}
	public double getAttackValue() {
		return this.attackValue;
	}
	public double getMaxHealth() {
		return this.maxHealth;
	}
	public double getCurrHealth() {
		return this.currHealth;
	}
	public int getNumWins() {
		return this.numWins;
	}
	
	//As instructed, the toString method for the character class will display the character's name and current health.
	public String toString() {
		//to do so, we will return a string info initialized as the empty string, and add to it both "this"'s name and current health.
		String info = "";
		info += this.name + " current health is " + this.currHealth;
		return info;
	}
	
	//This helper method, characterIntro, will display the first text the player sees
	public String characterIntro() {
		//this intro text will contain the player and opponent's name, current health (so maximum), attack value and number of wins).
		String info = "";
		info += ("Name: " + this.name +
				"\nHealth: " + this.currHealth + 
				"\nAttack: " + this.getAttackValue() +
				"\nNumber of wins: " + this.getNumWins());
		return info;	
	}
	
	//To get a character's attack damage, we will use a random object with the method's input as a seed.
	public double getAttackDamage(int seed) {
		Random randomValue = new Random(seed);
		//we then use the nextDouble method to generate a random double from 0 to 1. Since we want to multiply a character's attack value
		//by a random from 0.7 to 1, we will multiply the double obtained from .nextDouble() by (1-7.0)=3.0 and add back 0.7. 
		double damage = this.attackValue*((randomValue.nextDouble()*(0.3))+0.7);
		return damage;
	}
	
	//The take damage method is a very simple method. We input a double representing the damage we want to deal to a character, and consequently
	//reduce the character's health by that amount.
	public double takeDamage(double damage) {
		this.currHealth -= damage;
		return this.currHealth;
	}
	
	//increaseWins allows us to add 1 to a character's number of wins, it is a void method.
	public void increaseWins() {
		this.numWins += 1;
	}
}
