package crownInteractive.codingtest.codingtest.dao.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document("Customer")
public class Customer {
    @Id
    private String customerId;
    private String firstName;
    private String lastName;
    private String email;

    private BillingDetails billingDetails;

}
