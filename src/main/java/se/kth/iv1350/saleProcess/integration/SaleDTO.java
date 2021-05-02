package se.kth.iv1350.saleProcess.integration;

import se.kth.iv1350.saleProcess.model.Amount;
import se.kth.iv1350.saleProcess.model.Item;
import se.kth.iv1350.saleProcess.model.Sale;

public class SaleDTO {

    private final Amount runningTotal;
    private final Item itemDescription;

    /**
     * Creates an instance of <code>SaleDTO</code>
     * @param currentSale Used to get <code>runningTotal</code> of <code>currentSale</code> and the recently scanned <code>Item</code>
     */
    public SaleDTO(Sale currentSale){
        runningTotal = currentSale.getRunningTotal();
        itemDescription = currentSale.getRecentlyScannedItem();
    }

    @Override
    public String toString(){
        String itemDescr = itemDescription.toString();
        String runningTot = runningTotal.toString();
        return itemDescr + "Running total: " + runningTot + " SEK\n";
    }
}
