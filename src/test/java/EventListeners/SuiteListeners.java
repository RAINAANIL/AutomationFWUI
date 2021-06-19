package EventListeners;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListeners implements ISuiteListener {
Logger log= LogManager.getLogger(SuiteListeners.class);
    public void onStart(ISuite suite) {
        log.info("Suite Started "+ suite.getName());

    }

    public void onFinish(ISuite suite) {
        log.info("Suite Started "+ suite.getName());

    }
}
