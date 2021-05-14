package se.kth.iv1350.saleProcess.integration;

import se.kth.iv1350.saleProcess.model.Item;

/**
 * Thrown when the specified <code>ItemDTO</code> is not in the <code>Inventory</code>s item list.
 */
public class InvalidItemIdentifierException extends Exception{
    private ItemIdentifier invalidItemIdentifier;
    /**
     * Creates a new instance of <code>InvalidItemIdentifierException</code>.
     */
    public InvalidItemIdentifierException(ItemIdentifier invalidItemIdentifier){
        super("Item identifier does not exist.");
        this.invalidItemIdentifier = invalidItemIdentifier;
    }

    /**
     * Gets the value of <code>invalidItemIdentifier</code>
     * @return The value of <code>invalidItemIdentifier</code>
     */
    public ItemIdentifier getInvalidItemIdentifier(){
        return invalidItemIdentifier;
    }
}
