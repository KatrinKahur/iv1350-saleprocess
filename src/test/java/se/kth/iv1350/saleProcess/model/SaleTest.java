package se.kth.iv1350.saleProcess.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.saleProcess.integration.ItemDTO;
import se.kth.iv1350.saleProcess.integration.ItemIdentifier;
import se.kth.iv1350.saleProcess.integration.SaleDTO;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {

    private final ItemDTO itemDTOYoghurt = new ItemDTO("yoghurt", new Amount(27), 20, new ItemIdentifier(3));
    private final ItemDTO itemDTOIceCream = new ItemDTO("Strawberry ice cream", new Amount(39), 20, new ItemIdentifier(14));
    private Sale saleInstance;
    private Item itemInstanceYoghurt;
    private Item itemInstanceIceCream;

    @BeforeEach
    void setUp() {
        saleInstance = new Sale();
        itemInstanceYoghurt = new Item(itemDTOYoghurt);
        itemInstanceIceCream = new Item(itemDTOIceCream);

    }

    @AfterEach
    void tearDown() {
        saleInstance = null;
        itemInstanceYoghurt = null;
        itemInstanceIceCream = null;
    }

    @Test
    void testRunningTotalZero() {
        Amount expRunningTotal = new Amount();
        Amount runningTotal = saleInstance.getRunningTotal();
        assertEquals(expRunningTotal, runningTotal, "Running total is not 0 at the sale start.");
    }

    @Test
    void testGetRecentlyScannedItem() {
        saleInstance.registerItem(itemDTOYoghurt);
        Item expResult = itemInstanceYoghurt;
        Item result = saleInstance.getRecentlyScannedItem();
        assertEquals(expResult, result, "The recently registered item is not correct.");
    }

    @Test
    void runningTotalUpdatedAfterRegisteringANewItem() {
        saleInstance.registerItem(itemDTOYoghurt);
        itemInstanceYoghurt.calculatePriceWithVAT();
        Amount expRunningTotal = itemInstanceYoghurt.getPriceWithVAT();
        Amount runningTotal = saleInstance.getRunningTotal();
        assertEquals(expRunningTotal, runningTotal, "Running total is not correct.");
    }

    @Test
    void testUpdatingQuantityIfItemAlreadyExistsInTheSale() {
        saleInstance.registerItem(itemDTOYoghurt);
        saleInstance.registerItem(itemDTOYoghurt);
        int expItemQuantityAfterRegisteringTheItem = 2;
        int itemQuantityAfterRegisteringTheItem = saleInstance.getRecentlyScannedItem().getQuantity();
        assertEquals(expItemQuantityAfterRegisteringTheItem, itemQuantityAfterRegisteringTheItem, "Item quantity is not updated correctly.");
    }

    @Test
    void testTheReturnedSaleInformationAfterRegisteringANewItem(){
        itemInstanceIceCream.calculatePriceWithVAT();
        SaleDTO expResult = new SaleDTO(itemInstanceIceCream.getPriceWithVAT(), itemInstanceIceCream);
        SaleDTO result = saleInstance.registerItem(itemDTOIceCream);
        assertEquals(expResult, result, "The returned SaleDTO is not correct.");
    }

}