package ro.jmind.app;

import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static org.junit.Assert.assertEquals;

@Ignore
public class BillingTest {

//    @Test
//    public void conversionTest() {
//        BillingAmount billingAmount = new BillingAmount();
//
//        billingAmount.setBillingAmount(new BigDecimal("20"));
//        billingAmount.setParity(new BigDecimal("4.6543"));
//        billingAmount.setCurrency(Currency.getInstance("EUR"));
//        billingAmount.setLocalCurrency(Currency.getInstance("RON"));
//        billingAmount.setLocalAmount(billingAmount.getBillingAmount().multiply(billingAmount.getParity()));
//
//        assertEquals(billingAmount.getLocalAmount(),new BigDecimal("93.0860"));
//    }

    @Test
    public void localDateFramework() {
        LocalDate firstDay = LocalDate.of(2018, Month.AUGUST, 1);
        LocalDate lastDay = firstDay.with(lastDayOfMonth());
        List<LocalDate> allDays = new ArrayList<>();
        while (true) {
            allDays.add(firstDay);
            firstDay = firstDay.plusDays(1);
            if (firstDay.isEqual(lastDay)) {
                allDays.add(firstDay);
                break;
            }
        }
        assertEquals(allDays.size(), 31);

    }

}
