package se.kth.iv1350.saleProcess.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the current sale.
 */
public class Sale {
    private Receipt receipt;
    private LocalDateTime saleTime;
    private List<Item> listOfItems;
    private Amount runningTotal;
    private Amount totalPrice;

    /**
     * Creates an instance of <code>Sale</code>, sets the <code>saleTime</code>, creates a new <code>Receipt</code> and
     * a new <code>ArrayList</code>
      */
    public Sale(){
        setSaleTime();
        receipt = new Receipt(saleTime);
        listOfItems = new ArrayList<>();
    }

    /**
     * Sets the <code>saleTime</code>.
     */
    private void setSaleTime(){
        saleTime = LocalDateTime.now();
    }
}
