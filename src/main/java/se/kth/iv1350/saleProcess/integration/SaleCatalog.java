package se.kth.iv1350.saleProcess.integration;

import se.kth.iv1350.saleProcess.model.Sale;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains system calls to the database that has all completed sales.
 */
public class SaleCatalog {

    private final List<Sale> saleLog = new ArrayList<>();

    /**
     * This method logs a completed sale.
     * @param currentSale The specified sale that is logged
     */
    public void logSale(Sale currentSale){
        saleLog.add(currentSale);
    }
}
