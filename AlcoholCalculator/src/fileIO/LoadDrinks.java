package fileIO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import backEnd.Drink;
import backEnd.Drinks;


public class LoadDrinks {
	Drinks drinks;
	String filename;
	public LoadDrinks(ResourceBundle resourceBundle, String filename){
		this.filename = filename;
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Drinks.class);
			
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			drinks = (Drinks) jaxbUnmarshaller.unmarshal(new File(filename));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//this.save();

		/*
		drinks = new Drink[6];
		drinks[0] = new Drink("Frog");
		drinks[1] = new Drink("rum");
		drinks[2] = new Drink("Pijiu");
		drinks[3] = new Drink("beer");
		drinks[4] = new Drink("Baijiu");
		drinks[5] = new Drink("White wine");
		*/
		/*
		drinks = new ArrayList<Drink>();
		try {
			 
			File fXmlFile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
	 
			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName(Const.drink);
			//System.out.println("-----------------------");
			
			for (int temp = 0; temp < nList.getLength(); temp++) {
	 
			   Node nNode = nList.item(temp);
			   if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	 
			      Element eElement = (Element) nNode;
			      
			      
			      String name = getTagValue(Const.drinkName, eElement);
			      String percentage = getTagValue(Const.percentage, eElement);
			      String comment = getTagValue(Const.drinkComment, eElement);
			      Drink d = new Drink(resourceBundle.getString("drinkDefaultName"));
			      d.setDrinkName(name);
			      d.setDrinkAlcoholPercentage(Double.parseDouble(percentage));
			      d.setComment(comment);
			      drinks.add(d);
			      //System.out.println(d.getDrinkName() + " " + d.getDrinkAlcoholPercentage() + " " + d.getComment());
			   
			}
			}
		  } catch (Exception e) {
			e.printStackTrace();
		  }
		*/
	}
	
	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
	 
	        Node nValue = (Node) nlList.item(0);
	 
		return nValue.getNodeValue();
	  }
	
	public Drink [] getDrinks(){
		return (Drink[]) drinks.toArray();
	}
	
	public Drink getDrink(int i){
		return drinks.get(i);
	}
	
	public String [] getDrinksStrings(){
		String [] strArray = new String[drinks.size()];
		for (int i = 0; i < drinks.size(); i++){
			strArray[i] = drinks.get(i).getDrinkName();
		}
		return strArray;
	}
	
	public int getIndex(String dName){
		int index = -1;
		for (int j = 0; j < this.getSize(); j++){
			//System.out.println(dName + " = " + this.getDrink(j).getDrinkName());
			// TODO: better way to compare
			if (dName.equals(this.getDrink(j).getDrinkName())){
				index = j;
				break;
			}
		}
		return index;
	}
	
	public int getIndex(Drink d){
		int index = -1;
		for (int j = 0; j < this.getSize(); j++){
			//System.out.println(d.getDrinkName() + " = " + this.getDrink(j).getDrinkName());
			// TODO: better way to compare
			if (d.getDrinkName().equals(this.getDrink(j).getDrinkName())){
				index = j;
				break;
			}
		}
		return index;
	}
	
	private int smallest(int a, int b){
		if (a > b){
			return b;
		} else {
			return a;
		}
	}
	
	public String [] getDrinksStrings(int max){
		String [] strArray = new String[drinks.size()];
		for (int i = 0; i < smallest(drinks.size(), max); i++){
			strArray[i] = drinks.get(i).getDrinkName();
		}
		return strArray;
	}
	
	public int getSize(){
		return drinks.size();
	}
	
	public void save(){
		// Unused
        try {
        	JAXBContext jc = JAXBContext.newInstance(Drinks.class);
    		Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(drinks, new FileOutputStream(this.filename));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
