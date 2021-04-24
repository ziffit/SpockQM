import static de.fx.spockqm.GebConfigHelper.createDriver
import static de.fx.spockqm.GebConfigHelper.handleReportsDir

reportsDir = handleReportsDir()
baseUrl = "https://qualityminds.de/"

driver = { createDriver() }