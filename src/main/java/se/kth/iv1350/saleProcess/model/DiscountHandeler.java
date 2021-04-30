package se.kth.iv1350.saleProcess.model;

import se.kth.iv1350.saleProcess.integration.DiscountCatalog;

/**
 * This class is responsible for finding and applying the correct discount to a sale.
 */
public class DiscountHandeler {
    private DiscountCatalog discCatalog;

    /**
     * Creates an instance of DiscountHandler.
     * @param discCatalog The catalog that includes all discounts.
     */
    public DiscountHandeler(DiscountCatalog discCatalog){
        this.discCatalog = discCatalog;
    }

}
