package se.kth.iv1350.saleProcess.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.saleProcess.controller.Controller;
import se.kth.iv1350.saleProcess.integration.CatalogCreator;
import se.kth.iv1350.saleProcess.integration.Inventory;
import se.kth.iv1350.saleProcess.integration.ItemDTO;
import se.kth.iv1350.saleProcess.integration.ItemIdentifier;
import se.kth.iv1350.saleProcess.model.Amount;
import se.kth.iv1350.saleProcess.model.Sale;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ViewTest {
    ByteArrayOutputStream outputContent;
    PrintStream originalSystemOut;
    CatalogCreator creator;
    private Controller controller;
    private View view;
    private Sale sale;
    private Inventory inventory;

    @BeforeEach
    void setUp() {
        creator = new CatalogCreator();
        controller = new Controller(creator);
        view = new View(controller);
        sale = new Sale();
        inventory = creator.getInventory();
        outputContent = new ByteArrayOutputStream();
        originalSystemOut = System.out;
        System.setOut(new PrintStream(outputContent));
    }

    @AfterEach
    void tearDown() {
        creator = null;
        controller = null;
        view = null;
        sale = null;
        inventory = null;
        outputContent = null;
        System.setOut(originalSystemOut);
    }

    @Test
    void testFakeProgramExecutionItemsRegisteredInTheSale() {
        ItemIdentifier identifier = new ItemIdentifier(8);
        ItemIdentifier anotherIdentifier = new ItemIdentifier(17);
        ItemDTO item = inventory.searchItemByBarcode(identifier);
        ItemDTO anotherItem = inventory.searchItemByBarcode(anotherIdentifier);
        sale.registerItem(item);
        sale.registerItem(anotherItem);
        view.fakeProgramExecution();
        String result = outputContent.toString();
        assertTrue(result.contains(item.getName()), "Incorrect name of the item with barcode 8 returned");
        assertTrue(result.contains(anotherItem.getName()), "Incorrect name of the item with barcode 17 returned");
    }

    @Test
    void testFakeProgramExecutionSaleDTOWithRunningTotalReturnedAfterRegisteringTheFirstItem() {
        ItemIdentifier identifier = new ItemIdentifier(8);
        ItemDTO item = inventory.searchItemByBarcode(identifier);
        Amount itemPriceWithoutVAT = item.getPrice();
        Amount itemVATDividedByHundred = new Amount(item.getVAT()/100);
        Amount itemPriceWithVAT = itemPriceWithoutVAT.multiply(itemVATDividedByHundred);
        sale.registerItem(item);
        view.fakeProgramExecution();
        String result = outputContent.toString();
        assertTrue(result.contains(itemPriceWithVAT.toString()), "Running total after registering item with barcode 8 is not returned.");
    }

    @Test
    void testFakeProgramExecutionCorrectTotalPricePrinted() {
        ItemIdentifier identifier = new ItemIdentifier(8);
        ItemIdentifier anotherIdentifier = new ItemIdentifier(17);
        ItemDTO item = inventory.searchItemByBarcode(identifier);
        ItemDTO anotherItem = inventory.searchItemByBarcode(anotherIdentifier);
        sale.registerItem(item);
        sale.registerItem(anotherItem);
        sale.calculateTotalPrice();
        view.fakeProgramExecution();
        String result = outputContent.toString();
        assertTrue(result.contains(sale.getTotalPrice().toString()), "The presented total price is incorrect.");
    }

    @Test
    void testFakeProgramExecutionReceiptIsPrinted() {
        ItemIdentifier identifier = new ItemIdentifier(8);
        ItemIdentifier anotherIdentifier = new ItemIdentifier(17);
        ItemDTO item = inventory.searchItemByBarcode(identifier);
        ItemDTO anotherItem = inventory.searchItemByBarcode(anotherIdentifier);
        sale.registerItem(item);
        sale.registerItem(anotherItem);
        sale.calculateTotalPrice();
        view.fakeProgramExecution();
        String result = outputContent.toString();
        assertTrue(result.contains("Thank you for your purchase!"), "The receipt has not been printed.");
    }


}