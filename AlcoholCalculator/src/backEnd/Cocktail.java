package backEnd;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.ResourceBundle;

/**
 * Class for a Cocktail drink. Essentially adding extra context specific
 * wrapping to a list of Drink @see backEnd.Drink
 * @author Brendon Body
 * @version 25 December 2011
 */
public class Cocktail {
	private String cocktailName;
	private LinkedList<Drink> drinkList;
	private LinkedList<Double> drinkVolumeList;
	private String comment;
	
	/**
	 * The default constructor for the Cocktail class.
	 * @param cocktailName	Name given to the cocktail.
	 */
	public Cocktail(String cocktailName){
		this.cocktailName = cocktailName;
		//drinks = new HashMap<Drink, Double>();
		drinkList = new LinkedList<Drink>();
		drinkVolumeList = new LinkedList<Double>();
		comment = Const.defaultComment;
	}
	
	/**
	 * Getter method for the Cocktail Name;
	 * @return cocktailName		name of cocktail (String)
	 */
	public String getCocktailName() {
		return cocktailName;
	}
	
	/**
	 * Setter method for the Cocktail name.
	 * @param cocktailName	new name for the Cocktail
	 */
	public void setCocktailName(String cocktailName) {
		this.cocktailName = cocktailName;
	}
	
	/**
	 * Searches the list of drinks to find any instances of a given drink.
	 * Mainly used for when adding a new drink to the Cocktail.
	 * @param d		Drink to be searched for
	 * @return index	Return index of where Drink d is (-1 if not in list)
	 */
	private int hasDrink(Drink d){
		for (int i = 0; i < drinkList.size(); i++){
			if (drinkList.get(i).equals(d)){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Adds a given Drink to the Cocktail, if the given drink is already
	 * in the Cocktail then it just combines their volumes.
	 * @param volume	volume of given Drink
	 * @param drink		the Drink to be added
	 */
	public void addDrink(Double volume, Drink drink){
		
		int index = hasDrink(drink);
		
		if (index == -1){
			drinkList.add(drink);
			drinkVolumeList.add(volume);
		} else {
			drinkList.set(index, drink);
			drinkVolumeList.set(index, 
					drinkVolumeList.get(index) + volume);
		}
	}
	
	/**
	 * Removes a Drink in the Cocktail which is in the given index.
	 * @param i		index of Drink to be removed
	 */
	public void removeDrink(int i){
		drinkList.remove(i);
		drinkVolumeList.remove(i);
	}
	
	/**
	 * Returns a LinkedList<String[]> representation of the Drinks in the
	 * Cocktail. Very useful for the GUI to display information about drinks.
	 * @return	LinkedList<String[]> Representation of the Drinks with their
	 * Name,	Percentage,		Volume in each String[]
	 */
	private LinkedList<String[]> getStringList(){
		LinkedList<String [] > listOfDrinks = new LinkedList<String[]>();
		
		for (int i = 0; i < drinkList.size(); i++){
			String str[] = new String[3];
			str[0] = drinkList.get(i).toStringArray()[0]; // Name
			str[1] = drinkList.get(i).toStringArray()[1]; // Percentage
			str[2] = drinkVolumeList.get(i).toString();	// Volume
			listOfDrinks.add(str);
		}
		
		return listOfDrinks;
	}
	
	/**
	 * Typical toString, used for debugging purposes.
	 * @return String	Representing Cocktail
	 */
	public String toString(ResourceBundle resourceBundle){
		LinkedList<String []> ll = getStringList();
		String str = Const.cocktailDefaultToStringColumns;
		
		for (int i = 0; i < ll.size(); i++){
			str += ll.get(i)[0];
			str += Const.tab;
			str += ll.get(i)[1];
			str += Const.tab;
			str += ll.get(i)[2];
			str += Const.newLine;
		}
		
		str += resourceBundle.getString("totalPerc");
		
		// Format the number to 2 decimal places.
		NumberFormat formatter = new DecimalFormat(Const.numberFormat);
		str += formatter.format(getTotalPercentage());
		
		str += Const.newLine;
		return str;
	}
	
	/**
	 * Returns a String representing the cocktail, mainly used for when
	 * the main menu and a preview is shown of the Cocktail.
	 * Its name, ingredients. any comments and Total Percent.
	 * @return String	String representing a cocktail
	 */
	public String toGuiStringPreview(ResourceBundle resourceBundle){
		// TODO add more html tags to make it look more readable
		DecimalFormat df = new DecimalFormat(Const.numberFormat);
		String str = Const.htmlOpen + 
				resourceBundle.getString("nameLabel");
		
		str += cocktailName + Const.htmlBR;
		
		str += resourceBundle.getString("consists");
		
		str += Const.ulOpen;
		for (int i = 0; i < drinkList.size(); i++){
			str += Const.liOpen + drinkList.get(i).getDrinkName() +
					Const.liClose;
		}
		str += Const.ulClose;
		/*
		if (comment != Const.defaultComment){
			// TODO Nicely format the comment so it will fit into the frame
			str += comment + Const.htmlBR;
		}*/
		
		str += Const.htmlBR +  
				resourceBundle.getString("totalPerc") + 
				df.format(getTotalPercentage()).toString() + 
				Const.htmlBR + Const.htmlClose;
		
		return str;
	}
	
	/**
	 * 
	 * @return
	 */
	public String toGuiStringViewNoHTML(ResourceBundle resourceBundle){
		// TODO add more html tags to make it look more readable
		String str = //Const.htmlOpen + 
				resourceBundle.getString("nameLabel");
		
		str += cocktailName + Const.newLine;// + Const.htmlBR;
		
		str += resourceBundle.getString("consists") + Const.newLine;
		
		//str += Const.ulOpen;
		DecimalFormat df = new DecimalFormat(Const.numberFormat);
		for (int i = 0; i < drinkList.size(); i++){
			df.format(drinkList.get(i).getDrinkAlcoholPercentage());
			str += //Const.liOpen + 
					drinkList.get(i).getDrinkName() + Const.tab2 +
					//Const.tab + 
					drinkVolumeList.get(i).toString() + 
					Const.volume + Const.tab2 +
					// Const.tab +
					Const.leftBracket + 
					df.format(drinkList.get(i).getDrinkAlcoholPercentage()).toString() + Const.rightBracket
					//+ Const.liClose
					+ Const.newLine;
		}
		str += Const.newLine;
		
		if (comment != Const.defaultComment){
			// TODO Nicely format the comment so it will fit into the frame
			str += comment + Const.newLine;// + Const.htmlBR;
		}
		
		str += //Const.htmlBR +  
				resourceBundle.getString("totalPerc") + 
				df.format(getTotalPercentage()).toString();// + 
				//Const.htmlBR + Const.htmlClose;
		
		return str;
	}
	
	/**
	 * 
	 * @return
	 */
	public String toGuiStringView(ResourceBundle resourceBundle){
		// TODO add more html tags to make it look more readable
		String str = Const.htmlOpen + 
				resourceBundle.getString("nameLabel");
		
		str += cocktailName + Const.htmlBR;
		
		str += resourceBundle.getString("consists");
		
		str += Const.ulOpen;
		DecimalFormat df = new DecimalFormat(Const.numberFormat);
		for (int i = 0; i < drinkList.size(); i++){
			df.format(drinkList.get(i).getDrinkAlcoholPercentage());
			str += Const.liOpen + drinkList.get(i).getDrinkName() +
					Const.tab + drinkVolumeList.get(i).toString() + 
					Const.volume + Const.tab + Const.leftBracket + 
					df.format(drinkList.get(i).getDrinkAlcoholPercentage()).toString() + Const.rightBracket
					+ Const.liClose;
		}
		str += Const.ulClose;
		
		if (comment != Const.defaultComment){
			// TODO Nicely format the comment so it will fit into the frame
			str += comment + Const.htmlBR;
		}
		
		str += Const.htmlBR +  
				resourceBundle.getString("totalPerc") + 
				df.format(getTotalPercentage()).toString() + 
				Const.htmlBR + Const.htmlClose;
		
		return str;
	}

	
	/**
	 * Setter method for the comments about the Cocktail.
	 * @param comment	New comment	
	 */
	public void setComment(String comment){
		this.comment = comment;
	}
	
	/**
	 * Getter method for the comments about the Cocktail.
	 * @return comment	The comment	
	 */
	public String getComment(){
		return comment;
	}
	
	/**
	 * Returns the amount of ingredients that are inside the Cocktail.
	 * @return int	How many drinks in the Cocktail.
	 */
	public int getSize(){
		return drinkList.size();
	}
	
	/**
	 * Returns the total alcohol percentage of the Cocktail, calculates it
	 * when called.
	 * @return	double	Total Percent of Cocktail.
	 */
	public double getTotalPercentage(){
		Double totalVolume = new Double(0.0);
		Double alcoholicVolume = new Double(0.0);
		
		// Add up drink volume and drink percentages to get total percentage
		for (int i = 0; i < drinkList.size(); i++){
			alcoholicVolume += (drinkVolumeList.get(i) 
			* drinkList.get(i).getDrinkAlcoholPercentage()) / 100.0;
			
			totalVolume += drinkVolumeList.get(i);
		}
		
		return (alcoholicVolume / totalVolume) * Const.percConst;
	}
	
	
	/**
	 * Returns Drink at the index of i in the Cocktail.
	 * 
	 * @param i
	 * @return Drink
	 */
	public Drink getDrink(int i){
		return drinkList.get(i);
	}
	
	/**
	 * Returns Drink volume at the index of i in the Cocktail.
	 * 
	 * @param i
	 * @return double volume of drink
	 */
	public double getVolume(int i){
		return drinkVolumeList.get(i);
	}

	/**
	 * Alters a Drink and adds a new Drink at the position specified position
	 * 
	 * @param i 	index value
	 * @param drink		New Drink
	 * @param doubleValue 	Volume of the drink
	 */
	public void setDrink(int i, Drink drink, double volume) {
		drinkList.set(i, drink);
		drinkVolumeList.set(i, volume);
	}
	
	/**
	 * Copy operator for Cocktail.
	 * 
	 * @param c 	The cocktail which values are to be copied
	 */
	public void copy(Cocktail c){
		// Remove any existing drinks
		for (int i = 0; i < getSize(); i++){
			removeDrink(0);
		}
		
		setCocktailName(c.getCocktailName());
		setComment(c.getComment());
		
		// Add new Drinks
		for (int i = 0; i < c.getSize(); i++){
			addDrink(c.getVolume(i), c.getDrink(i));
		}
	}

	/**
	 * Reset the name and drinks of the cocktail.
	 * 
	 */
	public void reset() {
		cocktailName = "";
		comment = "";
		resetDrinks();
	}
	
	/**
	 * Reset the just the drinks of the cocktail.
	 * 
	 */
	public void resetDrinks() {
		drinkList = new LinkedList<Drink>();
		drinkVolumeList = new LinkedList<Double>();
	}

}
