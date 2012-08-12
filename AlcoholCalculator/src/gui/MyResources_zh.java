package gui;

import java.util.Enumeration;
import java.util.ResourceBundle;

public class MyResources_zh extends ResourceBundle {
	  public Object handleGetObject(String key) {
		  if (key.equals("title")) return "鸡尾计算 v0.0.1 Beta";
	      return null;
	  }

	public Enumeration<String> getKeys() {
		// TODO Auto-generated method stub
		return null;
	}
}
