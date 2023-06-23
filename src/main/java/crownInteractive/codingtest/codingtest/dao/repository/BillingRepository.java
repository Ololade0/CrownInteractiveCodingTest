package crownInteractive.codingtest.codingtest.dao.repository;

import crownInteractive.codingtest.codingtest.dao.model.BillingDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingRepository extends MongoRepository<BillingDetails, String> {
}
