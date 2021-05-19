package se.kth.iv1350.saleProcess.model;

import se.kth.iv1350.saleProcess.integration.DiscountRequestDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * A composite <code>DiscountRule</code> that uses different <code>DiscountRule</code>s to check for available discount.
 * All discount rules added to this composite are invoked when searching for discount.
 */
public class CompositeDiscountRule implements DiscountRule {

    private List<DiscountRule> discountRules = new ArrayList<>();

    CompositeDiscountRule(){

    }

    /**
     * Invokes all discount rule algorithms added to this composite to check for available discounts.
     * Adds all the separate discount amounts received from the different discount rule algorithms into a total discount
     * amount.
     * @param discountRequestDTO The specified <code>DiscountRequestDTO</code> that has all the data needed
     *                          to check for available discount
     * @return The total discount <code>Amount</code> after adding the return values from each discount rule algorithm.
     * If no discount was found, the composite returns <code>null</code>.
     */
    @Override
    public Amount tryDiscount(DiscountRequestDTO discountRequestDTO){
        Amount totalDiscountAmt = new Amount();
        for(DiscountRule discountRule : discountRules){
            Amount discountAmt = discountRule.tryDiscount(discountRequestDTO);
            if(discountAmt != null)
                totalDiscountAmt = totalDiscountAmt.plus(discountAmt);
        }
        if(!(totalDiscountAmt == null))
            return totalDiscountAmt;
        else
            return null;
    }

    /**
     * Adds the specified <code>DiscountRule/code> that is used when checking for discounts.
     * @param discountRule The specified <code>DiscountRule</code>
     */
    void addDiscountRule(DiscountRule discountRule){
        discountRules.add(discountRule);
    }
}
