package se.kth.iv1350.saleProcess.integration;

import se.kth.iv1350.saleProcess.model.Sale;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains system calls to the database that has all completed sales.
 */
public class SaleCatalog {

    private final List<SaleDTO> saleLog = new ArrayList<>();

    /**
     * This method logs a completed sale.
     * @param currentSaleDTO A DTO of the specified sale that is logged
     */
    public void logSale(SaleDTO currentSaleDTO){
        saleLog.add(currentSaleDTO);
    }
}
