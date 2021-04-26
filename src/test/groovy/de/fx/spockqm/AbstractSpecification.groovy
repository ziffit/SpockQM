package de.fx.spockqm

import geb.navigator.Navigator
import geb.spock.GebReportingSpec
import org.openqa.selenium.interactions.Actions

class AbstractSpecification extends GebReportingSpec {

    def activePage
    def actions = new Actions(driver)

    def hover(Navigator navigator){
        actions.moveToElement(navigator.firstElement()).build().perform()
    }
}
