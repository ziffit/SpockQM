import static de.fx.spockqm.GebConfigHelper.createDriver
import static de.fx.spockqm.GebConfigHelper.handleReportsDir
import static de.fx.spockqm.GebConfigHelper.initHeadless


initHeadless(true)

reportsDir = handleReportsDir()

baseUrl = "https://qualityminds.de/"

driver = { createDriver() }
