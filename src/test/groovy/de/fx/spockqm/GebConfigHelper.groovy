package de.fx.spockqm


import org.openqa.selenium.Dimension
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
/**
 * Helper for creating Webdriver.
 * Helper is easy to Debug (GebConfig is not debuggable).
 * Default:
 *   driver: Chrome
 *   headless: false
 */
class GebConfigHelper {

    /**
     * Creates Driver-Specific Reports-Directory, and sets spock-reports System-Property for this directory.
     * @return
     */
    static handleReportsDir(){
        def reportsDir = 'target/spock-reports/'+System.getenv("driver.active")
        System.setProperty("com.athaydes.spockframework.report.outputDir",reportsDir)
        println ("***** $reportsDir")
        return  reportsDir
    }
    /**
     * Creates Webdriver.
     * System-Properties that are read:
     *   driver: the driver to use. Supported Values are 'chrome' and 'firefox'.
     */
    static createDriver() {
        def activeDriver = Objects.requireNonNullElse(System.getenv("driver.active"),"default")
        println("===> activeDriver: $activeDriver")
        def headless = Boolean.valueOf(System.getProperty("driver.headless", "false"))
        def webdriver
        switch (activeDriver) {
            case "firefox":
                webdriver = createFirefoxDriver(headless)
                break;
            default:
                webdriver = createChromeDriver(headless)
        }
        webdriver.manage().window().setSize(new Dimension(1024, 650))
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