package se.kth.iv1350.saleProcess.integration;

import se.kth.iv1350.saleProcess.model.Amount;

/**
 * This class is used to transfer data of a grocery item
 */
public class ItemDTO {
    private final String name;
    private final Amount price;
    private final int VAT;
    private final String barcode;

    /**
     * This method creates an instance of <code>ItemDTO</code>
     * @param name The name of the item
     * @param price The price of the item
     * @param VAT The VAT rate of the item
     * @param identifier The <code>ItemIdentifier</code> of the item including the barcode and the item quantity
     */
    public ItemDTO(String name, Amount price, int VAT, ItemIdentifier identifier){
        this.name = name;
        this.price = price;
        this.VAT = VAT;
        this.barcode = identifier.getBarCode();
    }

    /**
     * This class gets the item name.
     * @return The value of <code>name</code>
     */
    public String getName(){
        return name;
    }

    /**
     * This class gets the item price.
     * @return The value of <code>price</code>
     */
    public Amount getPrice(){
        return price;
    }

    /**
     * This class gets the item VAT rate.
     * @return The value of <code>VAT</code>
     */
    public int getVAT(){
        return VAT;
    }

    /**
     * This class gets the item barcode.
     * @return The value of <code>barcode</code>
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * This class compares two instances of <code>ItemDTO</code> to determine whether they are equal.
     * @param other The specified <code>ItemDTO</code> that is compared to this <code>ItemDTO</code>
     * @return Returns <code>true</code> if <code>ItemDTO</code>s are equal, <code>false</code> if they are not.
     */
    public boolean equals(Object other){
       if (other == null || !(other instanceof ItemDTO))
           return false;

       ItemDTO comparedItem = (ItemDTO) other;

       if(!name.equals(comparedItem.getName()))
           return false;

       if(!price.equals(comparedItem.getPrice()))
           return false;

       if(VAT != comparedItem.getVAT())
           return false;

       if(!barcode.equals(comparedItem.getBarcode()))
           return false;

       return true;
    }

}
