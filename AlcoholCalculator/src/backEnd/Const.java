package backEnd;

public class Const {
	// Generic
	static final public String newLine = "\n";
	static final public String numberFormat = "#.##";
	static final public String tab = "    ";
	static final public String tab2 = "\t";
	
	// Drink
	static final public double drinkDefaultPercent = 0.0;
	static final public String defaultComment = "";
	
	// Cocktail
	static final public String volume = "mL";
	static final public String  leftBracket = "(";
	static final public String  rightBracket = "%)";
	static final public String cocktailDefaultToStringColumns = "Drink Name" +
			"	| %		| Volume\n";// Shouldn't need localisation as is a developer tool
	
	static final public double percConst =  100.0;
	
	// HTML Tags
	static final public String htmlBR = "<BR>";
	static final public String htmlOpen = "<HTML>", htmlClose = "</HTML>";
	static final public String ulOpen = "<UL>", ulClose = "</UL>";
	static final public String liOpen = "<LI>", liClose = "</LI>";
}
