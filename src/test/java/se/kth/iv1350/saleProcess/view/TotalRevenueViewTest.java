package se.kth.iv1350.saleProcess.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.saleProcess.integration.CatalogCreator;
import se.kth.iv1350.saleProcess.integration.ItemDTO;
import se.kth.iv1350.saleProcess.integration.ItemIdentifier;
import se.kth.iv1350.saleProcess.model.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class TotalRevenueViewTest {
    ByteArrayOutputStream outputContent;
    PrintStream originalSystemOut;
    private CatalogCreator creator;

    @BeforeEach
    void setUp(){
        outputContent = new ByteArrayOutputStream();
        originalSystemOut = System.out;
        System.setOut(new PrintStream(outputContent));
        creator = new CatalogCreator();
    }

    @AfterEach
    void tearDown() {
        outputContent = null;
        System.setOut(originalSystemOut);
        creator = null;
    }

    @Test
    void testTotalRevenueOutputToSystemOut() {
        Sale sale = new Sale();
        SaleObserver observer = new TotalRevenueView();
        sale.addSaleObserver(observer);
        CashRegister cashRegister = new CashRegister();
        ItemIdentifier identifier = new ItemIdentifier(9);
        ItemDTO item = null;
        try{
            item = creator.getInventory().searchItemByBarcode(identifier);
        }
        catch (Exception exc){
            fail("Failed to fetch the item from the inventory. An exception was thrown.");
            exc.printStackTrace();
        }
        sale.registerItem(item);
        Amount cashPayment = new Amount(100);
        sale.registerPayment(cashRegister, cashPayment);
        Amount itemVATDividedByHundred = new Amount(item.getVAT()/100);
        Amount itemVATAmount = itemVATDividedByHundred.multiply(item.getPrice());
        Amount itemPrice = item.getPrice().plus(itemVATAmount);
        String result = outputContent.toString();
        assertTrue(result.contains("The total revenue is: " + itemPrice.toString()), "Incorrect output describing the total revenue.");
    }
}