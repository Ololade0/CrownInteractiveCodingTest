package crownInteractive.codingtest.codingtest.utils;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


@Component
public class Utils {
    public String generateAccountNumber() {
                int maxNumber = 999999999;
        int accountNumber = new Random().nextInt(maxNumber) + 1;
        String formattedAccountNumber = String.format("%010d", accountNumber);
        return formattedAccountNumber + "-01";
    }



}

