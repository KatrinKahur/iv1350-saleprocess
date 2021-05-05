package se.kth.iv1350.saleProcess.integration;

import se.kth.iv1350.saleProcess.model.Sale;

import java.util.ArrayList;
import java.util.List;

/**
 * This class includes system calls to the database with all completed sales.
 */
public class SaleCatalog {

    private List<Sale> saleLog = new ArrayList<>();
    /**
     * This method adds the sale to the sale log.
     * @param currentSale The specified sale that is logged
     */
    public void logSale(Sale currentSale){
        saleLog.add(currentSale);
    }
}
