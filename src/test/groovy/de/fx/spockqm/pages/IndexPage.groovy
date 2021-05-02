package de.fx.spockqm.pages

import de.fx.spockqm.modules.Bottomnavigation
import de.fx.spockqm.modules.Topnavigation
import geb.Page
import org.openqa.selenium.Cookie

/**
 * Indexpage
 */
class IndexPage extends Page {
    static url = "/"
    static at = {
        title == "Qualitätssicherung in agiler Softwareentwicklung | QualityMinds"
    }
    static content = {
        topnavigation { module Topnavigation }
        bottomnavigation { module Bottomnavigation }
        layer_cookies(required: false) { $("div[aria-layer=cookieconsent]") }
        button_acceptCookies(required: false) { layer_cookies.find("a.cc-btn.cc-allow") }
    }
}
