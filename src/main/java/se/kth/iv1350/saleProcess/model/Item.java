package se.kth.iv1350.saleProcess.model;

import se.kth.iv1350.saleProcess.integration.ItemDTO;
import se.kth.iv1350.saleProcess.integration.ItemIdentifier;

/**
 * This class represents a grocery item.
 */
public class Item {
    private final Amount price;
    private final double VAT;
    private final String name;
    private final int barcode;
    private int quantity;
    private Amount priceWithVAT;

    /**
     * Creates an instance of <code>Item</code>
     * @param foundItem The <code>ItemDTO</code> fetched from <code>Inventory</code>
     */
    Item (ItemDTO foundItem){
        this.price = foundItem.getPrice();
        this.VAT = foundItem.getVAT();
        this.name = foundItem.getName();
        this.barcode = foundItem.getBarcode();
        this.quantity = 1;
    }

    /**
     * This method adds <code>VAT</code> to <code>price</code>
     */
    void calculatePriceWithVAT(){
        priceWithVAT = (convertVATPercentageIntoAmount()).plus(price);
    }

    /**
     * This method converts the VAT rate into <code>Amount</code>
     * @return <code>Amount</code> of the items VAT rate regarding to its price
     */
    private Amount convertVATPercentageIntoAmount(){
        return new Amount(VAT/100).multiply(price);
    }

    /**
     * This method gets the value of <code>priceWithVAT</code>
     * @return The value of <code>priceWithVAT</code>
     */
    public Amount getPriceWithVAT(){
        return priceWithVAT;
    }

    /**
     * This method updates increases <code>quantity</code> by 1
     */
    void increaseQuantity(){
        quantity++;
    }

    /**
     * This method sets the item quantity
     * @param quantity Quantity of the item
     */
    void setQuantity(int quantity){
        this.quantity = quantity;
    }

    /**
     * This method gets the value of <code>quantity</code>
     * @return The value of <code>quantity</code>
     */
    int getQuantity(){
        return quantity;
    }

    /**
     * This method gets the value of <code>barcode</code>
     * @return The value of <code>barcode</code>
     */
    int getBarcode(){
        return barcode;
    }

    /**
     * This method gets the value of <code>VAT</code>
     * @return The value of <code>VAT</code>
     */
    double getVAT(){
        return VAT;
    }

    /**
     * This method gets the value of <code>price</code>
     * @return The value of <code>price</code>
     */
    Amount getPrice(){
        return price;
    }

    /**
     * This method gets the name of the item.
     * @return The name of the item
     */
    String getName(){
        return name;
    }

    /**
     * This method is a <code>String</code> representation of Item name, price and VAT
     * @return <code>String</code> with item name, price and VAT
     */
    @Override
    public String toString(){
        return "Item name: " + name + "\n" + "Item price: " + price + " SEK\n" + "VAT: " + VAT + " %\n";
    }
}
