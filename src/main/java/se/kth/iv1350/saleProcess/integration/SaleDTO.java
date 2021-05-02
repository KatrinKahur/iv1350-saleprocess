package se.kth.iv1350.saleProcess.integration;

import se.kth.iv1350.saleProcess.model.Amount;
import se.kth.iv1350.saleProcess.model.Item;
import se.kth.iv1350.saleProcess.model.Sale;

public class SaleDTO {

    private final Amount runningTotal;
    private final Item recentlyScannedItem;

    /**
     * Creates an instance of <code>SaleDTO</code>
     * @param currentSale Used to get <code>runningTotal</code> of <code>currentSale</code> and the recently scanned <code>Item</code>
     */
    public SaleDTO(Sale currentSale){
        runningTotal = currentSale.getRunningTotal();
        recentlyScannedItem = currentSale.getRecentlyScannedItem();
    }

    /**
     * Creates an instance of <code>SaleDTO</code>
     * @param runningTotal The running total of a sale
     * @param recentlyScannedItem The most recently scanned item
     */
    public SaleDTO(Amount runningTotal, Item recentlyScannedItem){
        this.runningTotal = runningTotal;
        this.recentlyScannedItem = recentlyScannedItem;
    }

    /**
     * This method gets the value of <code>runningTotal</code>
     * @return The value of <code>runningTotal</code>
     */
    private Amount getRunningTotal(){
        return runningTotal;
    }

    /**
     * This method gets the value of the most recently scanned item
     * @return The value of <code>recentlyScannedItem</code>
     */
    private Item getRecentlyScannedItem(){
        return recentlyScannedItem;
    }

    @Override
    public String toString(){
        String itemDescription = recentlyScannedItem.toString();
        String runningTot = runningTotal.toString();
        return itemDescription + "Running total: " + runningTot + " SEK\n";
    }

    public boolean equals(Object other){
        if(other == null || !(other instanceof SaleDTO))
            return false;
        SaleDTO objToCompare = (SaleDTO) other;
        if(!runningTotal.equals(objToCompare.getRunningTotal()))
            return false;
        if(!recentlyScannedItem.equals(objToCompare.getRecentlyScannedItem()))
            return false;
        return true;
    }
}
