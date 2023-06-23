package crownInteractive.codingtest.codingtest.dao.repository;

import crownInteractive.codingtest.codingtest.dao.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
    Optional<Customer>findCustomerByCustomerId(String customerId);
    Optional<Customer>findCustomerByEmail(String email);
    Optional<Customer>findCustomerByFirstName(String name);
}
