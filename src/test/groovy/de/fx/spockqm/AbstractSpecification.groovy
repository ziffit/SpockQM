package de.fx.spockqm

import geb.navigator.Navigator
import geb.spock.GebReportingSpec
import org.openqa.selenium.interactions.Actions

/**
 * AbstractSpecification holds the activePage and methods to easy the usage of GEB.
 */
class AbstractSpecification extends GebReportingSpec {

    //activePage is used to enable autocompletion in the IDE and support easy refactoring
    //should always contain a dynamically typed instance of the actual page.
    def activePage

    //actions for direct WebDriver use
    def actions = new Actions(driver)

    /**
     * Emulate a Mouse-Hover event
     * @param navigator that should be hovered
     */
    def hover(Navigator navigator){
        actions.moveToElement(navigator.firstElement()).build().perform()
    }
}
