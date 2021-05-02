package de.fx.spockqm

import com.github.romankh3.image.comparison.ImageComparison
import com.github.romankh3.image.comparison.ImageComparisonUtil
import com.github.romankh3.image.comparison.model.ImageComparisonResult
import com.github.romankh3.image.comparison.model.ImageComparisonState
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
    def hover(Navigator navigator) {
        actions.moveToElement(navigator.firstElement()).build().perform()
    }

    def reportAndCompare(String description, String templateImageIdentifier) {
        //Make Screenshot
        report("${description}#${templateImageIdentifier}")

        def expectedImagePath = findFileInPath("src/test/resources/", templateImageIdentifier)
        def actualImagePath = findFileInPath("target/spock-reports", templateImageIdentifier)

        def expectedImage = ImageComparisonUtil.readImageFromResources(expectedImagePath)
        def actualImage = ImageComparisonUtil.readImageFromResources(actualImagePath)

        def resultDestination = new File("target/Result-${templateImageIdentifier}.png")
        ImageComparisonResult imageComparisonResult = new ImageComparison(expectedImage, actualImage, resultDestination).setRectangleLineWidth(3).compareImages()
        assert imageComparisonResult.getImageComparisonState() == ImageComparisonState.MATCH: "Pictures did not Match. " +
                "Difference is ${imageComparisonResult.differencePercent}%." +
                "${imageComparisonResult.rectangles ? imageComparisonResult.rectangles.size() : 0} rectangles. " +
                "${imageComparisonResult.rectangles ? "Report-image Location: '$resultDestination'." : ""}" +
                "\nATTENTION: Different Browsers/Platforms and Headless-Settings generate different images/image-sizes!"
        return true
    }

    String findFileInPath(String base, String identifier) {
        def filePaths = [] as List<String>
        def fileDir = new File(base)
        fileDir.eachDirRecurse() { dir ->
            dir.eachFileMatch(~/.*${identifier}.png/) { file ->
                filePaths.add(file.absolutePath)
            }
        }
        println("Found files: $filePaths")
        if (filePaths.size() != 1) {
            //throw new RuntimeException("More than one file found for identifier $identifier. Files: $filePaths.")
            return filePaths.last()
        }
        return filePaths.first()
    }
}
