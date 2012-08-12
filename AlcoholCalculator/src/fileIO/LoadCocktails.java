package fileIO;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import backEnd.Cocktail;
import backEnd.Drink;

public class LoadCocktails {
	ArrayList<Cocktail> cocktails;
	String fileName;
	public LoadCocktails(String fileName){
		this.fileName = fileName;
		cocktails = new ArrayList<Cocktail>();
		try {
			 
			File fXmlFile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
	 
			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName(Const.element);
			//System.out.println("-----------------------");
	 
			for (int temp = 0; temp < nList.getLength(); temp++) {
	 
			   Node nNode = nList.item(temp);
			   if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	 
			      Element eElement = (Element) nNode;
			      
			      
		         // System.out.println("Nick Name : " + getTagValue("nickname", eElement));
			      //System.out.println("Salary : " + getTagValue("salary", eElement));
			      String name = getTagValue(Const.cocktailName, eElement);
			      //System.out.println(name);
			      String comment = getTagValue(Const.comment, eElement);
			      //System.out.println(comment);
			      Cocktail c = new Cocktail(name);
			      c.setComment(comment);
			      //System.out.println(c.toString());
			      NodeList dList = eElement.getElementsByTagName(Const.drinks);
			      
			     // System.out.println(name + "=" + dList.getLength());
			      for (int j = 0; j < dList.getLength(); j++){
			    	  Node dNode = dList.item(j);
			    	  Element de = (Element) dNode;
			    	  String amount = getTagValue(Const.amount, de);
				      //System.out.println(drinkName + ":" + drinkPerc + ":" +  amount);
			    	  //System.out.println(amount);
			    	  
			    	  //
			    	  NodeList drinksList = de.getElementsByTagName(Const.drink);
			    	  Node dnNode = drinksList.item(0);
			    	  Element dElement = (Element) dnNode;
			    	  String drinkName = getTagValue(Const.drinkName, dElement);
			    	  String drinkPerc = getTagValue(Const.percentage, dElement);
			    	  String drinkComment = getTagValue(Const.drinkComment, dElement);
			    	  //Element aElement = (Element) dList;
				      Drink d = new Drink();
				      d.setDrinkName(drinkName);
				      d.setDrinkAlcoholPercentage(Integer.parseInt(drinkPerc));
				      d.setComment(drinkComment);
				      //System.out.println("- " +d.getDrinkName());
				      c.addDrink(Double.parseDouble(amount), d);
				      //System.out.println(c.getSize());
			      }
			      
			      
			      cocktails.add(c);
			   }
			   
			}
		  } catch (Exception e) {
			e.printStackTrace();
		  }
	}
	
	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
	 
	        Node nValue = (Node) nlList.item(0);
	 
		return nValue.getNodeValue();
	  }
	 
	
	public Cocktail [] getCocktails(){
		return (Cocktail[]) cocktails.toArray();
	}
	public void removeCocktail(int i){
		// TODO: Make it right changes to file
		cocktails.remove(i);
	}
	
	public String [] getStrings(){
		String s[] = new String[cocktails.size()];
		for (int i = 0; i < cocktails.size(); i++){
			s[i] = cocktails.get(i).getCocktailName();
		}
		return s;
	}

	public Cocktail getCocktail(int selectedIndex) {
		// TODO Auto-generated method stub
		return cocktails.get(selectedIndex);
	}
	
	public void addCocktail(Cocktail c){
		/*
		Cocktail [] nw = new Cocktail[cocktails.size() + 1];
		for (int i = 0; i < cocktails.size(); i++){
			nw[i] = cocktails.get(i);
		}
		nw[cocktails.size()] = c;
		cocktails = nw;
		*/
		cocktails.add(c);
	}
	public void setCocktail(int index, Cocktail c){
		cocktails.set(index, c);
		//System.out.println(cocktails.get(index).toString());
	}
	public int size() {
		// TODO Auto-generated method stub
		return cocktails.size();
	}
	
	public void save(){
		// TODO: Write everything back to file
	}
}
