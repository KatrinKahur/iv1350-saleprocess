package se.kth.iv1350.saleProcess.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.saleProcess.controller.Controller;
import se.kth.iv1350.saleProcess.integration.*;
import se.kth.iv1350.saleProcess.model.Amount;
import se.kth.iv1350.saleProcess.model.Sale;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
    private ItemDTO item;
    private ItemDTO anotherItem;

    @BeforeEach
    void setUp()throws IOException{
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
        try{
            ItemIdentifier identifier = new ItemIdentifier(8);
            ItemIdentifier anotherIdentifier = new ItemIdentifier(17);
            item = inventory.searchItemByBarcode(identifier);
            anotherItem = inventory.searchItemByBarcode(anotherIdentifier);
        }
        catch (InvalidItemIdentifierException exc){
            fail("Got exception.");
            exc.getCause();
        }
        view.fakeProgramExecution();
        String result = outputContent.toString();
        assertTrue(result.contains(item.getName()), "Incorrect name of the item with barcode 8 returned");
        assertTrue(result.contains(anotherItem.getName()), "Incorrect name of the item with barcode 17 returned");
    }

    @Test
    void testFakeProgramExecutionSaleDTOWithRunningTotalReturnedAfterRegisteringTheFirstItem(){
        try{
            ItemIdentifier identifier = new ItemIdentifier(8);
            item = inventory.searchItemByBarcode(identifier);
        }
        catch(InvalidItemIdentifierException exc){
            fail("Got exception.");
            exc.printStackTrace();
        }
        Amount itemPriceWithoutVAT = item.getPrice();
        Amount itemVATDividedByHundred = new Amount(item.getVAT()/100);
        Amount itemPriceWithVAT = itemPriceWithoutVAT.multiply(itemVATDividedByHundred);
        view.fakeProgramExecution();
        String result = outputContent.toString();
        assertTrue(result.contains(itemPriceWithVAT.toString()), "Running total after registering item with barcode 8 is not returned.");
    }

    @Test
    void testFakeProgramExecutionCorrectTotalPricePrinted(){
        try{
            ItemIdentifier identifier = new ItemIdentifier(8);
            ItemIdentifier anotherIdentifier = new ItemIdentifier(17);
            item = inventory.searchItemByBarcode(identifier);
            anotherItem = inventory.searchItemByBarcode(anotherIdentifier);
        }
        catch (InvalidItemIdentifierException exc){
            fail("Got exception.");
            exc.printStackTrace();
        }
        sale.registerItem(item);
        sale.registerItem(anotherItem);
        view.fakeProgramExecution();
        String result = outputContent.toString();
        assertTrue(result.contains(sale.getPaymentInformation().getRunningTotal().toString()),
                "The presented total price is incorrect.");

    }

    @Test
    void testFakeProgramExecutionReceiptIsPrinted(){
        view.fakeProgramExecution();
        String result = outputContent.toString();
        assertTrue(result.contains("Thank you for your purchase!"), "The receipt has not been printed.");
    }

    @Test
    void testFakeProgramExecutionUnableToPerformOperationExceptionCorrectMessagePrinted(){
        view.fakeProgramExecution();
        String result = outputContent.toString();
        assertTrue(result.contains("ERROR: Item registration failed."), "The output produced by the view after" +
                    " catching the exception is incorrect.");
    }

    @Test
    void testFakeProgramExecutionInvalidItemIdentifierExceptionCorrectMessagePrinted(){
        view.fakeProgramExecution();
        String result = outputContent.toString();
        assertTrue(result.contains("ERROR: Item identifier is invalid."), "The output produced by the view after" +
                " catching the exception is incorrect.");
    }

}