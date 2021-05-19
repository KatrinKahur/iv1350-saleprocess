package se.kth.iv1350.saleProcess.integration;

import se.kth.iv1350.saleProcess.model.Amount;
import se.kth.iv1350.saleProcess.model.Discount;
import se.kth.iv1350.saleProcess.model.Item;
import se.kth.iv1350.saleProcess.model.PaymentInformation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This class gathers information about the current sale that is returned to View.
 */
public final class SaleDTO {
    private final String saleTime;
    private final PaymentInformation paymentInformation;
    private final Item recentlyScannedItem;
    private List<Item> itemList = new ArrayList<>();
    private final Amount totalVAT;
    private final Discount discount;

    /**
     * Creates an instance of <code>SaleDTO</code>
     * @param saleTime The time of the sale.
     * @param recentlyScannedItem The most recently scanned item
     * @param itemList The list of scanned items
     * @param paymentInformation Contains information about payment
     * @param totalVAT The total VAT of the sale
     * @param discount Has the discount amount
     */
    public SaleDTO(String saleTime,
                   Item recentlyScannedItem,
                   List<Item> itemList,
                   PaymentInformation paymentInformation,
                   Amount totalVAT,
                   Discount discount){
        this.saleTime = saleTime;
        this.recentlyScannedItem = recentlyScannedItem;
        this.itemList = itemList;
        this.paymentInformation = paymentInformation;
        this.totalVAT = totalVAT;
        this.discount = discount;
    }

    /**
     *
     * @return Returns the <code>paymentInformation</code> object
     */
    public PaymentInformation getPaymentInformation(){
        return paymentInformation;
    }

    /**
     *
     * @return The value of <code>itemList</code>
     */
    public List<Item> getItemList(){
        return itemList;
    }

    /**
     *
     * @return The value of <code>recentlyScannedItem</code>
     */
    public Item getRecentlyScannedItem(){
        return recentlyScannedItem;
    }

    /**
     *
     * @return The value of <code>saleTime</code>
     */
    public String getSaleTime(){
        return saleTime;
    }

    /**
     *
     * @return The value of <code>totalVAT</code>
     */
    public Amount getTotalVAT(){
        return totalVAT;
    }

    /**
     *
     * @return The value of <code>discount</code>
     */
    public Discount getDiscount(){
        return discount;
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
        saleDTOToString.append(paymentInformation.getRunningTotal());
        saleDTOToString.append(" SEK\n");
        return saleDTOToString.toString();
    }

}
