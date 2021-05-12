package se.kth.iv1350.saleProcess.controller;

import org.intellij.lang.annotations.Identifier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.saleProcess.integration.*;
import se.kth.iv1350.saleProcess.model.Amount;
import se.kth.iv1350.saleProcess.model.CashRegister;
import se.kth.iv1350.saleProcess.model.Item;
import se.kth.iv1350.saleProcess.model.Sale;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private Controller controller;
    private CatalogCreator catalogCreator;
    private Inventory inventory;
    private Printer printer;
    ByteArrayOutputStream outputContent;
    PrintStream originalSystemOut;

    @BeforeEach
    void setUp() {
        outputContent = new ByteArrayOutputStream();
        originalSystemOut = System.out;
        System.setOut(new PrintStream(outputContent));
        catalogCreator = new CatalogCreator();
        printer = new Printer();
        controller = new Controller(catalogCreator);
        inventory = catalogCreator.getInventory();
    }

    @AfterEach
    void tearDown() {
        outputContent = null;
        System.setOut(originalSystemOut);
        catalogCreator = null;
        printer = null;
        controller = null;
        inventory = null;
    }

    @Test
    void testRegisterItem() {
        ItemIdentifier identifier = new ItemIdentifier(16);
        ItemDTO item = inventory.searchItemByBarcode(identifier);
        controller.startSale();
        SaleDTO saleInfo = controller.registerItem(identifier);
        assertTrue(saleInfo.toString().contains(item.getName()), "Item with barcode 16 is not registered in the sale.");
    }

    @Test
    void testEndSale() {
        ItemIdentifier identifier = new ItemIdentifier(9);
        ItemIdentifier anotherIdentifier = new ItemIdentifier(18);
        ItemDTO item = inventory.searchItemByBarcode(identifier);
        ItemDTO anotherItem = inventory.searchItemByBarcode(anotherIdentifier);
        Sale anotherSale = new Sale();
        anotherSale.registerItem(item);
        anotherSale.registerItem(anotherItem);
        anotherSale.calculateTotalPrice();
        Amount expResult = anotherSale.getTotalPrice();
        controller.startSale();
        controller.registerItem(identifier);
        controller.registerItem(anotherIdentifier);
        Amount result = controller.endSale();
        assertEquals(expResult, result, "The sale is not ended the way it should.");
    }

    @Test
    void testPay() {
        Sale sale = new Sale();
        CashRegister cashRegister = new CashRegister();
        ItemIdentifier identifier = new ItemIdentifier(9);
        ItemDTO item = inventory.searchItemByBarcode(identifier);
        sale.registerItem(item);
        sale.calculateTotalPrice();
        Amount cashPayment = new Amount(100);
        sale.registerPayment(cashRegister, cashPayment);

        controller.startSale();
        controller.registerItem(identifier);
        controller.endSale();
        controller.pay(cashPayment);
        String result = outputContent.toString();
        assertTrue(result.contains(cashPayment.toString()), "The payment has not been registered in the sale");
        assertTrue(result.contains(item.getName()), "The item has not been registered in the sale.");
    }
}