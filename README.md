# Spock/Geb Test for QM-Website
## description
This Project includes a testsuite to test the QM-Website.
The Tests are written with Geb/Spock.
The Tests are executed with Chrome and Firefox.
A report ist generated at the end.
The project can be run with Linux, or Windows. MacOs is not supported.

Please use jdk15 or older. Jdk16 is not compatible with the used Groovy-Version.

## usage
Just call `mvn clean install` to execute the testsuite.
`mvn clean install -Ddriver.headless=false` to execute the suite in non-Headless mode.
## reports
The reports are generated to `/target`.
A zip (1.0-SNAPHOT-report.zip) is created containing the reports (Chrome/Firefox) together with the screenshots taken.