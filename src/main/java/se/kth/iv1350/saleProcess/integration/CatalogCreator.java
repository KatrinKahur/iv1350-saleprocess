package se.kth.iv1350.saleProcess.integration;

/**
 * This class is the creator of all kinds of registries that are used in the program.
 */
public class CatalogCreator {
    private Inventory inventory = new Inventory();
    private SaleCatalog saleCatalog = new SaleCatalog();
    private CustomerCatalog customerCatalog = new CustomerCatalog();
    private Accounting accounting = new Accounting();
    private DiscountCatalog  discCatalog = new DiscountCatalog();

    public Inventory getInventory(){
        return inventory;
    }

    public SaleCatalog getSaleCatalog(){
        return saleCatalog;
    }

    public CustomerCatalog getCustomerCatalog(){
        return customerCatalog;
    }

    public Accounting getAccounting(){
        return accounting;
    }

    public DiscountCatalog getDiscountCatalog(){
        return discCatalog;
    }
}
