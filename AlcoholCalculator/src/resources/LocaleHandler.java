package resources;

import java.util.Locale;


public class LocaleHandler {
	private static String [] stringList = {"American English",
		"British English",
		"Simplified Chinese",
		"Traditional Chinese"};
	
	private static Locale [] list = {Locale.US,
		Locale.UK,
		Locale.SIMPLIFIED_CHINESE,
		Locale.TRADITIONAL_CHINESE};
	
	private int indexSelected = 0;
	
	public static String[] getStringList(){
		return stringList;
	}
	
	public LocaleHandler (){
		indexSelected = 0;
	}
	
	public LocaleHandler (String themeName){
		for (int i = 0; i < stringList.length; i++){
			if (stringList[i].equals(themeName)){
				indexSelected = i;
			}
		}
	}
	
	public Locale getSelectedLocale(){
		
		return list[indexSelected];
	}
	
	public String getSelectedLocaleString(){
		return stringList[indexSelected];
	}
}
