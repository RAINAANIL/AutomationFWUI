package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class Browser {

    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    String browser;


    public static WebDriver getThreadedWebDriver() {
        return driver.get();
    }

    public void initialiseBrowser() {
        Config.setPropertyPath();
        browser = System.getProperty("browser", Config.getValue("browser"));

        if (browser.equalsIgnoreCase("chrome") || browser.equalsIgnoreCase("ch")) {
            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver());
        } else if (browser.equalsIgnoreCase("firefox") || browser.equalsIgnoreCase("ff")) {
            WebDriverManager.firefoxdriver().setup();
            driver.set(new FirefoxDriver());
        } else if (browser.equalsIgnoreCase("ie")) {
            WebDriverManager.iedriver().setup();
            driver.set(new InternetExplorerDriver());
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



    public static String takeScreenshot() {
        String base64Image = "";
        TakesScreenshot ts = ((TakesScreenshot) getThreadedWebDriver());
        base64Image = ts.getScreenshotAs(OutputType.BASE64);
        return base64Image;
    }
}
