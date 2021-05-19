package se.kth.iv1350.saleProcess.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.saleProcess.integration.CatalogCreator;
import se.kth.iv1350.saleProcess.integration.Inventory;
import se.kth.iv1350.saleProcess.integration.ItemDTO;
import se.kth.iv1350.saleProcess.integration.ItemIdentifier;

import static org.junit.jupiter.api.Assertions.*;

class PaymentInformationTest {
    private PaymentInformation paymentInformation;
    private Discount discount;

    @BeforeEach
    void setUp() {
        paymentInformation = new PaymentInformation();
        discount = new Discount();

    }

    @AfterEach
    void tearDown() {
        paymentInformation = null;
        discount = null;
    }

    @Test
    void testRunningTotalCorrectlyUpdated() {
       ItemDTO itemDTO = new ItemDTO("Milk", new Amount(25), 25, new ItemIdentifier(1));
       ItemDTO anotherItemDTO = new ItemDTO("Pasta", new Amount(14), 12, new ItemIdentifier(2));
       Item item = new Item(itemDTO);
       Item anotherItem = new Item(anotherItemDTO);
       Amount expResult = item.getPriceWithVAT().plus(anotherItem.getPriceWithVAT());
       paymentInformation.updateRunningTotal(item);
       paymentInformation.updateRunningTotal(anotherItem);
       Amount result = paymentInformation.getRunningTotal();
       assertEquals(expResult, result, "The calculated running total is not correct.");
    }

    @Test
    void testApplyDiscount() {
        Amount discountAmount = new Amount(20);
        discount.setDiscountAmount(discountAmount);
        ItemDTO itemDTO = new ItemDTO("Milk", new Amount(25), 25, new ItemIdentifier(1));
        Item item = new Item(itemDTO);
        paymentInformation.updateRunningTotal(item);
        paymentInformation.applyDiscount(discount);
        Amount expResult = item.getPriceWithVAT().minus(discountAmount);
        Amount result = paymentInformation.getRunningTotal();
        assertEquals(expResult, result, "Discount is not applied correctly.");

    }

    @Test
    void testRegisteringPaidAmount() {
        CashRegister cashRegister = new CashRegister();
        Amount paidAmount = new Amount(45);
        paymentInformation.pay(paidAmount, cashRegister);
        Amount expResult = paidAmount;
        Amount result = paymentInformation.getPaidAmount();
        assertEquals(expResult, result, "Paid amount is not registered.");
    }

    @Test
    void testGettingChange(){
        CashRegister cashRegister = new CashRegister();
        ItemDTO itemDTO = new ItemDTO("Milk", new Amount(25), 25, new ItemIdentifier(1));
        Item item = new Item(itemDTO);
        paymentInformation.updateRunningTotal(item);
        Amount paidAmount = new Amount(45);
        paymentInformation.pay(paidAmount, cashRegister);
        Amount expResult = paidAmount.minus(item.getPriceWithVAT());
        Amount result = paymentInformation.getChange();
        assertEquals(expResult, result, "The returned change is not correct.");
    }
}