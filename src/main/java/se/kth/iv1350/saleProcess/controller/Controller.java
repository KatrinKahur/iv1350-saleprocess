package se.kth.iv1350.saleProcess.controller;

import se.kth.iv1350.saleProcess.integration.*;
import se.kth.iv1350.saleProcess.model.Amount;
import se.kth.iv1350.saleProcess.model.CashRegister;
import se.kth.iv1350.saleProcess.model.Sale;

/**
 * All method calls from the view to the model are passed through this class.
 */
public class Controller {
    private final Printer printer;
    private final CashRegister cashRegister;
    private final Inventory inventory;
    private final SaleCatalog saleCatalog;
    private final Accounting accounting;
    private Sale currentSale;

    /**
     * Creates a new instance of the Controller class.
     * @param creator Used get all registries that are used in the program.
     */
    public Controller(CatalogCreator creator){

        this.inventory = creator.getInventory();
        this.saleCatalog = creator.getSaleCatalog();
        this.accounting = creator.getAccounting();
        this.printer = new Printer();
        this.cashRegister = new CashRegister();
    }

    /**
     * This method that starts a new sale.
     */
    public void startSale(){
        currentSale = new Sale();
    }

    /**
     * This method registers a newly scanned item.
     * @param identifier Used to get the barcode of the newly scanned item
     * @return <code>String</code>> including information about item name, price and running total
     */
    public String registerItem(ItemIdentifier identifier){
        ItemDTO foundItem = inventory.searchItem(identifier);
        SaleDTO saleInformation = currentSale.registerItem(foundItem);
        return saleInformation.toString();
    }

    /**
     * This method makes the correct system calls to the model to end the sale.
     * @return The total price of the sale.
     */
    public Amount endSale(){
        Amount totalPrice = currentSale.endSale();
        return totalPrice;
    }

    /**
     * This class makes the correct system calls to the model to register payment
     * @param cashPayment Paid amount
     * @return String representation of the receipt
     */
    public String pay(Amount cashPayment){

        currentSale.registerPayment(cashRegister, cashPayment);
        String receipt = currentSale.printReceipt(printer);
        inventory.sendSaleToInventory(currentSale);
        accounting.sendSaleToAccounting(currentSale);
        saleCatalog.logSale(currentSale);
        return receipt;
    }

}
