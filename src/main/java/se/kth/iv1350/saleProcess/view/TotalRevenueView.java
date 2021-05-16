package se.kth.iv1350.saleProcess.view;

import se.kth.iv1350.saleProcess.model.Amount;
import se.kth.iv1350.saleProcess.model.SaleObserver;

/**
 * Prints the total amount paid for the purchases since the program started to the console.
 */
public class TotalRevenueView implements SaleObserver {

    private Amount totalRevenue;

    /**
     * Creates a new instance of <code>TotalRevenueView</code>
     */
    TotalRevenueView(){
        totalRevenue = new Amount();
    }

    @Override
    public void newPaymentAddedToSale(Amount totalPrice) {
        updateTotalRevenue(totalPrice);
        printCurrentState();
    }

    private void updateTotalRevenue(Amount totalPrice){
        totalRevenue = totalRevenue.plus(totalPrice);
    }

    private void printCurrentState(){
        System.out.println("The total revenue is: " + totalRevenue.toString() + " SEK.");
        System.out.println();
    }
}
