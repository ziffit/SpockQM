package de.fx.spockqm.pages

import geb.Page

class IndexPage extends Page {
    static url = ""
    static at = { title == "Qualitätssicherung in agiler Softwareentwicklung | QualityMinds" }
    static content = {
        dummy { $("p").first() }
    }
}
