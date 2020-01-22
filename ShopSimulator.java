import java.io.*;
/**
 * This program tries to simulate a shop, by implementing the principles of Object Oriented Design in a basic manner.
 * For this, we have three classes
 * <li>
 * <ul> A Goods class that represents the items in the shop</ul>
 * <ul> A shop class that simulates the shop, with the full understanding that a shop has a list of goods as stock</ul>
 * <ul>A ShopSimulator class that contains the shop, integrating the other classes.
 * </li>
 * When the main(String) method is run, it reads the contents of the essentials.text file that stores the stock of goods
 * into memory. This is important to help the program check in real time that new additions are in fact, increments to
 * the quantity of a particular good that already exist and then handle that appropriately. 
 * This means for example, that if the good Sugar already exists in the catalog with quantity 5 units, user input that
 * seeks to add more sugar at the same price by 6 units simply effects in a quantity increase of the previous sugar record.
 * @author Lemfon Karl and Ziggy Jesse-Jackson
 */
import java.util.ArrayList;
import java.util.Scanner;
public class ShopSimulator {
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		ShopSimulator temp = new ShopSimulator();
		Scanner input = new Scanner(System.in);
		Shop ourShop = new Shop("Essentials", "Fifii");
		ourShop.setStock(temp.readFileInfo("essentials_stock.txt"));
		int loopEnabler = 0;

		// PHASE ONE: Capturing input from the user
		try {
		while(loopEnabler < 10) {
			System.out.println("Please enter the output in the following format (Item Name Quantity Price) e.g, Rice 7 GHC 10:");
			String argName = "";
			while(!input.hasNextInt()) {
				argName += input.next() + " ";
			}	
			int argQuant = Integer.parseInt(input.next());
			String cur = input.next();
			double argPrice = Double.parseDouble(input.next());
			Goods element = new Goods(argName, argQuant, argPrice);
			ourShop.addToStock(element);
			loopEnabler++;
		 }
		} catch(RuntimeException e) {
			System.out.println("Please run the program again, ensuring that you are entering the information with the correct format.");
		}
		
		
		// PHASE 2: Writing the elements in the stock to a text file.
		String textToWrite = "";
		for(Goods e:ourShop.getStock()) {
			if(ourShop.getStock().lastIndexOf(e) != ourShop.getStock().size() - 1) {
				textToWrite +=  e.getName() + "    " +  e.getTotalQuantity() + "    " + " GHC  "  + e.getPrice()  + "\n";
			}
			else {
				textToWrite +=  e.getName() + "    " +  e.getTotalQuantity() + "    " + " GHC  "  + e.getPrice();
			}
		}
		temp.writingTextToFile("essentials_stock.txt", textToWrite);
		
		// PHASE 3: Backing the information up in another file
		String textToWrite2 = "";
		for(Goods e:ourShop.getStock()) {
			if(ourShop.getStock().lastIndexOf(e) != ourShop.getStock().size() - 1) {
				textToWrite2 +=  e.getName() + "    " +  e.getTotalQuantity() + "    " + " GHC  "  + e.getPrice()  + "\n";
			}
			else {
				textToWrite2 +=  e.getName() + "    " +  e.getTotalQuantity() + "    " + " GHC  "  + e.getPrice();
			}
		}
		temp.writingTextToFile("backup_essentials_stock.txt", textToWrite2);
		
		// PHASE 4: Printing out the information from the file
		try {
			ArrayList<Goods> items = temp.readFileInfo("essentials_stock.txt");
			for(Goods e:items) {
				System.out.println(e.getName() + "  " + e.getTotalQuantity() + "  " + " GHC " + e.getPrice());
			}
		} catch (FileNotFoundException e1) {
			System.out.println("File not found");
		}
		input.close();
	}
	
	/**
	 * Prints to the record file. 
	 * @param fileName The name of the file that has the records
	 * @param Name  The name of the good
	 * @param price The price of the good
	 * @param quantity The quantity or units of the good.
	 */
    public void writingTextToFile(String fileName, String Name, double price, int quantity) {
		    
		PrintWriter printWriter = null;
		
		try {
			//Note that we are able to append to the file because of the "true" parameter
			printWriter = new PrintWriter(new FileOutputStream(fileName));
		}catch(FileNotFoundException fnfe) {
			fnfe.getMessage();
		}
		
		    printWriter.printf(Name + "  " +  quantity +  "  " + "GHC " + price);
		    printWriter.println();
		  
		    //Close Writer
		    printWriter.close();
	}
    
    /**
     * writes the information about the goods to the file that stores the record.
     * 
     * @param fileName The name of the file that should store the stock information
     * @param arg The String to be written to the file, this string has to already be properly formatted to render the 
     * 				output properly.
     */
    
    public void writingTextToFile(String fileName, String arg) {
    	PrintWriter printWriter = null;
		
		try {
			//Note that we are able to append to the file because of the "true" parameter
			printWriter = new PrintWriter(new FileOutputStream(fileName));
		}catch(FileNotFoundException fnfe) {
			fnfe.getMessage();
		}
		
		    printWriter.println(arg);
		    
		  
		    //Close Writer
		    printWriter.close();
    	
    }
    
    /**
     * Reads the stock information from a file, and returns a list of Goods objects. Since each line represents information 
     * about a given good, the processing is done line by line, creating Goods objects with the information and then 
     * apppending then to the list to be returned.
     * 
     * @param fileName The name of the file that stores the stock data.
     * @return ArrayList<Goods> A list of good objects as constructed from the information in the stock file.
     * @throws NumberFormatException
     * @throws IOException
     */
    public ArrayList<Goods> readFileInfo(String fileName) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new FileReader(fileName));
    	ArrayList<Goods> arrayFromFile = new ArrayList<Goods>();
    	try {
    		String line;
    		while((line = br.readLine()) != null) {
	    		Scanner tempScanner = new Scanner(line.stripTrailing());
	    		String argName = "";
	    		/* 
	    		 * Going through the tokens to ensure that we don't have errors when the name of the good is more than one line long
	    		 * An entry such as;
	    		 * Baked beans 34 GHC 50, should work as well as one line Sugar 45 GHC 10.
	    		*/
				while(!tempScanner.hasNextInt()) {
					argName += tempScanner.next() + " ";
				}	
				int argQuant = Integer.parseInt(tempScanner.next());
				String cur = tempScanner.next(); // Reads the GHC string, but does nothing with it.
				double argPrice = Double.parseDouble(tempScanner.next());
				Goods element = new Goods(argName, argQuant, argPrice);
				arrayFromFile.add(element);
				tempScanner.close();
    		}
    	} finally {
    		br.close();
    	}
    	
    	return arrayFromFile;
    }
}
