package crownInteractive.codingtest.codingtest.dto.response;

import crownInteractive.codingtest.codingtest.dao.model.BillingDetails;
import lombok.*;

@Data
@Builder
public class SaveCustomerResponse {
  private String customerId;
  private String email;
  private String firstName;
  private String message;
  private BillingDetails billingDetails;

}
