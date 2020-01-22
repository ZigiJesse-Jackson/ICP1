/*
 * This class models goods that can be found at a shop and is used in the Shop class and the ShomSimulator class.
 * @author Lemfon Karl and Jesse-Jackson Ziggy
 */

public class Goods {
	 private String name;
	 private int totalQuantity;
	 private double price;
 
	 public Goods() {
		 this.name = "Unidentified";
		 this.totalQuantity =  0;
		 this.price = 0.0;
	 }
	 
	 public Goods(String name, int quantity, double price) {
		 this.name = name;
		 this.totalQuantity = quantity;
		 this.price = price;
	 }
	 
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getTotalQuantity() {
		return totalQuantity;
	}
	
	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	 
	 
}
