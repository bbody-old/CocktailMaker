package fileIO;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Locale;
import java.util.Properties;
import java.util.prefs.BackingStoreException;

import resources.LocaleHandler;

public class PreferenceHandler {
	Properties prefs;
	static String defaultFontName = "Tahoma";
	static int defaultFontSize = 18;
	static int defaultFontType = Font.PLAIN;
	
	static String preferencesFilename = "preferences.config";
	
	static String defaultDrinksFilename = "drink-list.drinks";
	static String defaultCocktailsFilename = "cocktail-list.cocktails";
	
	public PreferenceHandler(){
		try {	
			// Check if there is a config file already
			if (!new File(preferencesFilename).exists()){
				createConfigFile();
				save();
			} else {
				loadPreferences();
			}
		} catch (BackingStoreException bse) {
			System.out.println("Problem with accessing the backing store\n"
					+ bse);
			bse.printStackTrace();
		}
	}
	private void loadPreferences(){
		prefs = new Properties();
	    FileInputStream fis;
		try {
			fis = new FileInputStream(preferencesFilename);
			prefs.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPropertiesFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void createConfigFile(){
		prefs = new Properties();
		prefs.setProperty("fontname", defaultFontName);
		prefs.setProperty("fontsize", new Integer(defaultFontSize).toString());
		
		prefs.setProperty("language", LocaleHandler.getStringRepresentation(getLocale()));
		prefs.setProperty("cocktailsFilename", defaultCocktailsFilename);
		prefs.setProperty("drinksFilename", defaultDrinksFilename);
		
	}

	public void save()
			throws BackingStoreException {
		try {
			FileOutputStream fos = new FileOutputStream(preferencesFilename);

			prefs.store(fos, "Preferences");
			fos.close();
		} catch (IOException ioe) {
			System.out.println("IOException in exportToFile\n" + ioe);
			ioe.printStackTrace();
		}
	}
	
	public Font getFont(){
		String fontName = prefs.getProperty("fontname", defaultFontName);
		int fontSize = Integer.parseInt(prefs.getProperty("fontsize", new Integer(defaultFontSize).toString()));
		return new Font(fontName, Font.PLAIN, fontSize);
	}
	
	public void setFontName(String fontName){
		prefs.setProperty("fontname", fontName);
	}
	
	public void setFontSize(String size){
		prefs.setProperty("fontsize", size);
	}
	
	public Locale getLocale(){
		String selectedLanguage = prefs.getProperty("language", "System"); 
		if (!selectedLanguage.equals("System")){
			LocaleHandler l = new LocaleHandler(selectedLanguage);
			return l.getSelectedLocale();
		} else {
			Locale defaultLocale = Locale.getDefault();
			if (LocaleHandler.hasLocale(defaultLocale)){
				return defaultLocale;
			}
			return new LocaleHandler().getSelectedLocale();
		}
	}
	
	public String getLocaleName(){
		String selectedLanguage = prefs.getProperty("language", "System"); 
		if (!selectedLanguage.equals("System")){
			LocaleHandler l = new LocaleHandler(selectedLanguage);
			return l.getSelectedLocaleString();
		} else {
			Locale defaultLocale = Locale.getDefault();
			if (LocaleHandler.hasLocale(defaultLocale)){
				return LocaleHandler.getStringRepresentation(defaultLocale);
			}
			return LocaleHandler.getStringRepresentation(new LocaleHandler().getSelectedLocale());
		}
	}
	
	public void setLocaleName(String locale){
		prefs.setProperty("language", locale);
	}
	
	public String getCocktailsFilename() {
		return prefs.getProperty("cocktailsFilename", defaultCocktailsFilename);
	}
	
	public void setCocktailsFilename(String filename){
		prefs.setProperty("cocktailsFilename", filename);
	}
	
	public String getDrinksFilename(){
		return prefs.getProperty("drinksFilename", defaultDrinksFilename);
	}
	
	public void setDrinksFilename(String filename){
		prefs.setProperty("drinksFilename", filename);
	}
}