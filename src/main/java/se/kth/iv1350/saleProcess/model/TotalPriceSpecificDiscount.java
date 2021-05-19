package se.kth.iv1350.saleProcess.model;

import se.kth.iv1350.saleProcess.integration.DiscountRequestDTO;

/**
 * Checks for available discount based on sale price
 */
public class TotalPriceSpecificDiscount implements DiscountRule {
    /**
     * Checks if there is a discount available based on total price of the <code>Sale</code>.
     * @param discountRequestDTO The specified <code>DiscountRequestDTO</code> that has all the data needed
     *                          to check for available discount
     * @return  Returns the discount <code>Amount</code> of 10 SEK if the total price is greater than 150 SEK,
     * if not, returns <code>null</code>.
     */
    @Override
    public Amount tryDiscount(DiscountRequestDTO discountRequestDTO){
       if (discountRequestDTO.getSaleDTO().getPaymentInformation().getRunningTotal().isGreaterThan(new Amount(150)))
            return new Amount(10);
        else
            return null;
    }
}
