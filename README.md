# Spock/Geb Test for QM-Website
## Description
This Project includes a testsuite to test the QM-Website.
The Tests are written with Geb/Spock.
The Tests can be executed with Chrome and Firefox (use Mavenprofiles 'chrome' or 'firefox').
A report ist generated at the end.
The project can be run with Linux, Windows or MacOs.

Please use jdk >= 11. Jdk16 is not supported, as it is not compatible with the used Groovy-Version.
The testsuite was developed using Chrome 89 and Firefox 88.

## Usage
Just call `mvn clean install` to execute the testsuite with chrome im headless-mode.
`mvn clean install -Pfirefox` to use Firefox.
`mvn clean install -Ddriver.headless=false` to execute the suite in non-Headless mode.

## Reports
The reports are generated to `/target`.
A zip (1.0-SNAPHOT-report.zip) is created containing the reports and the screenshots taken.