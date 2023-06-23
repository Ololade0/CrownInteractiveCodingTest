package crownInteractive.codingtest.codingtest.service;

import crownInteractive.codingtest.codingtest.dao.model.BillingDetails;
import crownInteractive.codingtest.codingtest.dao.model.Customer;
import crownInteractive.codingtest.codingtest.dao.repository.CustomerRepository;
import crownInteractive.codingtest.codingtest.dto.request.SaveCustomerRequest;
import crownInteractive.codingtest.codingtest.dto.response.SaveCustomerResponse;
import crownInteractive.codingtest.codingtest.exception.CustomerAlreadyExistException;
import crownInteractive.codingtest.codingtest.exception.CustomerCannotBeFound;
import crownInteractive.codingtest.codingtest.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BillingService billingService;

    @Autowired
    private Utils utils;


    BillingDetails billingDetails;
    Customer savedCustomer;
    @Override
    public SaveCustomerResponse saveNewCustomer(SaveCustomerRequest saveCustomerRequest) throws CustomerAlreadyExistException {
        Optional<Customer> existingCustomer = customerRepository.findCustomerByEmail(saveCustomerRequest.getEmail());
//                if(existingCustomer.isPresent()){
//                    throw new CustomerAlreadyExistException("Customer with" + existingCustomer + " already exist");
//                }
        Customer customer = Customer.builder()
                .email(saveCustomerRequest.getEmail())
                .firstName(saveCustomerRequest.getFirstName())
                .lastName(saveCustomerRequest.getLastName())
                .billingDetails(saveCustomerRequest.getBillingDetails())
                .build();
        billingDetails = billingService.saveBillingDetails(saveCustomerRequest.getBillingDetails());
        customer.setBillingDetails(billingDetails);
        savedCustomer = customerRepository.save(customer);
        return response(savedCustomer);

    }
    public SaveCustomerResponse response(Customer customer) {
        return SaveCustomerResponse.builder()
                .message("Customer successfully saved")
                .customerId(savedCustomer.getCustomerId())
                .firstName(savedCustomer.getFirstName())
                .email(savedCustomer.getEmail())
                .customerId(savedCustomer.getCustomerId())
                .billingDetails(BillingDetails.builder()
                        .tarrif(billingDetails.getTarrif())
                        .accountNumber(utils.generateAccountNumber())
                        .billingId(billingDetails.getBillingId())
                        .build())
                .build();

    }

    @Override
    public Customer retriveCustomerById(String customerId) throws CustomerCannotBeFound {
        Optional<Customer> foundCustomer = customerRepository.findCustomerByCustomerId(customerId);
        if (foundCustomer.isPresent()) {
            return foundCustomer.get();
        }
        else {
            throw new CustomerCannotBeFound("Customer with " + customerId + "cannot ne found");

        }
    }

    @Override
    public Customer retriveCustomerByName(String firstName) throws CustomerCannotBeFound {
        Optional<Customer> foundCustomer = customerRepository.findCustomerByFirstName(firstName);
        if (foundCustomer.isPresent()) {
            return foundCustomer.get();
        }
        else {
            throw new CustomerCannotBeFound("Customer with " + firstName + "cannot me found");

        }
    }

    @Override
    public Customer retriveCustomerByEmail(String email) throws CustomerCannotBeFound {
        Optional<Customer> foundCustomer = customerRepository.findCustomerByEmail(email);
        if (foundCustomer.isPresent()) {
            return foundCustomer.get();
        }
        else {
            throw new CustomerCannotBeFound("Customer with " + email + "cannot ne found");

        }
    }

    @Override
    public List<Customer> retriveAllCustomers() {
        return customerRepository.findAll();

    }

    @Override
    public void deleteAll() {
        customerRepository.deleteAll();
    }
}
