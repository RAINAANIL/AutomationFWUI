package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class Browser {

    protected WebDriver driver;
    String browser;


    public void initialiseBrowser() {
        Config.setPropertyPath();
        browser = System.getProperty("browser", Config.getValue("browser"));

        if (browser.equalsIgnoreCase("chrome") || browser.equalsIgnoreCase("ch")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox") || browser.equalsIgnoreCase("ff")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("ie")) {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
        } else if (browser.startsWith("cloud")) {
            if (browser.contains("desktop")) {

            } else if (browser.contains("mobile")) {

            } else {
                try {
                    throw new Exception("Invalid Cloud Device is Provided");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
