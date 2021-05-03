package se.kth.iv1350.saleProcess.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This class represents a sale receipt.
 */
public class Receipt {
    private LocalDateTime saleTime;
    private final String storeName = "FoodLand";
    private final String storeAddress = "Kantarellstigen 34, 13411";
    private Amount cashPayment;
    private Amount change;
    private List<Item> itemList;
    private Amount totalPrice;

    /**
     * Creates a new instance of <code>Receipt</code>
     * @param saleTime The time of the current sale
     */
    Receipt (LocalDateTime saleTime){
        this.saleTime = saleTime;
    }

    void sendSaleToReceipt(Sale currentSale, Amount cashPayment, Amount change){
        this.itemList = currentSale.getListOfItems();
        this.totalPrice = currentSale.getTotalPrice();
        this.cashPayment = cashPayment;
        this.change = change;
    }

    /**
     * Creates a <code>String</code> representation of <code>Receipt</code>
     * @return <code>String</code> representation of <code>Receipt</code>
     */
    @Override
    public String toString(){
        StringBuilder receiptToString = new StringBuilder("Receipt \n");
        receiptToString.append("Store name: ");
        receiptToString.append(storeName);
        receiptToString.append("\n");
        receiptToString.append("Store address: " );
        receiptToString.append(storeAddress);
        receiptToString.append("\n\n");
        receiptToString.append("Bought items: \n\n");

        for (Item item : itemList){
            receiptToString.append(item);
            receiptToString.append("************\n");
        }

        receiptToString.append("\n");
        receiptToString.append("Total price: ");
        receiptToString.append(totalPrice);
        receiptToString.append("SEK\n");
        receiptToString.append("Cash payment: ");
        receiptToString.append(cashPayment);
        receiptToString.append(" SEK\n");
        receiptToString.append("Change: ");
        receiptToString.append(change);
        receiptToString.append("SEK\n");

        return receiptToString.toString();

    }
}
