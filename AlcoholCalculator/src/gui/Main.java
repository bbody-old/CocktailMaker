package gui;

import java.awt.EventQueue;
import java.util.ResourceBundle;
import fileIO.LoadCocktails;
import fileIO.LoadDrinks;
import fileIO.PreferenceHandler;

public class Main {
	
	static PreferenceHandler preferenceHandler;
	
	/**
	 * Start in point for the Main program.
	 * @param args
	 */
	public static void main(String[] args) {
		// Get preferences
		preferenceHandler = new PreferenceHandler();
		
		// Set up GUI
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Get string resources based on preferences.
					ResourceBundle resourceBundle = 
							ResourceBundle.getBundle("resources.Global", 
									preferenceHandler.getLocale());
					
					// Load cocktails and drinks for use in the program
					LoadCocktails loadCocktails = new LoadCocktails(
							resourceBundle, 
							preferenceHandler.getCocktailsFilename());
					LoadDrinks loadDrinks = new LoadDrinks(resourceBundle, 
							preferenceHandler.getDrinksFilename());
					
					// Start main window
					MainWindow window = new MainWindow(preferenceHandler, 
							loadCocktails, loadDrinks, resourceBundle);
					
					// Open frame
					window.getFrame().setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
