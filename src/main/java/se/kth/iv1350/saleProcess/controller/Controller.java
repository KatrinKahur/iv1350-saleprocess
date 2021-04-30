package se.kth.iv1350.saleProcess.controller;

import se.kth.iv1350.saleProcess.integration.*;
import se.kth.iv1350.saleProcess.model.CashRegister;

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
    private DiscountCatalog discCatalog;

    /**
     * Creates a new instance of the Controller class.
     * @param creator Used get all registries that are used in the program.
     */
    public Controller(CatalogCreator creator){
        this.inventory = creator.getInventory();
        this.saleCatalog = creator.getSaleCatalog();
        this.customerCatalog = creator.getCustomerCatalog();
        this.accounting = creator.getAccounting();
        this.discCatalog = creator.getDiscountCatalog();
        this.printer = new Printer();
        this.cashRegister = new CashRegister();
    }

    public void startSale(){

    }

}
