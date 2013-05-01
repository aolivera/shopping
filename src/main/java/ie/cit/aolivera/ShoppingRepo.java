package ie.cit.aolivera;

import java.util.List;
import java.util.ArrayList;

public class ShoppingRepo {

	private List<Shopping> shoppings = new ArrayList<Shopping>();

	public ShoppingRepo(){
		Shopping shopping1 = new Shopping();
		shopping1.setShop("Tesco");
		shopping1.setProduct("milk");
		shopping1.setPrice(123);
		shopping1.setBestPrice(true);
		shoppings.add(shopping1);		
		
		Shopping shopping2 = new Shopping();
		shopping2.setShop("Dunes Stores");
		shopping2.setProduct("bread");
		shopping2.setPrice(46);
		shopping2.setBestPrice(true);
		shoppings.add(shopping2);
		
	}
	
	public void addShopping (Shopping shopping) {
		shoppings.add(shopping);
	}
	
	public List<Shopping> getShoppings() {
		return shoppings;
	}
}
