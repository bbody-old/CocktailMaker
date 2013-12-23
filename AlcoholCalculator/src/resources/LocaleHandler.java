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

	public static boolean hasLocale(Locale defaultLocale) {
		// TODO Auto-generated method stub
		for (int i = 0; i < list.length; i++){
			if (list[i].equals(defaultLocale)){
				return true;
			}
		}
		return false;
	}
	
	public static String getStringRepresentation(Locale locale){
		for (int i = 0; i < list.length; i++){
			if (locale.equals(list[i])){
				return stringList[i];
			}
		}
		return "System";
	}
}
