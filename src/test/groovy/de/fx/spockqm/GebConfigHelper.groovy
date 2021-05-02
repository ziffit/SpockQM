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
     * Check is needed, if a single Test is started directly from the IDE.
     * @param defaultValue default-value to use
     */
    static checkHeadless(boolean defaultValue) {
        def headless = System.getProperty("headless")
        if (headless == null) {
            System.setProperty("headless", defaultValue.toString())
        }
    }

    /**
     * Checks if System-Property for Driver set, if not sets the default value as System-Property.
     * Check is needed, if a single Test is started directly from the IDE.
     * @param defaultValue default-value to use
     */
    static checkDriver(String defaultValue) {
        def driver = System.getProperty("driver.active")
        if (driver == null) {
            System.setProperty("driver.active", defaultValue)
        }
    }

    /**
     * Creates Driver-Specific Reports-Directory, and sets spock-reports System-Property for this directory.
     */
    static handleReportsDir() {
        def reportsDir = 'target/spock-reports/' + System.getenv("driver.active")
        System.setProperty("com.athaydes.spockframework.report.outputDir", reportsDir)
        return reportsDir
    }
    /**
     * Creates Webdriver.
     * System-Properties that are read:
     *   driver.active: the driver to use. Supported Values are 'chrome' and 'firefox'.
     *   If no driver is set, chrome is used as default.
     *   driver.headless: defines if the test is executes headless
     */
    static createDriver() {
        def webdriver
        switch (System.getenv("driver.active")) {
            case "firefox":
                webdriver = createFirefoxDriver()
                break
            default:
                webdriver = createChromeDriver()
        }
        webdriver.manage().window().setSize(new Dimension(1024, 800))
        return webdriver
    }

    private static createFirefoxDriver() {
        return new FirefoxDriver(new FirefoxOptions()
                .setHeadless(Boolean.valueOf(System.getProperty("driver.headless"))))
    }

    private static createChromeDriver() {
                def arguments = [
                "--disable-extensions",
                "--no-sandbox"
        ]
        return new ChromeDriver(new ChromeOptions()
                .addArguments(arguments)
                .setHeadless(Boolean.valueOf(System.getProperty("driver.headless"))))
    }
}