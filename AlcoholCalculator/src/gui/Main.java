package gui;

import java.awt.EventQueue;

import backEnd.Cocktail;
import fileIO.LoadCocktails;
import fileIO.LoadDrinks;

public class Main {
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
				
					// Start main window
					MainWindow window = new MainWindow(lc, ld, iconFileName);
					window.getFrame().setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
