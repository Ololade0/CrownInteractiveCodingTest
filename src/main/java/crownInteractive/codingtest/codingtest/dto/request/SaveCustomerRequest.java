package crownInteractive.codingtest.codingtest.dto.request;

import crownInteractive.codingtest.codingtest.dao.model.BillingDetails;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SaveCustomerRequest {

    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
    private BillingDetails billingDetails;




}
