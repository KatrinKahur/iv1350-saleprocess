package se.kth.iv1350.saleProcess.model;

import se.kth.iv1350.saleProcess.integration.ItemDTO;
import se.kth.iv1350.saleProcess.integration.SaleDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the current sale.
 */
public class Sale {
    private Receipt receipt;
    private LocalDateTime saleTime;
    private List<Item> listOfItems;
    private Item recentlyScannedItem;
    private Amount runningTotal;
    private Amount totalPrice;

    /**
     * Creates an instance of <code>Sale</code>, sets the <code>saleTime</code>, creates a new <code>Receipt</code> and
     * a new <code>ArrayList</code>
      */
    public Sale(){
        setSaleTime();
        receipt = new Receipt(saleTime);
        listOfItems = new ArrayList<>();
        runningTotal = new Amount();
    }

    /**
     * Sets the <code>saleTime</code>.
     */
    private void setSaleTime(){
        saleTime = LocalDateTime.now();
    }
    /**
     * This method updates the running total.
     */
    private void updateRunningTotal(){
        runningTotal = runningTotal.plus(recentlyScannedItem.getPriceWithVAT());
    }

    /**
     * This method gets the value of <code>runningTotal</code>
     * @return The value of <code>runningTotal</code>
     */
    public Amount getRunningTotal(){
        return runningTotal;
    }

    /**
     * This method gets the recently scanned item.
     * @return The value of <code>recentlyScannedItem</code>
     */
    public Item getRecentlyScannedItem(){
        return recentlyScannedItem;
    }

    public SaleDTO registerItem(ItemDTO foundItem){
        Item existingItem = checkForExistingItem(foundItem);

        if (existingItem == null) {
            setRecentlyScannedItem(new Item(foundItem));
            addRecentlyScannedItemToTheItemList();
        }
        else {
            existingItem.increaseQuantity();
            setRecentlyScannedItem(existingItem);
        }

        recentlyScannedItem.calculatePriceWithVAT();
        updateRunningTotal();
        return createSaleInformation();
    }

    /**
     * This method iterates the <code>listOfItems</code> to check if an <code>Item</code> with similar <code>barcode</code> already
     * exists in <code>Sale</code>
     * @param foundItem Used to get the scanned items barcode
     * @return If <code>Item</code> with similar barcode already exists in <code>Sale</code>, the value of
     * <code>Item</code> is returned, or else the program returns <code>null</code>
     */
    private Item checkForExistingItem(ItemDTO foundItem){
        for (Item item : listOfItems){
            if (foundItem.getBarcode() == item.getBarcode())
                return item;
        }
        return null;
    }

    /**
     * This method creates a new instance of <code>Item</code> and assigns <code>recentlyScannedItem</code>
     * refer to it.
     * @param newItem The fetched item from the inventory system
     */
    private void setRecentlyScannedItem(Item newItem){
        recentlyScannedItem = newItem;
    }

    /**
     * This method adds the most recently scanned item to the item list.
     */
    private void addRecentlyScannedItemToTheItemList(){
        listOfItems.add(recentlyScannedItem);
    }

    /**
     * This method creates a new instance of <code>SaleDTO</code> for the view.
     * @return <code>SaleDTO</code> that includes item description, VAT and running total
     */
    public SaleDTO createSaleInformation(){
        return new SaleDTO(this);
    }

    /**
     * This method calculates the total price of the sale.
     */
    private void calculateTotalPrice(){
        totalPrice = new Amount(runningTotal.getAmount());
    }

    /**
     * This method gets the total price of the sale.
     * @return The value of <code>totalPrice</code>
     */
    public Amount getTotalPrice(){
        return totalPrice;
    }

    /**
     * This method ends the sale by calculating the total price
     * @return The total price of the sale
     */
    public Amount endSale(){
        calculateTotalPrice();
        return getTotalPrice();
    }

}
