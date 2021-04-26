package de.fx.spockqm


import de.fx.spockqm.pages.IndexPage
import de.fx.spockqm.pages.KontaktPage

/**
 * Dummy Spec to show failings tests in the report.
 */
class XFailingSpec extends AbstractSpecification {

    def "Test Case 4"() {
        when: "(1) the URL www.qualityminds.de is opened,"
        go "http://www.qualityminds.de"
        activePage = page IndexPage
        then: "the QualityMinds 'Kontakt' page is open."
        at KontaktPage
        report "1 - Kontakt page"
    }
}
