package se.kth.iv1350.saleProcess.model;

import se.kth.iv1350.saleProcess.integration.SaleDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a sale receipt.
 */
public class Receipt {
    private SaleDTO currentSaleDTO;

    /**
     * This method sends the sale information to the receipt
     * @param currentSaleDTO The DTO object of the current <code>Sale</code>
     */
    void sendSaleToReceipt(SaleDTO currentSaleDTO){
        this.currentSaleDTO = currentSaleDTO;
    }

    /**
     * Creates a formatted string representation of <code>Receipt</code>
     * @return String representation of <code>Receipt</code>
     */
    public String receiptToString(){
        StringBuilder receiptToString = new StringBuilder("********* RECEIPT ************\n");
        receiptToString.append("Store name: FoodLand\n");
        receiptToString.append("Store address: Kantarellstigen 34, 13411\n" );
        receiptToString.append("Sale time and date: ");
        receiptToString.append(currentSaleDTO.getSaleTime());
        receiptToString.append("\n\n");
        receiptToString.append("BOUGHT ITEMS: \n\n");

        for (Item item : currentSaleDTO.getItemList()){
            receiptToString.append(item);
            receiptToString.append("************\n");
        }

        receiptToString.append("\n");
        receiptToString.append("Total price: ");
        receiptToString.append(currentSaleDTO.getPaymentInformation().getRunningTotal());
        receiptToString.append(" SEK\n");
        receiptToString.append("Total VAT: ");
        receiptToString.append(currentSaleDTO.getTotalVAT());
        receiptToString.append(" SEK\n");
        receiptToString.append("Discount: ");
        receiptToString.append(currentSaleDTO.getDiscount().getDiscountAmount());
        receiptToString.append(" SEK\n");
        receiptToString.append("Cash payment: ");
        receiptToString.append(currentSaleDTO.getPaymentInformation().getPaidAmount());
        receiptToString.append(" SEK\n");
        receiptToString.append("Change: ");
        receiptToString.append(currentSaleDTO.getPaymentInformation().getChange());
        receiptToString.append(" SEK\n\n");
        receiptToString.append("Thank you for your purchase!\n");
        receiptToString.append("*****************************");
        return receiptToString.toString();

    }

}
