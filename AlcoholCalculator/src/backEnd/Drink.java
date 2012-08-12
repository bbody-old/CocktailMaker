package backEnd;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Class for a Drink. Holds only a name, percentage and comment.
 * @author Brendon Body
 * @version 25 December 2011
 */
public class Drink {
	private String drinkName;
	private double drinkAlcoholPercentage;
	private String comment;
	
	/**
	 * Default Constructor.
	 * @param drinkName		Given drink name.
	 * @param drinkAlcoholPercentage	Given drink Percentage
	 */
	public Drink(String drinkName, double drinkAlcoholPercentage){
		this.drinkName = drinkName;
		this.drinkAlcoholPercentage = drinkAlcoholPercentage;
		comment = Const.defaultComment;
	}
	
	/**
	 * Constructor which calls the default and adds its' own default
	 * drink alcohol percentage.
	 * @param drinkName		Given Drink name.
	 */
	public Drink(String drinkName){
		this(drinkName, Const.drinkDefaultPercent);
	}
	
	/**
	 * Constructor which calls the default and adds its own default name.
	 * @param drinkAlcoholPercentage	Given drink percentage.
	 */
	public Drink(double drinkAlcoholPercentage){
		this(Const.drinkDefaultName, drinkAlcoholPercentage);
	}
	
	/**
	 * Calls the default and gives its own default name and drink alcohol
	 * percentage.
	 */
	public Drink(){
		this(Const.drinkDefaultName, Const.drinkDefaultPercent);
	}

	/**
	 * Getter method for comment of the drink.
	 * @return comment	The comment of the drink.
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Setter method for comment of the drink.
	 * @param comment	The new comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Getter method for the drink name.
	 * @return	drinkName	Name of the drink
	 */
	public String getDrinkName() {
		return drinkName;
	}
	
	/**
	 *  Setter method for the drink name.
	 * @param drinkName	The new drink name
	 */
	public void setDrinkName(String drinkName) {
		this.drinkName = drinkName;
	}

	/**
	 * Getter method for the drink alcohol percentage.
	 * @return	drinkPercentage		The percent of alcohol
	 */
	public double getDrinkAlcoholPercentage() {
		return drinkAlcoholPercentage;
	}

	/**
	 * Setter method for drinks' alcohol percentage.
	 * @param drinkAlcoholPercentage	The drinks new alcohol percentage
	 */
	public void setDrinkAlcoholPercentage(double drinkAlcoholPercentage) {
		this.drinkAlcoholPercentage = drinkAlcoholPercentage;
	}
	
	/**
	 * A representation of a String[] with [drinkName, drinkPercentage].
	 * @return String[] 	A list of all the attributes in the class
	 */
	public String [] toStringArray(){
		String s[] = new String[2];
		s[0] = drinkName;
		
		NumberFormat formatter = new DecimalFormat(Const.numberFormat);
		s[1] = formatter.format(drinkAlcoholPercentage);
		return s;
	}
	
	/**
	 * Compares two drinks to see if their internal attributes are the same.
	 * @param drink		Another drink to compare it to
	 * @return boolean	If they are equal(true) or not (false)
	 */
	public boolean equals(Drink drink){
		// Check attributes to see if they are the same, if not return false
		// If passes all, it will return true
		
		if (drink.getDrinkAlcoholPercentage() !=
				this.getDrinkAlcoholPercentage()){
			return false;
		} 
		
		if (!drink.getDrinkName().equals( 
				this.getDrinkName())){
			return false;
		} 

		return true;
	}
	
}
