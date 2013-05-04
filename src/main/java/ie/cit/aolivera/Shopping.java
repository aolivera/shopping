package ie.cit.aolivera;

import java.util.UUID;

public class Shopping {
	private String id;
	private String shop;
	private String product;
	private Integer price;
	private boolean bestPrice;

	public Shopping() {
		id = UUID.randomUUID().toString();
	}

	public String getShop() {
		return shop;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public boolean isBestPrice() {
		return bestPrice;
	}

	public void setBestPrice(boolean bestPrice) {
		this.bestPrice = bestPrice;
	}

}
