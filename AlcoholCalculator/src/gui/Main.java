package gui;

import java.awt.EventQueue;
import java.util.Locale;
import java.util.ResourceBundle;

import backEnd.Cocktail;
import fileIO.LoadCocktails;
import fileIO.LoadDrinks;
import fileIO.PreferenceHandler;
public class Main {
	/**
	 * Step in point for the Main program.
	 * @param args
	 */
	public static void main(String[] args) {
		
		// TODO: Dialog box to get different files (On First Run)
		final PreferenceHandler pf = new PreferenceHandler();
		final String iconFileName = "icon.png";
		
		// TODO: Choose Language Dialog
		
		// Set up GUI
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Global", pf.getLocale());
					// Load cocktails and drinks for use in the program
					LoadCocktails lc = new LoadCocktails(resourceBundle, pf.getCocktailsFilename());
					LoadDrinks ld = new LoadDrinks(resourceBundle, pf.getDrinksFilename());
					
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
