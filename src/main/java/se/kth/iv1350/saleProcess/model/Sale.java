package se.kth.iv1350.saleProcess.model;

import se.kth.iv1350.saleProcess.integration.ItemDTO;
import se.kth.iv1350.saleProcess.integration.Printer;
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
    private Amount cashPayment;
    private Amount change;

    /**
     * Creates an instance of <code>Sale</code>, sets the <code>saleTime</code>, creates a new <code>Receipt</code> and
     * a new <code>ArrayList</code>
      */
    public Sale(){
        setSaleTime();
        receipt = new Receipt();
        listOfItems = new ArrayList<>();
        runningTotal = new Amount();
    }


    private void setSaleTime(){
        saleTime = LocalDateTime.now();
    }

    /**
     * This method gets the time of the sale.
     * @return The time of the sale
     */
    LocalDateTime getSaleTime(){
        return saleTime;
    }

    private void updateRunningTotal(){
        runningTotal = runningTotal.plus(recentlyScannedItem.getPriceWithVAT());
    }

    /**
     * This method gets the value of <code>runningTotal</code>
     * @return The value of <code>runningTotal</code>
     */
     Amount getRunningTotal(){
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


    private Item checkForExistingItem(ItemDTO foundItem){
        for (Item item : listOfItems){
            if (foundItem.getBarcode() == item.getBarcode())
                return item;
        }
        return null;
    }


    private void setRecentlyScannedItem(Item newItem){
        recentlyScannedItem = newItem;
    }


    private void addRecentlyScannedItemToTheItemList(){
        listOfItems.add(recentlyScannedItem);
    }


    private SaleDTO createSaleInformation(){
        return new SaleDTO(runningTotal, recentlyScannedItem);
    }

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
     * This method gets the items in <code>listOfItems</code>
     * @return It returns the value of <code>listOfItems</code>
     */
    public List<Item> getListOfItems(){
        return listOfItems;
    }

    /**
     * This method ends the sale by calculating the total price
     * @return The total price of the sale
     */
    public Amount endSale(){
        calculateTotalPrice();
        return getTotalPrice();
    }


    public void registerPayment(CashRegister cashRegister, Amount cashPayment){
        setCashPayment(cashPayment);
        Amount change = cashRegister.addPayment(this);
        setChange(change);
        receipt.sendSaleToReceipt(this);
    }

    /**
     * This method calls the printer to print out the receipt
     * @param printer The object representing the printer
     * @return String representation of the receipt
     */
    public String printReceipt(Printer printer){
        return printer.printReceipt(receipt);
    }

    /**
     * This method gets the value of <code>cashPayment</code>
     * @return The value of <code>cashPayment</code>
     */
    Amount getCashPayment(){
        return cashPayment;
    }

    /**
     * This method gets the value of <code>change</code>
     * @return The value of <code>change</code>
     */
    Amount getChange(){
        return change;
    }

    private void setCashPayment(Amount cashPayment){
        this.cashPayment = cashPayment;
    }

    private void setChange(Amount change){
        this.change = change;
    }


}
