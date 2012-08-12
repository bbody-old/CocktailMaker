package gui;

import java.util.Enumeration;
import java.util.ResourceBundle;

//default (English language, United States)
public class MyResources extends ResourceBundle {
  public Object handleGetObject(String key) {
	  if (key.equals("title")) return "Cocktail Maker v0.0.1 Beta";
      return null;
  }

@Override
public Enumeration<String> getKeys() {
	// TODO Auto-generated method stub
	return null;
}
}

