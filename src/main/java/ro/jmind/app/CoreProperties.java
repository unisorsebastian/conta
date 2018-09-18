package ro.jmind.app;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties("core")
public class CoreProperties {
    private String coreInvoiceDefaultSeries;

}
