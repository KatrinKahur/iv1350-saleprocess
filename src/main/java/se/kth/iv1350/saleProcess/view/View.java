package se.kth.iv1350.saleProcess.view;

import se.kth.iv1350.saleProcess.controller.Controller;
import se.kth.iv1350.saleProcess.integration.ItemIdentifier;
import se.kth.iv1350.saleProcess.integration.SaleDTO;
import se.kth.iv1350.saleProcess.model.Amount;

/**
 * This class is a placeholder for the entire view of the program.
 */
public class View {
    Controller contr;

    /**
     * Creates a new instance of the class.
     * @param contr The controller that is used for all operations.
     */
    public View(Controller contr){
        this.contr = contr;
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
        SaleDTO saleInfo = contr.registerItem(enteredIdentifier);
        System.out.println("Item with barcode " + scannedBarcode + " has been registered. " +
                "The program returns item description and running total.");
        System.out.println(saleInfo.toString());

        System.out.println("Enter a barcode: ");
        scannedBarcode = 17;
        enteredIdentifier = new ItemIdentifier(scannedBarcode);
        System.out.println("Barcode " + scannedBarcode + " has been entered.");
        saleInfo = contr.registerItem(enteredIdentifier);
        System.out.println("Item with barcode " + scannedBarcode + " has been registered." +
                " The program returns item description and running total.");
        System.out.println(saleInfo.toString());

        System.out.println();

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
