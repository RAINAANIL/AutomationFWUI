package Module1;

import core.Browser;
import core.Config;
import org.testng.annotations.Listeners;

import java.io.File;
import java.io.IOException;

@Listeners(Listener.TestListeners.class)
public class TestUtils extends Browser {


    String moduleName;

    public void setup() {
        initialiseBrowser();
        launchUrl();
    }

    public void launchUrl() {

        try {
            moduleName = new File(".").getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String url = Config.getValue("ModuleA" + "url");
        driver.get(url);

    }

}
