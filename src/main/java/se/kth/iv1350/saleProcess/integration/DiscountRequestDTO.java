package se.kth.iv1350.saleProcess.integration;

/**
 * The DTO class that gathers information needed to find matching discount.
 */
public class DiscountRequestDTO {
    private final SaleDTO saleDTO;
    private final CustomerDTO customerDTO;

    /**
     * Creates a new instance of <code>DiscountRequestDTO</code>
     * @param saleDTO The specified <code>SaleDTO</code> that has information about current sale
     * @param customerDTO Customer eligible for discount
     */
    public DiscountRequestDTO(SaleDTO saleDTO, CustomerDTO customerDTO){
        this.saleDTO = saleDTO;
        this.customerDTO = customerDTO;
    }

    /**
     * @return The <code>saleDTO</code> object.
     */
    public SaleDTO getSaleDTO(){
        return saleDTO;
    }

    /**
     * @return The <code>customerDTO</code> object
     */
    public CustomerDTO getCustomerDTO(){
        return customerDTO;
    }
}
