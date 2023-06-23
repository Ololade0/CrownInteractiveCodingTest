package crownInteractive.codingtest.codingtest.dao.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document("BillingDetails")
public class BillingDetails {
    @Id
    private String billingId;
    private String accountNumber;
    private String tarrif;
}
