package ro.jmind.app;

import org.junit.Test;
import ro.jmind.model.BillingAmount;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BillingTest {

    @Test
    public void conversionTest() {
        BillingAmount billingAmount = new BillingAmount();

        billingAmount.setAmount(new BigDecimal("20"));
        billingAmount.setParity(new BigDecimal("4.6543"));
        billingAmount.setCurrency(Currency.getInstance("EUR"));
        billingAmount.setLocalCurrency(Currency.getInstance("RON"));
        billingAmount.setLocalAmount(billingAmount.getAmount().multiply(billingAmount.getParity()));

        assertEquals(billingAmount.getLocalAmount(),new BigDecimal("93.0860"));
    }

}
