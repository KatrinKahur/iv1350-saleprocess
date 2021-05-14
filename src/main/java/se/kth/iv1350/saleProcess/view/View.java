package se.kth.iv1350.saleProcess.view;

import se.kth.iv1350.saleProcess.controller.Controller;
import se.kth.iv1350.saleProcess.controller.OperationFailedException;
import se.kth.iv1350.saleProcess.integration.InvalidItemIdentifierException;
import se.kth.iv1350.saleProcess.integration.ItemIdentifier;
import se.kth.iv1350.saleProcess.integration.SaleDTO;
import se.kth.iv1350.saleProcess.model.Amount;
import se.kth.iv1350.saleProcess.util.ExceptionLogger;

import java.io.IOException;

/**
 * This class is a placeholder for the entire view of the program.
 */
public class View {
    Controller contr;
    ExceptionLogger logger;

    /**
     * Creates a new instance of the class.
     * @param contr The controller that is used for all operations.
     */
    public View(Controller contr)throws IOException {
        this.contr = contr;
        this.logger = new ExceptionLogger();
    }

    /**
     * Simulates a user input to test the programs all operations.
     */
    public void fakeProgramExecution(){
        contr.startSale();
        System.out.println("A new sale has been started.");
        System.out.println();

        System.out.println("Enter a barcode: ");
        int scannedBarcode = 8;
        ItemIdentifier enteredIdentifier = new ItemIdentifier(scannedBarcode);
        System.out.println("Barcode " + scannedBarcode + " has been entered.");

        try{
            SaleDTO saleInfo = contr.registerItem(enteredIdentifier);
            System.out.println("Item with barcode " + scannedBarcode + " has been registered. " +
                    "The program returns item description and running total.");
            System.out.println(saleInfo.toString());
        }
        catch(InvalidItemIdentifierException invalidItemExc){
            System.out.println("ERROR: Item with barcode " + invalidItemExc.getInvalidItemIdentifier().getBarcode() +
                    " cannot be found.");
        }
        catch (OperationFailedException opFailedExc){
            System.out.println("ERROR: Item registration failed.");
            logger.logException(opFailedExc);
        }

        System.out.println("Enter a barcode: ");
        scannedBarcode = 17;
        enteredIdentifier = new ItemIdentifier(scannedBarcode);
        System.out.println("Barcode " + scannedBarcode + " has been entered.");

        try{

            SaleDTO saleInfo = contr.registerItem(enteredIdentifier);
            System.out.println("Item with barcode " + scannedBarcode + " has been registered." +
                    " The program returns item description and running total.");
            System.out.println(saleInfo.toString());
        }
        catch(InvalidItemIdentifierException invalidItemExc){
            System.out.println("ERROR: Item with barcode " + invalidItemExc.getInvalidItemIdentifier().getBarcode() +
                    " cannot be found.");
        }
        catch(OperationFailedException opFailedExc){
            System.out.println("ERROR: Item registration failed.");
            logger.logException(opFailedExc);
        }

        System.out.println("Enter a barcode: ");
        int scannedBarcodeThatCausesOperationFailedException = 20;
        ItemIdentifier enteredIdentifierThatCausesOperationFailedException =
                new ItemIdentifier(scannedBarcodeThatCausesOperationFailedException);
        System.out.println("Barcode " + scannedBarcodeThatCausesOperationFailedException + " has been entered.");

        try{
            SaleDTO saleInfo = contr.registerItem(enteredIdentifierThatCausesOperationFailedException);
            System.out.println("Item with barcode " + scannedBarcodeThatCausesOperationFailedException +
                    " has been registered." +
                    " The program returns item description and running total.");
            System.out.println(saleInfo.toString());
        }
        catch (InvalidItemIdentifierException invalidItemExc){
            System.out.println("ERROR: Item with barcode " + invalidItemExc.getInvalidItemIdentifier().getBarcode() +
                    " cannot be found.");
        }
        catch (OperationFailedException opFailedExc){
            System.out.println("ERROR: Item registration failed.");
            logger.logException(opFailedExc);
        }

        System.out.println("Enter a barcode: ");
        int scannedBarcodeThatCausesInvalidItemIdentifierException = 25;
        ItemIdentifier enteredIdentifierThatCausesInvalidIdentifierException =
                new ItemIdentifier(scannedBarcodeThatCausesInvalidItemIdentifierException);
        System.out.println("Barcode " + scannedBarcodeThatCausesInvalidItemIdentifierException+ " has been entered.");
        try{
            SaleDTO saleInfo = contr.registerItem(enteredIdentifierThatCausesInvalidIdentifierException);
            System.out.println("Item with barcode " + scannedBarcodeThatCausesInvalidItemIdentifierException +
                    " has been registered." +
                    " The program returns item description and running total.");
            System.out.println(saleInfo.toString());
        }
        catch (InvalidItemIdentifierException invalidItemExc){
            System.out.println("ERROR: Item with barcode " + invalidItemExc.getInvalidItemIdentifier().getBarcode() +
                    " cannot be found.");
        }
        catch (OperationFailedException opFailedExc){
            System.out.println("ERROR: Item registration failed.");
            logger.logException(opFailedExc);
        }

        Amount totalPrice = contr.endSale();
        System.out.println("The program ends the sale and returns the total price.");
        System.out.println();
        System.out.println("The total price of the sale is: " + totalPrice.toString() + " SEK \n");

        System.out.println("Enter payment: ");
        Amount cashPayment = new Amount(150);
        System.out.println("The program registers " + cashPayment + " SEK, " +
                "calculates the change and prints a receipt.");
        System.out.println();
        contr.pay(cashPayment);

    }
}
