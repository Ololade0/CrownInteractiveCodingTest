package crownInteractive.codingtest.codingtest.service;

import crownInteractive.codingtest.codingtest.dao.model.Customer;
import crownInteractive.codingtest.codingtest.dto.request.SaveCustomerRequest;
import crownInteractive.codingtest.codingtest.dto.response.SaveCustomerResponse;
import crownInteractive.codingtest.codingtest.exception.CustomerAlreadyExistException;
import crownInteractive.codingtest.codingtest.exception.CustomerCannotBeFound;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {
    SaveCustomerResponse saveNewCustomer(SaveCustomerRequest saveCustomerRequest) throws CustomerAlreadyExistException;
    Customer retriveCustomerById(String customerId) throws CustomerCannotBeFound;
    Customer retriveCustomerByName(String firstName) throws CustomerCannotBeFound;
    Customer retriveCustomerByEmail(String email) throws CustomerCannotBeFound;

    List<Customer> retriveAllCustomers();

    void deleteAll();
}
