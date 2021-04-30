package se.kth.iv1350.saleProcess.model;

import se.kth.iv1350.saleProcess.integration.ItemDTO;
import se.kth.iv1350.saleProcess.integration.ItemIdentifier;

/**
 * This class represents a grocery item.
 */
public class Item {
    private Amount price;
    private int VAT;
    private String name;
    private int quantity;
    private String barcode;

    /**
     * Creates an instance of <code>Item</code>
     * @param foundItem The <code>ItemDTO</code> fetched from <code>Inventory</code>
     * @param identifier The <code>ItemIdentifier</code> entered by the cashier. It stores the item barcode and the quantity.
     */
    Item (ItemDTO foundItem, ItemIdentifier identifier){
        this.price = foundItem.getPrice();
        this.VAT = foundItem.getVAT();
        this.name = foundItem.getName();
        this.quantity = identifier.getQuantity();
        this.barcode = identifier.getBarCode();
    }
}
