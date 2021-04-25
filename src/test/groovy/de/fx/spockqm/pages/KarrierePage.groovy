package de.fx.spockqm.pages

import de.fx.spockqm.modules.Bottomnavigation
import de.fx.spockqm.modules.Topnavigation
import geb.Page

class KarrierePage extends Page {
    static at = { title == "Karriere | QualityMinds" }
    static content = {
        maincontent { $("#main-content") }
        button_application {$("a", text: iContains("Bewirb dich jetzt!")).first()}
        topnavigation { module Topnavigation}
        bottomnavigation { module Bottomnavigation}
    }
}
