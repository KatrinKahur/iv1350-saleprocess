package se.kth.iv1350.saleProcess.integration;

/**
 * This class represents an item identifier of a <code>Item</code>.
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
     * This method gets the value of barCode
     * @return The value of barCode
     */
    public int getBarcode(){
        return barcode;
    }


}
