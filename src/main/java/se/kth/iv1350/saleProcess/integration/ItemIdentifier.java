package se.kth.iv1350.saleProcess.integration;

/**
 * This class represents an item identifier of <code>Item</code>.
 */
public class ItemIdentifier {
    private String barCode;
    private int quantity;

    /**
     * Creates an instance of <code>ItemIdentifier</code>
     * @param barCode The identification code of an <code>Item</code>
     * @param quantity The quantity of an <code>Item</code>
     */
    public ItemIdentifier(String barCode, int quantity){
        this.barCode = barCode;
        this.quantity = quantity;
    }

    /**
     * This class gets the value of <code>barCode</code>
     * @return The value of <code>barCode</code>
     */
    public String getBarCode(){
        return barCode;
    }

    /**
     * This class gets the value of <code>quantity</code>
     * @return The value of <code>quantity</code>
     */
    public int getQuantity(){
        return quantity;
    }

}
