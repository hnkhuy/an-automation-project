import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class FirstRPTest {
    private static final Logger LOGGER = LogManager.getLogger(FirstRPTest.class);

    @Test
    public void testMySimpleTest() {
        LOGGER.info("Hello from my simple test");
    }
}
