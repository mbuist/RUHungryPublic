package ruhungry;

/**
 * This file is the data portion of the StockNode
 * 
 * @author Kushi Sharma
 * @author Mary Buist
*/

public class Ingredient{

    private int ID; // ingredient ID number
    private String name; // name of ingredient
    private int stockLevel; // amount of stock
    private double priceToPurchase; // cost for restaurant to purchase ingredient

    // Constructor
    public Ingredient(int ID, String name, int amount, double price) {
        this.ID = ID;
        this.name = name;
        stockLevel = amount;
        priceToPurchase = price;
    }

    // "To Get" and "To Set" Methods
    public int getID() { return ID; }
    public void setID(int newID) { ID = newID; }

    public String getName() { return name; }
    public void setName(String newName) { name = newName; }

    public int getStockLevel() { return stockLevel; }
    public void setStockLevel(int newAmount) { stockLevel = newAmount; }
    public void updateStockLevel(int addAmount) { stockLevel += addAmount; }

    public double getPriceToPurchase() { return priceToPurchase; }
    public void setPriceToPurchase(int newPrice) { priceToPurchase = newPrice; }
}