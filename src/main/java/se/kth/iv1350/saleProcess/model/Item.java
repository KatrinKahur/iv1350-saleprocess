package se.kth.iv1350.saleProcess.model;

import se.kth.iv1350.saleProcess.integration.ItemDTO;

/**
 * This class represents a grocery item.
 */
public class Item {
    private final Amount price;
    private final double VAT;
    private final String name;
    private final int barcode;
    private int quantity;

    /**
     * Creates an instance of <code>Item</code>
     * @param foundItem The <code>ItemDTO</code> fetched from the inventory
     */
    Item (ItemDTO foundItem){
        this.price = foundItem.getPrice();
        this.VAT = foundItem.getVAT();
        this.name = foundItem.getName();
        this.barcode = foundItem.getBarcode();
        this.quantity = 1;
    }

    /**
     * This method gets the total price of the item, including VAT
     * @return Item price incl. VAT
     */
    Amount getPriceWithVAT(){
        return getVATConvertedIntoAmount().plus(price);
    }

    /**
     * This method increases item quantity by 1
     */
    void increaseQuantity(){
        quantity++;
    }

    /**
     * This method gets the value of quantity
     * @return The value of quantity
     */
    int getQuantity(){
        return quantity;
    }

    /**
     * This method gets the value of barcode
     * @return The value of barcode
     */
    int getBarcode(){
        return barcode;
    }

    /**
     * This method gets the value of VAT
     * @return The value of VAT
     */
    double getVAT(){
        return VAT;
    }

    /**
     * This method is a string representation of <code>Item</code>
     * @return String with item name, price and VAT
     */
    @Override
    public String toString(){
        return "Item name: " + name + "\n" +
                "Item price: " + price + " SEK\n" +
                "Item quantity: " + quantity + "\n" +
                "VAT: " + VAT + " %\n";
    }

    /**
     * This class compares two instances of <code>Item</code> to determine whether they are equal.
     * @param other The specified <code>Item</code> that is compared to this <code>Item</code>
     * @return Returns <code>true</code> if <code>Item</code>s are equal, <code>false</code> if they are not.
     */
    public boolean equals(Object other){
        if (other == null)
            return false;

        if (!(other instanceof Item))
            return false;

        Item comparedItem = (Item) other;
        return barcode == comparedItem.getBarcode();
    }

    /**
     * This method gets the items VAT calculated to its corresponding <code>Amount</code>
     * @return The value of VAT calculated to its corresponding <code>Amount</code>
     */
    Amount getVATConvertedIntoAmount(){
        return new Amount(VAT/100).multiply(price);
    }
}
