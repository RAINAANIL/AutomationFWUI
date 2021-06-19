package Module1;


import core.Reporting;
import org.testng.annotations.*;

import static org.assertj.core.api.Assertions.*;

@Test
public class TestA extends TestUtils {

    @BeforeMethod
    public void setup() {
        super.setup();
    }

    @Test
    public void test() {
        System.out.println("Jai Ganesh");
        assertThat(true).isTrue();
    }


    @Test
    public void testB() {
        System.out.println("Test B");
        assertThat("This string").doesNotContainOnlyWhitespaces();
    }

    @Test
    public void testC() {
        System.out.println("Test C");
        assertThat(false).isTrue();
    }

    @Test
    public void testD() {
        System.out.println("Test D");
        assertThat(driver.getCurrentUrl()).isEqualToIgnoringWhitespace("https://phptravels.com/demo/");
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


}
