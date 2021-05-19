package se.kth.iv1350.saleProcess.model;


import org.junit.jupiter.api.Test;
import se.kth.iv1350.saleProcess.integration.ItemDTO;
import se.kth.iv1350.saleProcess.integration.ItemIdentifier;

import static org.junit.jupiter.api.Assertions.*;

class CashRegisterTest {


    @Test
    void testThatChangeIsCalculatedCorrectly() {
        CashRegister cashRegister = new CashRegister();
        Sale saleInstance = new Sale();
        ItemDTO itemDTO = new ItemDTO("milk", new Amount(45), 20, new ItemIdentifier(28));
        saleInstance.registerItem(itemDTO);
        Amount paidAmt = new Amount(100);
        saleInstance.registerPayment(cashRegister, paidAmt);
        Amount expResult = paidAmt.minus(saleInstance.getPaymentInformation().getRunningTotal());
        Amount result = cashRegister.getChange(saleInstance.getPaymentInformation());
        assertEquals(expResult, result, "Change was not calculated correctly");
    }


}