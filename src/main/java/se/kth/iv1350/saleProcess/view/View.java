package se.kth.iv1350.saleProcess.view;

import se.kth.iv1350.saleProcess.controller.Controller;
import se.kth.iv1350.saleProcess.integration.ItemIdentifier;
import se.kth.iv1350.saleProcess.model.Amount;

import java.util.Scanner;

/**
 * This class is a placeholder for the entire view.
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
     * Simulates a user input to test the program's all operations.
     */
    public void fakeProgramExecution(){
        contr.startSale();
        System.out.println("A new sale has been started.");

        Scanner in = new Scanner(System.in);
        System.out.println("Enter a barcode: ");
        int scannedBarcode = in.nextInt();
        ItemIdentifier enteredIdentifier = new ItemIdentifier(scannedBarcode);
        String saleInfo = contr.registerItem(enteredIdentifier);
        System.out.println("A new item has been registered. The program returns item description and running total.");
        System.out.println();
        System.out.println(saleInfo);

        Amount totalPrice = contr.endSale();
        System.out.println("The program ends the sale and returns the total price.");
        System.out.println();
        System.out.println("The total price of the sale is: " + totalPrice.toString() + " SEK \n");
        System.out.println("Enter payment: ");
        double payment = in.nextInt();
        System.out.println("The program registers the cash payment.");
        String receipt = contr.pay(new Amount(payment));
        System.out.println("The cash payment has been registered. The program returns a receipt.");
        System.out.println();
        System.out.println(receipt);



    }

}
