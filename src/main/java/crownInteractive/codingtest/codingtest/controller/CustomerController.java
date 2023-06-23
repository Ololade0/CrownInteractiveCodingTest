package crownInteractive.codingtest.codingtest.controller;

import crownInteractive.codingtest.codingtest.dao.model.Customer;
import crownInteractive.codingtest.codingtest.dto.request.SaveCustomerRequest;
import crownInteractive.codingtest.codingtest.dto.response.SaveCustomerResponse;
import crownInteractive.codingtest.codingtest.exception.CustomerAlreadyExistException;
import crownInteractive.codingtest.codingtest.exception.CustomerCannotBeFound;
import crownInteractive.codingtest.codingtest.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/customer")
    public ResponseEntity<?> saveCustomer(@RequestBody SaveCustomerRequest saveCustomerRequest) throws CustomerAlreadyExistException {
    SaveCustomerResponse saveCustomerResponse = customerService.saveNewCustomer(saveCustomerRequest);
    return new ResponseEntity<>(saveCustomerResponse, HttpStatus.OK);
}


    @GetMapping("retrieve/{customerId}")
    public ResponseEntity<?> retrieveCustomerById(@PathVariable String customerId) throws CustomerCannotBeFound {
        Customer foundCustomer =  customerService.retriveCustomerById(customerId);
        return new ResponseEntity<>(foundCustomer, HttpStatus.OK);
 }


    @GetMapping("find/{email}")
    public ResponseEntity<?> retrieveCustomerByEmail(@PathVariable String email) throws CustomerCannotBeFound {
        Customer foundCustomer =  customerService.retriveCustomerByEmail(email);
        return new ResponseEntity<>(foundCustomer, HttpStatus.OK);
    }


    @GetMapping("customer/{name}")
    public ResponseEntity<?> retrieveCustomerByName(@PathVariable String name) throws CustomerCannotBeFound {
        Customer foundCustomer =  customerService.retriveCustomerByName(name);
        return new ResponseEntity<>(foundCustomer, HttpStatus.OK);
    }

    @GetMapping("/findAll")
        public ResponseEntity<?> retrieveAllCustomer() throws CustomerCannotBeFound {

            List<Customer> customerPage = customerService.retriveAllCustomers();
            return new ResponseEntity<>(customerPage, HttpStatus.OK);
        }



}
