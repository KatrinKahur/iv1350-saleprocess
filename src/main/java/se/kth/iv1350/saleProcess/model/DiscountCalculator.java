package se.kth.iv1350.saleProcess.model;

import se.kth.iv1350.saleProcess.integration.CustomerDTO;

import java.util.List;

/**
 * This class is responsible for finding and applying the correct discount to a sale.
 */
public class DiscountCalculator {

    Amount totalDiscount;

    /**
     * Creates an instance of <code>DiscountCalculator</code>
     */
    public DiscountCalculator(){
        totalDiscount = new Amount();
    }

    /**
     * This method calculates the total discount based on customers membership level, number of bought items,
     * item list and total price of the sale.
     * @param currentSale Used to get the number of bought items, item list and the total price of the sale
     * @param customerEligibleForDiscount The specified <code>CustomerDTO</code> that is used to get the membership
     * level of the customer.
     * @return The calculated discount <code>Amount</code>
     */
    public Amount calculateDiscount(Sale currentSale, CustomerDTO customerEligibleForDiscount){

        Amount discountBasedOnMembership = calculateDiscountBasedOnMembership(customerEligibleForDiscount, currentSale);
        updateTotalDiscount(discountBasedOnMembership);

        Amount discountBasedOnNumberOfItems = calculateDiscountBasedOnNumberOfBoughtItems(currentSale);
        updateTotalDiscount(discountBasedOnNumberOfItems);

        Amount discountBasedOnPrice = calculateDiscountBasedOnSalePrice(currentSale);
        updateTotalDiscount(discountBasedOnPrice);

        Amount discountBasedOnItemList = calculateTotalDiscountBasedOnItemList(currentSale);
        updateTotalDiscount(discountBasedOnItemList);

        return totalDiscount;
    }

    /**
     * This method calculates discount based on customers membership level
     * @param customerEligibleForDiscount Used to get the membership level of the customer
     * @param currentSale Used to get the total price of the sale before discount
     * @return  The calculated discount <code>Amount</code>
     */
    private Amount calculateDiscountBasedOnMembership(CustomerDTO customerEligibleForDiscount, Sale currentSale){
        double discountPercentageBasedOnMembership = getDiscountBasedOnMembershipLevel(customerEligibleForDiscount);
        return convertPercentageIntoAmount(discountPercentageBasedOnMembership, currentSale.getTotalPrice());
    }

    /**
     * This method calculates discount based on the number of bought items
     * @param currentSale Used to get the number of bought items and the total price
     * @return The calculated discount <code>Amount</code>
     */
    private Amount calculateDiscountBasedOnNumberOfBoughtItems(Sale currentSale){
        double discountPercentageBasedOnNumberOfItems = getDiscountBasedOnTheNumberOfBoughtItems(currentSale);
        return convertPercentageIntoAmount(discountPercentageBasedOnNumberOfItems, currentSale.getTotalPrice());
    }

    /**
     * This method calculates discount based on the total price of the sale.
     * @param currentSale Used to get the total price of the sale
     * @return The calculated discount <code>Amount</code>
     */
    private Amount calculateDiscountBasedOnSalePrice(Sale currentSale){
        double discountPercentageBasedOnSalePrice = getDiscountBasedOnTheSalePrice(currentSale);
        return convertPercentageIntoAmount(discountPercentageBasedOnSalePrice, currentSale.getTotalPrice());
    }

    private Amount calculateTotalDiscountBasedOnItemList(Sale currentSale){

        List<Item> itemList = currentSale.getListOfItems();
        Amount totalDiscountForItemList = new Amount();

        for (Item item : itemList){
            double discountPercentageForItem = getDiscountBasedOnTheBarcode(item);
            Amount discountPerItem = convertPercentageIntoAmount(discountPercentageForItem, item.getPrice());
            totalDiscountForItemList = totalDiscountForItemList.plus(discountPerItem);
        }
        return totalDiscountForItemList;
    }

    /**
     * This method updates <code>totalDiscount</code> by adding the specified discount
     * @param discount The specified discount that needs to be added to <code>totalDiscount</code>
     */
    private void updateTotalDiscount(Amount discount){
        totalDiscount = totalDiscount.plus(discount);
    }

    /**
     * This method gets the correct discount based on customers membership level
     * @param customerEligibleForDiscount <code>CustomerDTO</code> that is used to get customers membership level
     * @return The discount percentage based on customers membership level. If the level is bronze, a discount of 5% is returned.
     *         If the level is silver, a discount of 10% is returned. If the level is gold, a discount of 15% is returned.
     */
    private double getDiscountBasedOnMembershipLevel(CustomerDTO customerEligibleForDiscount) {

        if (customerEligibleForDiscount.getMembershipLevel().equals("bronze"))
            return 5;
        else if (customerEligibleForDiscount.getMembershipLevel().equals("silver"))
            return 10;
        else if (customerEligibleForDiscount.getMembershipLevel().equals("gold"))
            return 15;
        else
            return 0;
    }

    /**
     * This method gets the correct discount based on the number of bought items.
     * @param currentSale Used to get the number of bought items
     * @return The discount percentage based on the number of bought items. If the number of bought items is between 1 and 10,
     *          a discount of 5% is returned. If the number of bought items is more than 10, a discount of 10% is returned.
     */
    private double getDiscountBasedOnTheNumberOfBoughtItems(Sale currentSale){

        if(currentSale.getTheNumberOfBoughtItems() >= 1 && currentSale.getTheNumberOfBoughtItems() <= 10)
            return 5;
        else if (currentSale.getTheNumberOfBoughtItems() > 10)
            return 10;
        else
            return 0;
    }

    /**
     * This method gets the correct discount based on the total price of the sale.
     * @param currentSale Used to get the total price of the sale.
     * @return The discount percentage based on the total price of the sale. If the total price is below 100 SEK, there is
     *         no discount and 0% is returned. If the price is 100 SEK or higher, a discount of 5% is returned.
     */
    private double getDiscountBasedOnTheSalePrice(Sale currentSale){

        if(currentSale.getTotalPrice().getAmount() >= 100)
            return 5;
        else
            return 0;
    }

    /**
     * This method gets the correct discount based on the barcode of the bought item
     * @param item Used to get the barcode of the item
     * @return The discount percentage based on the barcode of the bought item. If the barcode value is between 1 and 5, a discount
     *         of 5% is returned. If the barcode is between 6 and 10, a discount of 10% is returned. If the barcode value is between 11 and 15,
     *         a discount of 15% is returned. If the barcode value is between 16 and 20, a discount of 20% is returned.
     */
    private double getDiscountBasedOnTheBarcode(Item item){

        if(item.getBarcode() >= 1 && item.getBarcode() <= 5)
            return 5;
        else if(item.getBarcode() > 5 && item.getBarcode() <= 10)
            return 10;
        else if(item.getBarcode() > 10 && item.getBarcode() <= 15)
            return 15;
        else if (item.getBarcode() > 15 && item.getBarcode() <= 20)
            return 20;
        else
            return 0;
    }

    /**
     * Converts the value of a discount percentage into corresponding <code>Amount</code> based on the specified price
     * @param percentage The specified discount percentage
     * @param price The specified price the discount is deducted from
     * @return The converted discount <code>Amount</code>
     */
    public Amount convertPercentageIntoAmount(double percentage, Amount price){
        Amount percentageDividedByHundred = new Amount(percentage/100);
        return percentageDividedByHundred.multiply(price);
    }

}
