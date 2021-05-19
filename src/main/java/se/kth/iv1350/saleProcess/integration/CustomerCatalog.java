package se.kth.iv1350.saleProcess.integration;

import java.util.ArrayList;
import java.util.List;

/**
 * This class includes a list of customers.
 */
public class CustomerCatalog {

    List <CustomerDTO> customerList = new ArrayList<>();

    /**
     * Creates a new instance of <code>CustomerCatalog</code>
     */
    CustomerCatalog(){
        addCustomerList();
    }

    /**
     * This method adds 21 <code>CustomerDTO</code>s to <code>customerList</code>
     * that are later used to find the matching customer.
     */
    private void addCustomerList(){
        customerList.add(new CustomerDTO(120, "bronze"));
        customerList.add(new CustomerDTO(121, "bronze"));
        customerList.add(new CustomerDTO(122, "silver"));
        customerList.add(new CustomerDTO(123,"silver"));
        customerList.add(new CustomerDTO(124,"gold"));
        customerList.add(new CustomerDTO(125,"bronze"));
        customerList.add(new CustomerDTO(126,"silver"));
        customerList.add(new CustomerDTO(127, "bronze"));
        customerList.add(new CustomerDTO(128, "silver"));
        customerList.add(new CustomerDTO(129, "silver"));
        customerList.add(new CustomerDTO(130, "gold"));
        customerList.add(new CustomerDTO(131, "gold"));
        customerList.add(new CustomerDTO(132,"silver"));
        customerList.add(new CustomerDTO(133, "bronze"));
        customerList.add(new CustomerDTO(134, "bronze"));
        customerList.add(new CustomerDTO(135, "gold"));
        customerList.add(new CustomerDTO(136, "bronze"));
        customerList.add(new CustomerDTO(137, "silver"));
        customerList.add(new CustomerDTO(138, "bronze"));
        customerList.add(new CustomerDTO(139, "gold"));
        customerList.add(new CustomerDTO(140, "gold"));
    }

    /**
     * This method searches the matching <code>CustomerDTO</code> in <code>customerList</code>
     * @param searchedCustomer The specified <code>CustomerDTO</code> that is used to find the matching <code>CustomerDTO</code>
     * in the customer list.
     * @return <code>CustomerDTO</code> that has the same customer identification as <code>searchedCustomer</code>, <code>null</code>
     * if no such <code>CustomerDTO</code> is found.
     */
    public CustomerDTO searchCustomer(CustomerDTO searchedCustomer){
        for (CustomerDTO customer : customerList){
            if (customer.equals(searchedCustomer))
                return customer;
        }
        return null;
    }
}