package Module1;


import core.Reporting;
import org.assertj.core.api.SoftAssertions;
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
        Reporting.info("Jai Ganesh");
        assertThat(true).isTrue();
    }


    @Test
    public void testB() {
        Reporting.info("Test B");
        assertThat("This string").doesNotContainOnlyWhitespaces();
    }

    @Test
    public void testC() {
        Reporting.info("Test C");
        assertThat(false).isTrue();
    }

    @Test
    public void testD() {
        Reporting.info("Test D");
        assertThat(getThreadedWebDriver().getCurrentUrl()).isEqualToIgnoringWhitespace("https://phptravels.com/demo/");
    }


    @Test
    public void testE() {
        Reporting.info("Test E for soft assertions");
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(getThreadedWebDriver().getCurrentUrl()).isEqualToIgnoringWhitespace("https://phptravels.com/demo/");
        softly.assertThat(5).isGreaterThan(4);
        softly.assertThat(4).isLessThan(3);
        softly.assertThat("A").isEqualTo("a");
        softly.assertAll();

    }

    @AfterMethod()
    public void tearDown() {
        super.tearDown();
    }

}
