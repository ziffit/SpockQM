package de.fx.spockqm

import de.fx.spockqm.pages.IndexPage
import de.fx.spockqm.pages.KontaktPage
import geb.spock.GebReportingSpec

class WebsiteSpec extends GebReportingSpec {

    def activePage
    def activeDriver

    def setup(){
        activeDriver = System.getenv("driver")
        println("===> Driver: $driver")
    }


    def "dummytest"() {
        when:
        activePage = to IndexPage

        then:
        report"$activeDriver: Ohhhh what a Nice Webseite"
        $("#logo").attr("alt") == "QualityMinds"
        activePage.dummy.text() != ""

        when:
        activePage.navigation.kontakt.click()
        then:
        at KontaktPage
    }
    def "dummytest Failing"() {
        when:
        activePage = to IndexPage


        then:
        $("#logo").attr("alt") == "QualityMinds2"
        report"$activeDriver: Ohhhh Nooo"
        activePage.dummy.text() != ""

    }

}
