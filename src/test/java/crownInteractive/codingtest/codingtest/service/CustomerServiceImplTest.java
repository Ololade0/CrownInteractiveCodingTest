package crownInteractive.codingtest.codingtest.service;

import crownInteractive.codingtest.codingtest.dao.model.BillingDetails;
import crownInteractive.codingtest.codingtest.dao.model.Customer;
import crownInteractive.codingtest.codingtest.dto.request.SaveCustomerRequest;
import crownInteractive.codingtest.codingtest.dto.response.SaveCustomerResponse;
import crownInteractive.codingtest.codingtest.exception.CustomerAlreadyExistException;
import crownInteractive.codingtest.codingtest.exception.CustomerCannotBeFound;
import crownInteractive.codingtest.codingtest.utils.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CustomerServiceImplTest {
    SaveCustomerResponse savedCustomers;


    @Autowired
    private CustomerService customerService;

    @Autowired
    private Utils utils;

    @BeforeEach
    void setUp() throws CustomerAlreadyExistException {
        SaveCustomerRequest saveCustomerRequest = SaveCustomerRequest.builder()
                .email("eunice@gmail.com")
                .firstName("Adesuyi Oluwatosin")
                .lastName("Oluwatosin Ololade")
                .billingDetails(BillingDetails.builder()
                        .accountNumber(utils.generateAccountNumber())
                        .tarrif("Export Duty Tarrif")
                        .build())
                .build();
      savedCustomers = customerService.saveNewCustomer(saveCustomerRequest);

    }

    @AfterEach
    void tearDown() {
        customerService.deleteAll();
    }

    @Test
    void saveNewCustomer() throws CustomerAlreadyExistException {
        SaveCustomerRequest saveCustomerRequest = SaveCustomerRequest.builder()
                .email("eunice@gmail.com")
                .firstName("Bose")
                .lastName("Oluwatosin")
                .billingDetails(BillingDetails.builder()
                        .accountNumber(utils.generateAccountNumber())
                        .tarrif("Export Duty Tarrif")
                        .build())
                .build();
        savedCustomers = customerService.saveNewCustomer(saveCustomerRequest);
        assertEquals("eunice@gmail.com",savedCustomers.getEmail());
        assertEquals("Export Duty Tarrif",savedCustomers.getBillingDetails().getTarrif());
        assertNotNull(savedCustomers.getBillingDetails().getAccountNumber());
        assertNotNull(savedCustomers.getCustomerId());

    }

    @Test
    public void retriveCustomerById() throws CustomerCannotBeFound{
            Customer foundCustomer = customerService.retriveCustomerById(savedCustomers.getCustomerId());
            assertThat(foundCustomer).isNotNull();
            assertThat(foundCustomer.getCustomerId()).isEqualTo(savedCustomers.getCustomerId());

        }


    @Test
    void retriveCustomerByName() throws  CustomerCannotBeFound {
        Customer foundCustomer = customerService.retriveCustomerByName(savedCustomers.getFirstName());
        assertEquals("Adesuyi Oluwatosin", foundCustomer.getFirstName());
        assertThat(foundCustomer).isNotNull();
        assertThat(foundCustomer.getFirstName()).isEqualTo(savedCustomers.getFirstName());
    }

    @Test
    void retriveCustomerByEmail() throws CustomerCannotBeFound {
        Customer foundCustomer = customerService.retriveCustomerByEmail(savedCustomers.getEmail());
        assertEquals("eunice@gmail.com", foundCustomer.getEmail());
        assertThat(foundCustomer).isNotNull();
        assertThat(foundCustomer.getEmail()).isEqualTo(savedCustomers.getEmail());
    }


    @Test
    void retriveAllCustomers() {
     List<Customer> customerPage =  customerService.retriveAllCustomers();
        assertEquals("eunice@gmail.com",customerPage.get(0).getEmail());
        assertEquals("Adesuyi Oluwatosin",customerPage.get(0).getFirstName());

    }
}
