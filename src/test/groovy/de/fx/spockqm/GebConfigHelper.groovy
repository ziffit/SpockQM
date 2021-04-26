package de.fx.spockqm


import org.openqa.selenium.Dimension
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
/**
 * Helper for creating GebConfig.
 * Helper is easy to Debug (GebConfig is not debuggable).
 */
class GebConfigHelper {

    /**
     * Checks if System-Property is set, if not sets the default value as System-Property.
     * @param defaultValue default-value to use
     */
    static initHeadless(boolean defaultValue){
        def headless = System.getProperty("headless")
        if(headless==null){
            System.setProperty("headless",defaultValue.toString())
        }
    }

    /**
     * Creates Driver-Specific Reports-Directory, and sets spock-reports System-Property for this directory.
     */
    static handleReportsDir(){
        def reportsDir = 'target/spock-reports/'+System.getenv("driver.active")
        System.setProperty("com.athaydes.spockframework.report.outputDir",reportsDir)
        return  reportsDir
    }
    /**
     * Creates Webdriver.
     * System-Properties that are read:
     *   driver.active: the driver to use. Supported Values are 'chrome' and 'firefox'.
     *   If no driver is set, chrome is used as default.
     *   driver.headless: defines if the test is executes headless
     */
    static createDriver() {
        def activeDriver = Objects.requireNonNullElse(System.getenv("driver.active"),"default")
        def headless = Boolean.valueOf(System.getProperty("driver.headless"))
        def webdriver
        switch (activeDriver) {
            case "firefox":
                webdriver = createFirefoxDriver(headless)
                break
            default:
                webdriver = createChromeDriver(headless)
        }
        webdriver.manage().window().setSize(new Dimension(1024, 800))
        return webdriver
    }

    private static createFirefoxDriver(boolean headless) {
        return new FirefoxDriver(new FirefoxOptions().setHeadless(headless))
    }

    private static createChromeDriver(boolean headless) {
        def arguments = [
                "--disable-extensions",
                "--no-sandbox"
        ]
        return new ChromeDriver(new ChromeOptions().addArguments(arguments).setHeadless(headless))
    }
}