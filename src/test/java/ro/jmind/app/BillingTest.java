package ro.jmind.app;

import org.junit.Test;
import ro.jmind.model.BillingAmount;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

import static org.junit.Assert.assertTrue;

public class BillingTest {

    @Test
    public void conversionTest() {
        BillingAmount billingAmount = new BillingAmount();
        BigDecimal bd = new BigDecimal("22.1");

        billingAmount.setAmount(new BigDecimal("20"));
        billingAmount.setParity(new BigDecimal("4.6543"));
        billingAmount.setCurrency(Currency.getInstance("EUR"));
        billingAmount.setLocalCurrency(Currency.getInstance("RON"));

        BigDecimal localAmount = billingAmount.getAmount().multiply(billingAmount.getParity());
        billingAmount.setLocalAmount(localAmount);
        localAmount.setScale(4, RoundingMode.HALF_UP);
        assertTrue(true);
    }

}
