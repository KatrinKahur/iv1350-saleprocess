package se.kth.iv1350.saleProcess.model;

import java.time.LocalDateTime;

/**
 * This class represents a sale receipt.
 */
public class Receipt {
    private LocalDateTime saleTime;

    /**
     * Creates a new instance of <code>Receipt</code>
     * @param saleTime The time of the current sale
     */
    Receipt (LocalDateTime saleTime){
        this.saleTime = saleTime;
    }

}
