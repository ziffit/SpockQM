package de.fx.spockqm

import de.fx.spockqm.pages.IndexPage
import de.fx.spockqm.pages.KarrierePage
import de.fx.spockqm.pages.KontaktPage
import de.fx.spockqm.pages.KontaktformularPage
import de.fx.spockqm.pages.WebAutomationMobileTestingPage
import geb.module.Checkbox
import geb.spock.GebReportingSpec
import org.openqa.selenium.interactions.Actions

import java.awt.Color

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
        then: "'Web, Automation & Mobile Testing' page is displayed"
        at WebAutomationMobileTestingPage
        and: "'Portfolio' item of the top bar menu is highlighted (color is rgb(130, 168, 69, 1))."
        activePage.topnavigation.portfolio_link.css("color") ==~ /.*130, 186, 69.*/
        //regex is used, as chrome and firefox return slightly different string

        when: "Tab 'Mobile' in 'Web, Automation & Mobile Testing' is clicked,"
        activePage.tab_mobile.click()
        then: "mobile section content is displayed"
        activePage.tab_mobile_content.displayed
        and: "'Mobile' is underlined in grey (border-bottom: 3px solid rgb(151, 151, 151))"
        activePage.tab_mobile.parent().css("border-bottom-color") ==~ /.*151, 151, 151.*/
        //regex is used, as chrome and firefox return slightly different string

        and: "'Flyer find the bug session'-button is displayed on the right."
        activePage.a_flyer.displayed

        expect: "the download link for flyer is: 'https://qualityminds.de/app/uploads/2018/11/Find-The-Mobile-Bug-Session.pdf'"
        activePage.a_flyer.attr("href") == "https://qualityminds.de/app/uploads/2018/11/Find-The-Mobile-Bug-Session.pdf"

        and: "the pdf is downloadable."
        new String(downloadBytes(activePage.a_flyer.attr("href")) as byte[]).contains("PDF-1.7")
        //Apache PDFBox could be used to further test content
    }


    def "Test Case 3"() {
        when: "the URL www.qualityminds.de is opened,"
        go "http://www.qualityminds.de"
        activePage = page IndexPage
        then: "the QualityMinds startpage is open."
        at IndexPage

        when: "'Karriere' is clicked in the top navigation bar,"
        activePage.topnavigation.karriere.click()
        activePage = page KarrierePage
        then: "the 'Karriere' page is open."
        at KarrierePage

        when: "button 'Bewirb Dich Jetzt!' is clicked,"
        activePage.button_application.click()
        activePage = page KontaktformularPage
        then: "the application form is opened."
        at KontaktformularPage

        when: "button 'Jetzt bewerben' is clicked,"
        activePage.button_submit.click()
        then: "the message is not submitted"
        at KontaktformularPage
        and: "validation messages for 4 fields (Vorname, Nachname, Email, Datenschutz) are displayed."
        activePage.firstname_validation.displayed
        activePage.lastname_validation.displayed
        activePage.email_validation.displayed
        activePage.privacy_validation.displayed

        when: "the fields 'Vorname' and 'Nachname' are filled,"
        activePage.firstname_input = "Hubert"
        activePage.lastname_input = "Hansen"
        then: "both fileds are filled."
        activePage.firstname_input.value() == "Hubert"
        activePage.lastname_input.value() == "Hansen"

        when: "button 'Jetzt bewerben' is clicked,"
        activePage.button_submit.click()
        then: "the message is not submitted"
        at KontaktformularPage
        and: "validation messages for 2 fields (Email, Datenschutz) are displayed."
        activePage.email_validation.displayed
        activePage.privacy_validation.displayed

        when: "'E-Mail'-field is filled with an INVALID value,"
        activePage.email_input = "INVALID VALUE"
        then: "the 'E-Mail'-field is filled."
        activePage.email_input.value() == "INVALID VALUE"
        report("Email with invalid value")

        when: "(10) button 'Jetzt bewerben' is clicked,"
        activePage.button_submit.click()
        then: "the message is not submitted."
        at KontaktformularPage
        report "10 - Form is not submitted"

        when: "(11) a file is attached for upload,"
        //File-Upload via the system file-chooser is not supported by WebDriver (see: https://gebish.org/manual/current/#file-upload)
        activePage.upload_input = new File("src/test/resources/dummyUploadFile.txt").absolutePath
        then: "the filename of the attachment is displayed above 'DATEIEN HOCHLADEN'-button."
        activePage.upload_filename.text() == "dummyUploadFile.txt"
        report "11 - Filename is displayed"

        when: "(12) the checkbox for 'Datenschutzerklärung' is checked,"
        activePage.privacy_input.click()
        then: "the checkbox is checked."
        activePage.privacy_input.module(Checkbox).checked
        report "12 - Privacy checkbox is checked"
    }
}
