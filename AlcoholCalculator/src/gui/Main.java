package gui;

import java.awt.EventQueue;
import java.util.Locale;
import java.util.ResourceBundle;

import backEnd.Cocktail;
import fileIO.LoadCocktails;
import fileIO.LoadDrinks;
public class Main {
	/*
	private static ResourceBundle setupResourceBundles(){
		ResourceBundle enUS = ResourceBundle.getBundle("American English", new Locale("en", "US"));
		ResourceBundle enGB = ResourceBundle.getBundle("British English", Locale.UK);
		ResourceBundle zhS = ResourceBundle.getBundle("Simplified Chinese", Locale.SIMPLIFIED_CHINESE);
		ResourceBundle zhT = ResourceBundle.getBundle("Traditional Chinese", Locale.TRADITIONAL_CHINESE);
		
		return enUS;
	}*/
	/**
	 * Step in point for the Main program.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO: Dialog box to get different files (On First Run)
		final String cocktailsFileName = "cocktail-list.xml";
		final String drinksFileName = "drink-list.xml";
		final String iconFileName = "icon.png";
		
		// TODO: Choose Language Dialog
		
		// Set up GUI
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Load cocktails and drinks for use in the program
					LoadCocktails lc = new LoadCocktails(cocktailsFileName);
					LoadDrinks ld = new LoadDrinks(drinksFileName);
					Cocktail c = lc.getCocktail(0);
					//System.out.println(c.getSize());
					//DrinkSelector ds = new DrinkSelector(ld, c);
					//System.out.println(c.getSize());
					//ds.setVisible(true);
					ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Global", Locale.SIMPLIFIED_CHINESE);
					// Start main window
					MainWindow window = new MainWindow(lc, ld, iconFileName, resourceBundle);
					window.getFrame().setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
