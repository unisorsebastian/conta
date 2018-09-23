package ro.jmind.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public final class BigDecimalWrapper {
    private BigDecimal bigDecimal;

    public BigDecimalWrapper(String val) {
        this.bigDecimal = new BigDecimal(val).setScale(4, RoundingMode.HALF_EVEN);
    }

    public BigDecimalWrapper(BigDecimal val) {
        String bigDecimal = val.setScale(4, RoundingMode.HALF_EVEN).toString();
        this.bigDecimal = new BigDecimal(bigDecimal).setScale(4, RoundingMode.HALF_EVEN);
    }
}
