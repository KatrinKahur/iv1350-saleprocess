package se.kth.iv1350.saleProcess.model;

import se.kth.iv1350.saleProcess.integration.DiscountPercentage;
import se.kth.iv1350.saleProcess.integration.DiscountRequestDTO;

/**
 * Checks for discount based on customers membership level.
 */
public class MembershipSpecificDiscount implements DiscountRule, DiscountPercentage {
    /**
     * This method checks if there are discounts based on customer membership level.
     * @param discountRequestDTO The specified <code>DiscountRequestDTO</code> that has all the data needed
     *                          to check for available discount
     * @return The discount <code>Amount</code> that needs to be subtracted from the sale price. If there are no
     * discounts available, <code>null</code> is returned.
     */
    @Override
    public Amount tryDiscount(DiscountRequestDTO discountRequestDTO){
        Amount discountAmt;

        if(discountRequestDTO.getCustomerDTO().getCustomerMembershipLevel().equals("bronze")){
           discountAmt = new Amount(FIVE_PERCENT/(double)100).multiply(
                   discountRequestDTO.getSaleDTO().getPaymentInformation().getRunningTotal());
           return discountAmt;
        }
        else if(discountRequestDTO.getCustomerDTO().getCustomerMembershipLevel().equals("silver")){
            discountAmt = new Amount(TEN_PERCENT/(double)100).multiply(
                    discountRequestDTO.getSaleDTO().getPaymentInformation().getRunningTotal());
            return discountAmt;
        }
        else if(discountRequestDTO.getCustomerDTO().getCustomerMembershipLevel().equals("gold")){
            discountAmt = new Amount(FIFTEEN_PERCENT/(double)100).multiply(
                    discountRequestDTO.getSaleDTO().getPaymentInformation().getRunningTotal());
            return discountAmt;
        }
        else
            return null;
    }

}
