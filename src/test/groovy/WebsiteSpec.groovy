

import de.fx.spockqm.pages.IndexPage
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
    }

}
