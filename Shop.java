import java.util.ArrayList;
public class Shop {
	private String shopName;
	private String vendorName;
	private ArrayList<Goods> stock;
	
	public Shop() {
		this.shopName = "ICP Class";
		this.vendorName = "Benjamin Kusi";
		this.stock = null;
	}
	
	public Shop(String shopName, String vendorName) {
		this.shopName = shopName;
		this.vendorName = vendorName;
		this.stock = new ArrayList<Goods>();
	}
	
	public Shop(String shopName, String vendorName, ArrayList<Goods> stock) {
		this.shopName = shopName;
		this.vendorName = vendorName;
		this.stock = stock;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public ArrayList<Goods> getStock() {
		return stock;
	}
	
	public void setStock(ArrayList<Goods> stock) {
		this.stock = stock;
	}
	
	public void addToStock(Goods...goods ) {
		for(Goods e:goods) {
			addToStock(e.getName(), e.getTotalQuantity(), e.getPrice());
		}
		
	}
	public void addToStock(String name, int quantity, double price) {
		for(Goods e:stock) {
			if(e.getName().toLowerCase().equals(name.toLowerCase()) && e.getPrice() == price) {
				e.setTotalQuantity(e.getTotalQuantity() + quantity);
				return;
			}
		}
		Goods temp = new Goods(name, quantity, price);
		this.stock.add(temp);
	
	}
}
