package de.fx.spockqm.pages

import de.fx.spockqm.modules.Bottomnavigation
import de.fx.spockqm.modules.Topnavigation
import geb.Page

/**
 * Web, Automation & Mobile Testing page
 */
class WebAutomationMobileTestingPage extends Page {
    static at = { title == "Web, Automation & Mobile Testing | QualityMinds" }
    static content = {
        maincontent { $("#main-content") }
        topnavigation { module Topnavigation}
        bottomnavigation { module Bottomnavigation}
        tab_mobile { $("#team-tab-three-title-desktop")}
        tab_mobile_content { $("#team-tab-three-body")}
        a_flyer { $("a[download='FLYER FIND THE BUG SESSION']")}
    }
}
