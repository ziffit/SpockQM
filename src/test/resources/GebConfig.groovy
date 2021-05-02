import static de.fx.spockqm.GebConfigHelper.createDriver
import static de.fx.spockqm.GebConfigHelper.handleReportsDir
import static de.fx.spockqm.GebConfigHelper.*


checkHeadless(false)
checkDriver("chrome")

reportsDir = handleReportsDir()

baseUrl = "https://qualityminds.de/"
autoClearCookies = false
driver = { createDriver() }
