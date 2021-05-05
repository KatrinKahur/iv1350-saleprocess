package se.kth.iv1350.saleProcess.integration;

import se.kth.iv1350.saleProcess.model.Receipt;

/**
 * This class represents a printer. It sends receipts to the actual printer for printouts.
 */
public class Printer {

    /**
     * This method is responsible for making the correct system calls to the printer to print out a receipt
     * @param receipt The specified receipt that is printed by the printer
     * @return String representation of the receipt
     */
    public String printReceipt(Receipt receipt){
        return receipt.toString();
    }
}
