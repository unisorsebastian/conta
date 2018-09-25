package ro.jmind.app;

import org.junit.Ignore;
import org.junit.Test;
import ro.jmind.model.BigDecimalWrapper;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class BigDecimalTest {
    @Ignore
    @Test
    public void bigDecimalWrapperTest() {
        BigDecimal bd1 = new BigDecimal("3.21");
        BigDecimal bd2 = new BigDecimal("3.210");
        int precision1 = bd1.precision();
        int precision2 = bd2.precision();

        BigDecimalWrapper bdw1 = new BigDecimalWrapper(bd1);
        BigDecimalWrapper bdw2 = new BigDecimalWrapper("3.21");

        assertNotEquals(precision1, precision2);
        assertNotEquals(bd1, bd2);

        assertEquals(bdw1, bdw2);
        assertEquals(bdw1.getBigDecimal().precision(), bdw2.getBigDecimal().precision());


    }
}
