package se.kth.iv1350.saleProcess.integration;

/**
 * This class is used to transfer customer specific data.
 */
public class CustomerDTO {
    private final int customerID;
    private String membershipLevel;

    /**
     * Creates an instance of <code>CustomerID</code>
     * @param customerID The customer identification number
     */
    public CustomerDTO(int customerID){
        this.customerID = customerID;
    }

    /**
     * Creates an instance of <code>CustomerID</code>
     * @param customerID Customer identification number
     * @param membershipLevel Customers membership level
     */
    CustomerDTO(int customerID, String membershipLevel){
        this.customerID = customerID;
        this.membershipLevel = membershipLevel;
    }

}
