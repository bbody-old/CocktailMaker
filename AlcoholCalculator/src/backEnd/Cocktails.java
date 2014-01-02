package backEnd;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Cocktails {
	@XmlElement(name="cocktail")
	public List<Cocktail> cocktails;
	public Cocktails(){
		cocktails = new ArrayList();
	}

	public int size() {
		return cocktails.size();
	}

	public Cocktail[] toArray() {
		Cocktail[] c = new Cocktail[cocktails.size()];
		for (int i = 0; i < cocktails.size(); i++){
			c[i] = cocktails.get(i);
		}
		return c;
	}

	public void remove(int i) {
		cocktails.remove(i);
	}
	
	public Cocktail get(int i) {
		return cocktails.get(i);
	}

	public String getCocktailName(int i) {
		// TODO Auto-generated method stub
		return cocktails.get(i).getCocktailName();
	}

	public void add(Cocktail c) {
		cocktails.add(c);		
	}

	public void set(int index, Cocktail c) {
		cocktails.set(index, c);
	}
}
