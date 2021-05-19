package se.kth.iv1350.saleProcess.view;

import se.kth.iv1350.saleProcess.controller.Controller;
import se.kth.iv1350.saleProcess.controller.UnableToPerformOperationException;
import se.kth.iv1350.saleProcess.integration.*;
import se.kth.iv1350.saleProcess.model.Amount;
import se.kth.iv1350.saleProcess.util.ExceptionLogger;
import se.kth.iv1350.saleProcess.util.TotalRevenueFileOutput;

import java.io.IOException;

/**
 * This class is a placeholder for the entire view of the program.
 */
public class View {
    Controller contr;
    ExceptionLogger logger;
    SaleDTO saleDTO;

    /**
     * Creates a new instance of the class.
     * @param contr The controller that is used for all operations.
     */
    public View(Controller contr)throws IOException {
        this.contr = contr;
        this.logger = new ExceptionLogger();
        contr.addSaleObserver(new TotalRevenueView());
        contr.addSaleObserver(new TotalRevenueFileOutput());
    }

    /**
     * Simulates a user input to test the programs all operations.
     */
    public void fakeProgramExecution(){
        simulateASale(8,17,20,25,128,560);
        simulateASale(1,2,9,9,129,250);
    }

    private void simulateASale(int barcodeNr1, int barcodeNr2, int barcodeNr3, int barcodeNr4,
                               int customerID, double paidAmt){
        contr.startSale();
        System.out.println("A new sale has been started.");
        System.out.println();

        simulateItemRegistration(barcodeNr1);
        simulateItemRegistration(barcodeNr2);
        simulateItemRegistration(barcodeNr3);
        simulateItemRegistration(barcodeNr4);

        Amount totalPrice = contr.endSale();
        System.out.println("The program ends the sale and returns the total price.");
        System.out.println();
        System.out.println("The total price of the sale is: " + totalPrice.toString() + " SEK \n");

        simulateDiscountRequest(customerID);

        System.out.println("Enter payment: ");
        Amount paidAmount = new Amount(paidAmt);
        System.out.println("The program registers " + paidAmount + " SEK, " +
                "calculates the change and prints a receipt.");
        System.out.println();
        contr.pay(paidAmount);
    }

    private void simulateItemRegistration(int scannedBarcode){
        System.out.println("Enter a barcode: ");
        ItemIdentifier enteredIdentifier = new ItemIdentifier(scannedBarcode);
        System.out.println("Barcode " + scannedBarcode + " has been entered.");

        try{
            saleDTO = contr.registerItem(enteredIdentifier);
            System.out.println("Item with barcode " + scannedBarcode + " has been registered. " +
                    "The program returns item description and running total.");
            System.out.println(saleDTO.toString());
        }
        catch(InvalidItemIdentifierException exc){
            System.out.println("ERROR: Item identifier is invalid.");
        }
        catch (UnableToPerformOperationException exc){
            System.out.println("ERROR: Item registration failed.");
            logger.logException(exc);
        }
    }

    private void simulateDiscountRequest(int searchedCustomerID){
        System.out.println("The customer requests discount.");
        System.out.println("The program checks if the customer is registered as a member.");

        CustomerDTO searchedCustomer = new CustomerDTO(searchedCustomerID);
        CustomerDTO customerEligibleForDiscount = contr.checkIfCustomerIsAMember(searchedCustomer);

        System.out.println("The program found a customer ID with number " +
                customerEligibleForDiscount.getCustomerID() + ".\n");

        DiscountRequestDTO discountRequest = new DiscountRequestDTO(saleDTO, customerEligibleForDiscount);

        System.out.println("The program now handles the discount request.");

        saleDTO = contr.handleDiscountRequest(discountRequest);

        System.out.println("The applied discount amount is " + saleDTO.getDiscount().getDiscountAmount() + " SEK.");
        System.out.println("The updated total price after applying discount is: "
                + saleDTO.getPaymentInformation().getRunningTotal().toString() + " SEK.\n");
    }

}
