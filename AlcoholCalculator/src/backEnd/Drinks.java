package backEnd;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Drinks {
	@XmlElement(name="drink")
	public List<Drink> drinks;
	
	public Drinks(){
		drinks = new ArrayList<Drink>();
	}

	public Drink get(int i) {
		// TODO Auto-generated method stub
		return drinks.get(i);
	}

	public Drink[] toArray() {
		// TODO Auto-generated method stub
		Drink [] ds = new Drink[drinks.size()];
		for (int i = 0; i < drinks.size(); i++){
			ds[i] = drinks.get(i);
		}
		return ds;
	}

	public int size() {
		// TODO Auto-generated method stub
		return drinks.size();
	}
}
