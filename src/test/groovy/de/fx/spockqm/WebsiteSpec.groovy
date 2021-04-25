package de.fx.spockqm

import de.fx.spockqm.pages.IndexPage
import de.fx.spockqm.pages.KontaktPage
import de.fx.spockqm.pages.WebAutomationMobileTestingPage
import geb.spock.GebReportingSpec
import org.openqa.selenium.interactions.Actions

class WebsiteSpec extends AbstractSpec {



    def "Test Case 1"() {
        when: "the URL www.qualityminds.de is opened,"
        go "http://www.qualityminds.de"
        activePage = page IndexPage
        then: "the QualityMinds startpage is open."
        at IndexPage

        when: "'Kontakt' at the top navigation is clicked,"
        activePage = activePage.topnavigation.kontakt.click(KontaktPage)
        activePage = page KontaktPage
        then: "the Page 'Kontakt &  Anfahrt' is displayed."
        at KontaktPage

        expect: "that the page contains the email address 'hello@qualityminds.de'."
        activePage.maincontent.text() =~ /.*hello@qualityminds.de.*/

        when: "'Kontakt & Anfahrt' in the bottom navigation is clicked,"
        activePage.bottomnavigation.kontakt.click()
        then: "the Page 'Kontakt &  Anfahrt' is displayed."
        at KontaktPage
    }

    def "Test Case 2"() {
        when: "the URL www.qualityminds.de is opened,"
        go "http://www.qualityminds.de"
        activePage = page IndexPage
        then: "the QualityMinds startpage is open."
        at IndexPage

        when: "'Portfolio' is hovered at the top navigation bar,"
        hover activePage.topnavigation.portfolio
        then: "the submenu is displayed."
        activePage.topnavigation.portfolio_submenu.isDisplayed()

        when: "'Web, Automation & Mobile Testing' sub option is clicked,"
        activePage.topnavigation.portfolio_automation.click()
        activePage = page WebAutomationMobileTestingPage
        then:
        at WebAutomationMobileTestingPage

    }

}
