package de.fx.spockqm

import org.asciidoctor.Asciidoctor
import org.asciidoctor.Attributes
import org.asciidoctor.SafeMode

import static org.asciidoctor.Asciidoctor.Factory.create
import static org.asciidoctor.OptionsBuilder.options

/**
 * Simple Class to convert the AsciiDoc Reports to PDFs.
 * ToDo: Hardcoded names should be removed
 */
class AsciidocToPdf {
    static void convert() {
        println("########## Convert AsciiDoc Reports to PDFs ###########")
        Asciidoctor asciidoctor = create()
        createPdf(asciidoctor, "target/spock-reports/chrome/report.ad", "report-chrome.pdf")
        createPdf(asciidoctor, "target/spock-reports/firefox/report.ad", "report-firefox.pdf")
    }

    private static createPdf(Asciidoctor asciidoctor, String asciiDocPath, String pdfName) {
        def attributes = new Attributes(
                author: System.getProperty("user.name"),
                icons: "font",
                header: ""
        )
        def options = options()
                .inPlace(false)
                .backend("pdf")
                .safe(SafeMode.SAFE)
                .toFile(new File("target/$pdfName"))
                .attributes(attributes)
                .asMap()
        try {
            asciidoctor.convertFile(new File(asciiDocPath), options)
            println("Written: $pdfName")
        } catch (Exception e) {
            println("PDF Generation Failed.${e.message}")
        }
    }
}
