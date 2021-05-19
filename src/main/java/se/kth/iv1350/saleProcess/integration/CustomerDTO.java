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
     * @param membershipLevel Customers membership level. There are 3 possible membership levels:
     * bronze, silver and gold.
     */
    CustomerDTO(int customerID, String membershipLevel){
        this.customerID = customerID;
        this.membershipLevel = membershipLevel;
    }

    /**
     * This method gets the value of <code>customerID</code>
     * @return The value of <code>customerID</code>
     */
    public int getCustomerID(){
        return customerID;
    }

    /**
     * This method gets the customers membership level.
     * @return  The value of <code>membershipLevel</code>
     */
    public String getCustomerMembershipLevel(){
        return membershipLevel;
    }

    /**
     * This method compares two instances of <code>CustomerDTO</code> to determine whether they are equal.
     * @param object The specified <code>CustomerDTO</code> that is compared with this <code>CustomerDTO</code>
     * @return The method returns <code>true</code> if <code>customerID</code>s of both instances are equal, <code>false</code>
     * if they are not.
     */
    public boolean equals(Object object){
        if (object == null)
            return false;
        if (!(object instanceof CustomerDTO))
            return false;
        CustomerDTO customerToCompare = (CustomerDTO) object;

        return customerID == customerToCompare.getCustomerID();
    }
}