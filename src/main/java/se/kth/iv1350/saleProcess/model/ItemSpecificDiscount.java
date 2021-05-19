package se.kth.iv1350.saleProcess.model;

import se.kth.iv1350.saleProcess.integration.DiscountPercentage;
import se.kth.iv1350.saleProcess.integration.DiscountRequestDTO;

/**
 * Checks for discount based on a bought item and number of bought items.
 */
public class ItemSpecificDiscount implements DiscountRule, DiscountPercentage {

    /**
     * Checks for seasonal item specific discounts and multiple item discounts.
     * @param discountRequestDTO The specified <code>DiscountRequestDTO</code> that has all the data needed
     *                          to check for available discount.
     * @return The discount <code>Amount</code> if there are any seasonal/ multiple item discounts available,
     * or else <code>null</code> is returned.
     */
    @Override
    public Amount tryDiscount(DiscountRequestDTO discountRequestDTO){
        Amount discountAmount = new Amount();
        for(Item checkedItem : discountRequestDTO.getSaleDTO().getItemList()){
            if(trySeasonalDiscount(checkedItem) != null)
                discountAmount = discountAmount.plus(trySeasonalDiscount(checkedItem));
            if(tryMultipleItemDiscount(checkedItem) != null)
                discountAmount = discountAmount.plus(tryMultipleItemDiscount(checkedItem));
        }
        if(!discountAmount.equals(new Amount()))
            return discountAmount;
        else
            return null;
    }

    private Amount trySeasonalDiscount(Item item){
        Amount discountAmount = null;
        if(item.getBarcode() == 1){
            Amount discountAmountPerItem =
                        new Amount(TWENTY_FIVE_PERCENT/(double) 100).multiply(item.getPriceWithVAT());
                discountAmount = new Amount(item.getQuantity() * discountAmountPerItem.getAmount());
        }

        if(item.getBarcode() == 7){
            Amount discountAmountPerItem = new Amount(TEN_PERCENT/(double) 100).multiply(item.getPriceWithVAT());
            discountAmount = new Amount(item.getQuantity() * discountAmountPerItem.getAmount());
        }

        if(item.getBarcode() == 8){
            Amount discountAmountPerItem = new Amount(FIFTEEN_PERCENT/(double) 100).multiply(item.getPriceWithVAT());
            discountAmount = new Amount(item.getQuantity() * discountAmountPerItem.getAmount());
        }
        return discountAmount;
    }

    private Amount tryMultipleItemDiscount(Item checkedItem) {
        Amount discountAmount = null;

        if ((checkedItem.getBarcode() == 6 || checkedItem.getBarcode() == 9) && checkedItem.getQuantity() >= 2) {
            Amount totalPrice = calculatePriceForAllItems(checkedItem);
            discountAmount = new Amount(TWENTY_PERCENT / (double) 100).multiply(totalPrice);
        }
        return discountAmount;
    }

    private Amount calculatePriceForAllItems(Item checkedItem){
        Amount totalPrice = new Amount(checkedItem.getQuantity() * checkedItem.getPriceWithVAT().getAmount());
        return totalPrice;
    }

}
