package se.kth.iv1350.saleProcess.view;

import se.kth.iv1350.saleProcess.controller.Controller;
import se.kth.iv1350.saleProcess.integration.CustomerDTO;
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
        System.out.println("A new item has been scanned.");

        String saleInfo = contr.registerItem(enteredIdentifier);
        System.out.println(saleInfo);

        System.out.println("The program ends the sale.");
        Amount totalPrice = contr.endSale();
        System.out.println("The total price of the sale is: " + totalPrice.toString() + " SEK \n");
        String change = contr.pay(new Amount(100));
        System.out.println(change);


    }

}
