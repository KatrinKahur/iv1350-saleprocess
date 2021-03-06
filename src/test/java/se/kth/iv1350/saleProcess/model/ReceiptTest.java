package se.kth.iv1350.saleProcess.model;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.saleProcess.integration.ItemDTO;
import se.kth.iv1350.saleProcess.integration.ItemIdentifier;
import se.kth.iv1350.saleProcess.integration.SaleDTO;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptTest {

    @Test
    void testReceiptToString() {
        ItemDTO item = new ItemDTO("Orange juice", new Amount(19), 6, new ItemIdentifier(10));
        ItemDTO anotherItem = new ItemDTO("Tomato", new Amount(10), 6, new ItemIdentifier(9));
        Sale saleInstance = new Sale();
        saleInstance.registerItem(item);
        saleInstance.registerItem(anotherItem);
        CashRegister cashRegister = new CashRegister();
        Amount cashPayment = new Amount(100);
        saleInstance.registerPayment(cashRegister, cashPayment);
        Receipt receipt = new Receipt();
        receipt.sendSaleToReceipt(saleInstance.createSaleDTO());
        String expResult = new String("********* RECEIPT ************\n" + "Store name: FoodLand\n" +
                "Store address: Kantarellstigen 34, 13411\n" +
                "Sale time and date: " + saleInstance.createSaleDTO().getSaleTime() + "\n\n" + "BOUGHT ITEMS: \n\n" +
                "Item name: " + item.getName() + "\n" + "Item price: " + item.getPrice() + " SEK\n" +
                "Item quantity: " + 1 + "\n" + "VAT: " + item.getVAT() + " %\n"
                + "************\n" +
                "Item name: " + anotherItem.getName() + "\n" + "Item price: " + anotherItem.getPrice() + " SEK\n" +
                "Item quantity: " + 1 + "\n" +
                "VAT: " + anotherItem.getVAT() + " %\n" +
                "************\n" +
                "\nTotal price: " + saleInstance.getPaymentInformation().getRunningTotal() + " SEK\n" +
                "Total VAT: " + saleInstance.createSaleDTO().getTotalVAT() +
                " SEK\n" +
                "Discount: " + saleInstance.createSaleDTO().getDiscount().getDiscountAmount() + " SEK\n" +
                "Cash payment: " + cashPayment + " SEK\n" +
                "Change: " + saleInstance.getPaymentInformation().getChange() +
                " SEK\n\n" + "Thank you for your purchase!\n" + "*****************************"  );
        String result = receipt.receiptToString();
        assertEquals(expResult, result, "The printed strings do not have the same content.");
    }
}