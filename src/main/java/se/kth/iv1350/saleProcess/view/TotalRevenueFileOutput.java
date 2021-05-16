package se.kth.iv1350.saleProcess.view;

import se.kth.iv1350.saleProcess.model.Amount;
import se.kth.iv1350.saleProcess.model.SaleObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * Prints the total amount paid for the purchases since the program started to a file.
 */
public class TotalRevenueFileOutput implements SaleObserver {

    private PrintWriter fileOutput;
    private Amount totalRevenue;

    /**
     * Creates a new instance of <code>TotalRevenueFileOutput</code>
     */
    TotalRevenueFileOutput(){
        totalRevenue = new Amount();
        try{
            fileOutput = new PrintWriter(new FileWriter("total-revenue.txt"), true);
        }
        catch (IOException exception){
            System.out.println("Could not print to a file.");
            exception.printStackTrace();
        }
    }

    @Override
    public void newPaymentAddedToSale(Amount totalPrice) {
        updateTotalRevenue(totalPrice);
        printCurrentStateToFile();
    }

    private void updateTotalRevenue(Amount totalPrice){
        totalRevenue = totalRevenue.plus(totalPrice);
    }

    private void printCurrentStateToFile(){
        fileOutput.println("Time: " + LocalDateTime.now());
        fileOutput.println("The total revenue is " + totalRevenue.toString() + " SEK.");
        fileOutput.println();
    }
}
