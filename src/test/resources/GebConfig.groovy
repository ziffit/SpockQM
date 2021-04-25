import geb.Browser
import geb.Page
import geb.PageEventListenerSupport

import static de.fx.spockqm.GebConfigHelper.createDriver
import static de.fx.spockqm.GebConfigHelper.handleReportsDir

reportsDir = handleReportsDir()
baseUrl = "https://qualityminds.de/"

driver = { createDriver() }
