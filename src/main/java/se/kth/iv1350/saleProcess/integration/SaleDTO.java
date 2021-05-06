package se.kth.iv1350.saleProcess.integration;

import se.kth.iv1350.saleProcess.model.Amount;
import se.kth.iv1350.saleProcess.model.Item;

/**
 * This class gathers information about the current sale that is returned to View.
 */
public class SaleDTO {

    private final Amount runningTotal;
    private final Item recentlyScannedItem;

    /**
     * Creates an instance of <code>SaleDTO</code>
     * @param runningTotal The running total of the current sale
     * @param recentlyScannedItem The most recently scanned item
     */
    public SaleDTO(Amount runningTotal, Item recentlyScannedItem){
        this.runningTotal = runningTotal;
        this.recentlyScannedItem = recentlyScannedItem;
    }

    /**
     * This method creates a formatted string with the information about running total and the recently scanned item.
     * @return Formatted string with the information about running total and the recently scanned item.
     */
    @Override
    public String toString(){
        StringBuilder saleDTOToString = new StringBuilder();
        saleDTOToString.append(recentlyScannedItem);
        saleDTOToString.append("Running total: ");
        saleDTOToString.append(runningTotal);
        saleDTOToString.append(" SEK\n");
        return saleDTOToString.toString();
    }

}
