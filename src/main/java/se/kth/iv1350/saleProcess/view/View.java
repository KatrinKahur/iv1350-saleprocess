package se.kth.iv1350.saleProcess.view;

import se.kth.iv1350.saleProcess.controller.Controller;
import se.kth.iv1350.saleProcess.integration.CustomerDTO;
import se.kth.iv1350.saleProcess.integration.ItemIdentifier;
import se.kth.iv1350.saleProcess.model.Amount;

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
        ItemIdentifier enteredIdentifier = new ItemIdentifier(5);
        System.out.println("A new item has been scanned.");
        String saleInfo = contr.registerItem(enteredIdentifier);
        System.out.println(saleInfo);
        System.out.println("The program ends the sale.");
        Amount totalPrice = contr.endSale();
        System.out.println("The total price of the sale is: " + totalPrice.toString() + " SEK");
        CustomerDTO searchedCustomer = new CustomerDTO(130);
        totalPrice = contr.handleDiscountRequest(searchedCustomer);

    }

}
