package ro.jmind.app;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;


@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class ContabApplicationTests {

    @Value("${app.context.type}")
    private String contextName;


    @Test
    public void contextLoads() {
        assertTrue(1 == 1);
        assertTrue("testContext".equalsIgnoreCase(contextName));
    }


}
