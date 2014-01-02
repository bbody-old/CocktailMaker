package backEnd;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Class for a Drink. Holds only a name, percentage and comment.
 * @author Brendon Body
 * @version 25 December 2011
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Drink {
	private String name;
	private double percentage;
	private String comment;
	
	public Drink(){
		this("",0.0);
	}
	
	/**
	 * Default Constructor.
	 * @param drinkName		Given drink name.
	 * @param drinkAlcoholPercentage	Given drink Percentage
	 */
	public Drink(String drinkName, double drinkAlcoholPercentage){
		this.name = drinkName;
		this.percentage = drinkAlcoholPercentage;
		comment = Const.defaultComment;
	}
	
	/**
	 * Default Constructor.
	 * @param drinkName		Given drink name.
	 * @param drinkAlcoholPercentage	Given drink Percentage
	 */
	public Drink(String drinkName, double drinkAlcoholPercentage, String comment){
		this.name = drinkName;
		this.percentage = drinkAlcoholPercentage;
		this.comment = comment;
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
		return name;
	}
	
	/**
	 *  Setter method for the drink name.
	 * @param drinkName	The new drink name
	 */
	public void setDrinkName(String drinkName) {
		this.name = drinkName;
	}

	/**
	 * Getter method for the drink alcohol percentage.
	 * @return	drinkPercentage		The percent of alcohol
	 */
	public double getDrinkAlcoholPercentage() {
		return percentage;
	}

	/**
	 * Setter method for drinks' alcohol percentage.
	 * @param drinkAlcoholPercentage	The drinks new alcohol percentage
	 */
	public void setDrinkAlcoholPercentage(double drinkAlcoholPercentage) {
		this.percentage = drinkAlcoholPercentage;
	}
	
	/**
	 * A representation of a String[] with [drinkName, drinkPercentage].
	 * @return String[] 	A list of all the attributes in the class
	 */
	public String [] toStringArray(){
		String s[] = new String[2];
		s[0] = name;
		
		NumberFormat formatter = new DecimalFormat(Const.numberFormat);
		s[1] = formatter.format(percentage);
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
