package se.kth.iv1350.saleProcess.model;

import se.kth.iv1350.saleProcess.integration.DiscountRequestDTO;

/**
 * Defines the algorithm for applying a discount.
 */
public interface DiscountRule {
    /**
     * This method checks if a discount can be applied based on the data included in the <code>discountRequestDTO</code>
     * @param discountRequestDTO The specified <code>DiscountRequestDTO</code> that has all the data needed
     *                          to check for available discount
     * @return The discount amount that has to be subtracted from the sale price
     */
    Amount tryDiscount(DiscountRequestDTO discountRequestDTO);
}
