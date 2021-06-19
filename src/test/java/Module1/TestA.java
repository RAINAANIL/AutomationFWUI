package Module1;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
public class TestA extends TestUtils{

    @BeforeClass
    public void setup()
    {
        super.setup();
    }

    @Test
    public void test()
    {
        System.out.println("Jai Ganesh");
    }
}
