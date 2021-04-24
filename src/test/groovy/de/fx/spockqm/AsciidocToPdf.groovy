package de.fx.spockqm

import org.asciidoctor.Asciidoctor
import org.asciidoctor.SafeMode

import static org.asciidoctor.Asciidoctor.Factory.create
import static org.asciidoctor.OptionsBuilder.options

class AsciidocToPdf {
    static void convert() {
        println("########## Convert AsciiDoc Reports to PDFs ###########")
        Asciidoctor asciidoctor = create()
        createPdf(asciidoctor, "target/spock-reports/chrome/report.ad", "report-chrome.pdf")
        createPdf(asciidoctor, "target/spock-reports/firefox/report.ad", "report-firefox.pdf")

    }

    private static createPdf(Asciidoctor asciidoctor, String asciiDocPath, String pdfName) {
        def options = options()
                .inPlace(false)
                .backend("pdf")
                .safe(SafeMode.SAFE)
                .toFile(new File(pdfName))
                .destinationDir(new File("target/"))
                .asMap()
        try {
            asciidoctor.convertFile(new File(asciiDocPath), options)
        } catch (Exception e) {
            println("PDF Generation Failed.")
            println(e.message)
        }
    }
}
