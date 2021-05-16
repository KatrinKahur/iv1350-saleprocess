package se.kth.iv1350.saleProcess.model;

import se.kth.iv1350.saleProcess.integration.ItemDTO;
import se.kth.iv1350.saleProcess.integration.Printer;
import se.kth.iv1350.saleProcess.integration.SaleDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the current sale.
 */
public class Sale {
    private final Receipt receipt;
    private String saleTime;
    private final List<Item> listOfItems;
    private Item recentlyScannedItem;
    private Amount runningTotal;
    private Amount totalPrice;
    private Amount cashPayment;
    private Amount change;
    private List<SaleObserver> saleObservers = new ArrayList<>();

    /**
     * Creates an instance of <code>Sale</code>
      */
    public Sale(){
        setSaleTime();
        receipt = new Receipt();
        listOfItems = new ArrayList<>();
        runningTotal = new Amount();
    }

    /**
     * This method gets the time and the date of the sale.
     * @return A String with the time and the date of the sale
     */
    String getSaleTime(){
        return saleTime;
    }

    /**
     * This method gets the value of <code>runningTotal</code>
     * @return The value of <code>runningTotal</code>
     */
     Amount getRunningTotal(){
        return runningTotal;
    }

    /**
     * This method gets the most recently scanned item.
     * @return The most recently scanned item
     */
    public Item getRecentlyScannedItem(){
        return recentlyScannedItem;
    }

    /**
     * This method adds the scanned item to the sale
     * @param foundItem The fetched item from the inventory
     * @return <code>SaleDTO</code> with the description of the scanned item and the running total
     */
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

        updateRunningTotal();
        return createSaleInformationReturnedToView();
    }

    /**
     * This method calculates the total price of the sale.
     */
    public void calculateTotalPrice(){
        totalPrice = new Amount(runningTotal.getAmount());
    }

    /**
     * This method gets the value of totalPrice
     * @return The value of totalPrice
     */
    public Amount getTotalPrice(){
        return totalPrice;
    }

    /**
     * This method gets the items in listOfItems
     * @return The value of listOfItems
     */
    List<Item> getListOfItems(){
        return listOfItems;
    }

    /**
     * This method makes the correct system calls to register a cash payment.
     * @param cashRegister The cash register used to register cash payments
     * @param cashPayment Paid amount
     */
    public void registerPayment(CashRegister cashRegister, Amount cashPayment){
        setCashPayment(cashPayment);
        cashRegister.registerPayment(this);
        setChange(cashRegister.getChange(this));
        notifyObservers();
    }

    /**
     * This method calls the printer to print out the receipt
     * @param printer The object representing the printer
     */
    public void printReceipt(Printer printer){
        receipt.sendSaleToReceipt(this);
        printer.printReceipt(receipt);
    }

    /**
     * Adds the specified observer that needs to be notified about a certain state change.
     * @param observer The specified observer
     */
    public void addSaleObserver(SaleObserver observer){
        saleObservers.add(observer);
    }

    /**
     * Adds the specified observers that need to be notified about a certain state change.
     * @param observers The specified observers
     */
    public void addSaleObservers(List<SaleObserver> observers){
        saleObservers.addAll(observers);
    }

    /**
     * This method gets the <code>Amount</code> of VAT for the entire sale.
     * @return <code>Amount</code> of VAT for the entire sale
     */
    Amount getVATForTheEntireSale(){
        Amount totalVAT = new Amount();
        for(Item item : listOfItems){
            totalVAT = totalVAT.plus(item.getVATConvertedIntoAmount());
        }
        return totalVAT;
    }

    /**
     * This method gets the value of cashPayment
     * @return The value of cashPayment
     */
    Amount getCashPayment(){
        return cashPayment;
    }

    /**
     * This method gets the value of change
     * @return The value of change
     */
    Amount getChange(){
        return change;
    }

    private void notifyObservers(){
        for (SaleObserver observer : saleObservers)
            observer.newPaymentAddedToSale(totalPrice);
    }

    private void setCashPayment(Amount cashPayment){
        this.cashPayment = cashPayment;
    }

    private void setChange(Amount change){
        this.change = change;
    }

    private void setSaleTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        saleTime = LocalDateTime.now().format(formatter);
    }

    private void setRecentlyScannedItem(Item newItem){
        recentlyScannedItem = newItem;
    }

    private void addRecentlyScannedItemToTheItemList(){
        listOfItems.add(recentlyScannedItem);
    }

    private void updateRunningTotal(){
        runningTotal = runningTotal.plus(recentlyScannedItem.getPriceWithVAT());
    }

    private SaleDTO createSaleInformationReturnedToView(){
        return new SaleDTO(runningTotal, recentlyScannedItem);
    }

    private Item checkForExistingItem(ItemDTO foundItem){
        for (Item item : listOfItems){
            if (foundItem.getBarcode() == item.getBarcode())
                return item;
        }
        return null;
    }
}
