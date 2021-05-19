package se.kth.iv1350.saleProcess.integration;

import se.kth.iv1350.saleProcess.model.Item;

/**
 * Thrown when item with the specified item identifier is not found in the inventory item list
 */
public class InvalidItemIdentifierException extends Exception{
    private final ItemIdentifier invalidItemIdentifier;
    /**
     * Creates a new instance of <code>InvalidItemIdentifierException</code>.
     */
    public InvalidItemIdentifierException(ItemIdentifier invalidItemIdentifier){
        super("Item identifier " + invalidItemIdentifier.getBarcode() + " does not exist.");
        this.invalidItemIdentifier = invalidItemIdentifier;
    }

    /**
     * @return The invalid item identifier
     */
    public ItemIdentifier getInvalidItemIdentifier(){
        return invalidItemIdentifier;
    }

}
