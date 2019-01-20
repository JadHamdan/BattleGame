//NAME: JAD HAMDAN
//STUDENT ID: 260870558

//import the necessary package random.
import java.util.Random;
public class Spell {
	//our spell will have 4 attributes, a string for its name and 3 doubles for its minimumDamage, maximumDamage and chance of success.
	private String name;
	private double minDamage;
	private double maxDamage;
	private double chanceOfSuccess;
	
	//the constructor for our spell uses the this keyword to assign each attribute, as usual.
	public Spell(String name, double minDamage, double maxDamage, double chanceOfSuccess) {
		this.name = name;
		//we use an if statement to throw an IllegalArgumentException if the spell's damage doesn't make sense.
		if (minDamage<0 || minDamage>maxDamage) {
			throw new IllegalArgumentException();
		} else {
			this.minDamage = minDamage;
		}
		this.maxDamage = maxDamage;
		this.chanceOfSuccess = chanceOfSuccess;
	}
	//a standard get method to get the spell's name:
	public String getName(){
		return this.name;
	}
	//our getMagicDamage method uses a random generator with the method's input as a seed to see if the spell was successfully casted.
	public double getMagicDamage(int seed){
		Random generator = new Random(seed);
		double random = generator.nextDouble();
		//if the random is superior to the chance of Success, then the spell casting has failed, so return 0.
		if (random>this.chanceOfSuccess) {
			return 0;
		} else {
			//if not, then return a random double representing the damage, between the minimum and maximum damage of that spell.
			return (generator.nextDouble()*(this.maxDamage-this.minDamage) + this.minDamage);
		}
	}
	//our toString method for spells displays a string, spellInfo, that has all the needed info about a spell.
	//to do so, we create an empty string spellInfo, multiple statements to add strings to it and finally return it to our toString method.
	public String toString() {
		String spellInfo = "";
		spellInfo += "Name: ";
		spellInfo += this.name;
		spellInfo += " Damage: ";
		spellInfo += this.minDamage + "-";
		spellInfo += this.maxDamage;
		spellInfo += " Chance: ";
		spellInfo += this.chanceOfSuccess*100;
		spellInfo += "%";
		return spellInfo;
	}
}
