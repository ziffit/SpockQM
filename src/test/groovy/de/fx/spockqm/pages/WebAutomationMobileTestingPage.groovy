package de.fx.spockqm.pages

import de.fx.spockqm.modules.Bottomnavigation
import de.fx.spockqm.modules.Topnavigation
import geb.Page

class WebAutomationMobileTestingPage extends Page {
    static url = ""
    static at = { title == "Web, Automation & Mobile Testing | QualityMinds" }
    static content = {
        maincontent { $("#main-content") }
        topnavigation { module Topnavigation}
        bottomnavigation { module Bottomnavigation}
    }
}
