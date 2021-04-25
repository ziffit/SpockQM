package de.fx.spockqm.pages

import de.fx.spockqm.modules.NavigationModule
import geb.Page

class KontaktPage extends Page {
    static url = ""
    static at = { title == "Kontakt & Anfahrt | QualityMinds" }
    static content = {
        dummy { $("p").first() }
        navigation { module NavigationModule}
    }
}
