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

import backEnd.Cocktail;
import backEnd.Cocktails;

public class LoadCocktails {
	Cocktails cocktails;
	String filename;
	public LoadCocktails(ResourceBundle resourceBundle, String filename){
		this.filename = filename;
		cocktails = new Cocktails();
		
		this.filename = filename;
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Cocktails.class);
			
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			cocktails = (Cocktails) jaxbUnmarshaller.unmarshal(new File(filename));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
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
			s[i] = cocktails.getCocktailName(i);
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
        try {
        	JAXBContext jc = JAXBContext.newInstance(Cocktails.class);
    		Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(cocktails, new FileOutputStream(this.filename));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
