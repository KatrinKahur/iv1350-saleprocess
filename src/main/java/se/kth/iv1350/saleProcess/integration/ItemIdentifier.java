package se.kth.iv1350.saleProcess.integration;

/**
 * This class represents an item identifier of <code>Item</code>.
 */
public class ItemIdentifier {
    private final int barcode;

    /**
     * Creates an instance of <code>ItemIdentifier</code>
     * @param barCode The identification code of an <code>Item</code>
     */
    public ItemIdentifier(int barCode){
        this.barcode = barCode;
    }

    /**
     * This class gets the value of <code>barCode</code>
     * @return The value of <code>barCode</code>
     */
    public int getBarcode(){
        return barcode;
    }


}
