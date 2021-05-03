package se.kth.iv1350.saleProcess.controller;

import se.kth.iv1350.saleProcess.integration.*;
import se.kth.iv1350.saleProcess.model.Amount;
import se.kth.iv1350.saleProcess.model.CashRegister;
import se.kth.iv1350.saleProcess.model.DiscountCalculator;
import se.kth.iv1350.saleProcess.model.Sale;

/**
 * All method calls from the view to the model are passed through this class.
 */
public class Controller {
    private Printer printer;
    private CashRegister cashRegister;
    private Inventory inventory;
    private SaleCatalog saleCatalog;
    private CustomerCatalog customerCatalog;
    private Accounting accounting;
    private DiscountCalculator discCalculator;
    private Sale currentSale;

    /**
     * Creates a new instance of the Controller class.
     * @param creator Used get all registries that are used in the program.
     */
    public Controller(CatalogCreator creator){

        this.inventory = creator.getInventory();
        this.saleCatalog = creator.getSaleCatalog();
        this.customerCatalog = creator.getCustomerCatalog();
        this.accounting = creator.getAccounting();
        this.printer = new Printer();
        this.cashRegister = new CashRegister();
    }

    /**
     * The method that starts a new sale.
     */
    public void startSale(){
        currentSale = new Sale();
        discCalculator = new DiscountCalculator();
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
     * This method handles the discount request by making the correct system calls to find a matching discount and update the total price
     * @param searchedCustomer <code>CustomerDTO</code> that is used to get the membership level
     * @return The total price of the sale after applying the discounts
     */
    public Amount handleDiscountRequest(CustomerDTO searchedCustomer){
        CustomerDTO foundCustomer = customerCatalog.searchCustomer(searchedCustomer);
        Amount calculatedDiscount = discCalculator.calculateDiscount(currentSale, foundCustomer);
        currentSale.applyDiscount(calculatedDiscount);
        return currentSale.getTotalPrice();
    }

    public String pay(Amount cashPayment){
        Amount change = cashRegister.addPayment(cashPayment, currentSale);
       return currentSale.registerPayment(cashPayment, change);
    }



}
