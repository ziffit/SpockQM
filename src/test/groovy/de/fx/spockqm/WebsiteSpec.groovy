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
        when: "(1) the URL www.qualityminds.de is opened,"
        go "http://www.qualityminds.de"
        activePage = page IndexPage
        then: "the QualityMinds indexpage is open."
        at IndexPage
        report "1 - Index page"

        when: "(2) 'Kontakt' at the top navigation is clicked,"
        activePage = activePage.topnavigation.kontakt.click(KontaktPage)
        activePage = page KontaktPage
        then: "the Page 'Kontakt &  Anfahrt' is displayed."
        at KontaktPage
        report "2 - Kontakt page"

        expect: "(3) that the page contains the email address 'hello@qualityminds.de'."
        activePage.maincontent.text() =~ /.*hello@qualityminds.de.*/

        when: "(4) Navigate back to 'www.qualityminds.de'"
        to IndexPage
        activePage = page IndexPage
        then: "the QualityMinds indexpage is displayed."
        at IndexPage
        report "4 - Index page"

        when: "(5) 'Kontakt & Anfahrt' in the bottom navigation is clicked,"
        activePage.bottomnavigation.kontakt.click()
        then: "the Page 'Kontakt &  Anfahrt' is displayed."
        at KontaktPage
        report "5 - Kontakt page"

        //Step 6 is omitted. The Page-Object used in Step2 and Step5 is the same.
    }

    def "Test Case 2"() {
        when: "(1) the URL www.qualityminds.de is opened,"
        go "http://www.qualityminds.de"
        activePage = page IndexPage
        then: "the QualityMinds indexpage is open."
        at IndexPage
        report "1 - Index page"

        when: "(2) 'Portfolio' is hovered at the top navigation bar,"
        hover activePage.topnavigation.portfolio
        then: "the submenu is displayed."
        activePage.topnavigation.portfolio_submenu.isDisplayed()
        report "2 - Portfolio hovered"

        when: "(3,4) 'Web, Automation & Mobile Testing' sub option is clicked,"
        activePage.topnavigation.portfolio_automation.click()
        activePage = page WebAutomationMobileTestingPage
        then: "'Web, Automation & Mobile Testing' page is displayed"
        at WebAutomationMobileTestingPage
        and: "'Portfolio' item of the top bar menu is highlighted (color is rgb(130, 168, 69, 1))."
        activePage.topnavigation.portfolio_link.css("color") ==~ /.*130, 186, 69.*/
        //regex is used, as chrome and firefox return slightly different string
        report "3,4 - Portfolio page"

        when: "(5) Tab 'Mobile' in 'Web, Automation & Mobile Testing' is clicked,"
        activePage.tab_mobile.click()
        then: "mobile section content is displayed"
        activePage.tab_mobile_content.displayed
        and: "'Mobile' is underlined in grey (border-bottom: 3px solid rgb(151, 151, 151))"
        activePage.tab_mobile.parent().css("border-bottom-color") ==~ /.*151, 151, 151.*/
        //regex is used, as chrome and firefox return slightly different string
        and: "'Flyer find the bug session'-button is displayed on the right."
        activePage.a_flyer.displayed
        report "5 - Tab 'mobile'"

        expect: "(6) the download link for flyer is: 'https://qualityminds.de/app/uploads/2018/11/Find-The-Mobile-Bug-Session.pdf'"
        activePage.a_flyer.attr("href") == "https://qualityminds.de/app/uploads/2018/11/Find-The-Mobile-Bug-Session.pdf"
        and: "(7) the pdf is downloadable."
        new String(downloadBytes(activePage.a_flyer.attr("href")) as byte[]).contains("PDF-1.7")
        //Apache PDFBox could be used to further test content
        report "6,7 - Flyer"
    }


    def "Test Case 3"() {
        when: "(1) the URL www.qualityminds.de is opened,"
        go "http://www.qualityminds.de"
        activePage = page IndexPage
        then: "the QualityMinds indexpage is open."
        at IndexPage
        report "1 - Index page"

        when: "(2) 'Karriere' is clicked in the top navigation bar,"
        activePage.topnavigation.karriere.click()
        activePage = page KarrierePage
        then: "the 'Karriere' page is open."
        at KarrierePage
        report "2 - Karriere page"

        when: "(3) button 'Bewirb Dich Jetzt!' is clicked,"
        activePage.button_application.click()
        activePage = page KontaktformularPage
        then: "the application form is opened."
        at KontaktformularPage
        report "3 - Application form"

        when: "(4,5) button 'Jetzt bewerben' is clicked,"
        activePage.button_submit.click()
        then: "the message is not submitted"
        at KontaktformularPage
        and: "validation messages for 4 fields (Vorname, Nachname, Email, Datenschutz) are displayed."
        activePage.firstname_validation.displayed
        activePage.lastname_validation.displayed
        activePage.email_validation.displayed
        activePage.privacy_validation.displayed
        report "4,5 - 4 validation messages"

        when: "(6) the fields 'Vorname' and 'Nachname' are filled,"
        activePage.firstname_input = "Hubert"
        activePage.lastname_input = "Hansen"
        then: "both fileds are filled."
        activePage.firstname_input.value() == "Hubert"
        activePage.lastname_input.value() == "Hansen"
        report "6 - Inputs Firstname, Lastname"

        when: "(7,8) button 'Jetzt bewerben' is clicked,"
        activePage.button_submit.click()
        then: "the message is not submitted"
        at KontaktformularPage
        and: "validation messages for 2 fields (Email, Datenschutz) are displayed."
        activePage.email_validation.displayed
        activePage.privacy_validation.displayed
        report "7,8 - Validation messages email, privacy"

        when: "(9) 'E-Mail'-field is filled with an INVALID value,"
        activePage.email_input = "INVALID VALUE"
        then: "the 'E-Mail'-field is filled."
        activePage.email_input.value() == "INVALID VALUE"
        report "9 - Email invalid input"

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
