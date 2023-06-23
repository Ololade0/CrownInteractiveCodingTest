package crownInteractive.codingtest.codingtest.service;

import crownInteractive.codingtest.codingtest.dao.model.BillingDetails;
import crownInteractive.codingtest.codingtest.dao.repository.BillingRepository;
import crownInteractive.codingtest.codingtest.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceImpl implements BillingService {

    @Autowired
    private Utils utils;
    @Autowired
    private BillingRepository billingRepository;
    @Override
    public BillingDetails saveBillingDetails(BillingDetails billingDetails) {
        BillingDetails billingDetails1 = BillingDetails.builder()
                .accountNumber(utils.generateAccountNumber())
                .tarrif(billingDetails.getTarrif())
                .build();
        return billingRepository.save(billingDetails1);
    }
}
