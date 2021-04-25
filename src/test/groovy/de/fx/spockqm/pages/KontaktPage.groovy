package de.fx.spockqm.pages

import de.fx.spockqm.modules.Bottomnavigation
import de.fx.spockqm.modules.Topnavigation
import geb.Page

class KontaktPage extends Page {
    static url = ""
    static at = { title == "Kontakt & Anfahrt | QualityMinds" }
    static content = {
        maincontent { $("#main-content") }
        topnavigation { module Topnavigation}
        bottomnavigation { module Bottomnavigation}
    }
}
