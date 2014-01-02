package backEnd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class StoredDrink{
	private Drink drink;
	private Double volume;
	public StoredDrink(){
		drink = new Drink();
		volume = new Double(0.0);
	}
	
	public StoredDrink(Drink drink, Double volume){
		this.drink = drink;
		this.volume = volume;
	}

	public StoredDrink(Drink drink) {
		this.drink = drink;
		this.volume = new Double(0.0);
	}

	public boolean drinkEquals(Drink d) {
		return drink.equals(d);
	}

	public Drink getDrink() {
		return drink;
	}

	public void setDrink(Drink drink) {
		this.drink = drink;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}
}