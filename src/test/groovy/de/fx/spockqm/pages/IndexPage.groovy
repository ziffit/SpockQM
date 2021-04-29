package de.fx.spockqm.pages

import de.fx.spockqm.modules.Bottomnavigation
import de.fx.spockqm.modules.Topnavigation
import geb.Page

/**
 * Indexpage
 */
class IndexPage extends Page {
    static url = ""
    static at = { title == "Qualitätssicherung in agiler Softwareentwicklung | QualityMinds" }
    static content = {
        topnavigation { module Topnavigation}
        bottomnavigation { module Bottomnavigation}
    }
}
