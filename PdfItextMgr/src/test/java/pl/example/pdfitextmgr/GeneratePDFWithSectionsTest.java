package pl.example.pdfitextmgr;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.example.pdfitextmgr.config.PDFiTextConfig.BASE_PATH;

import java.io.File;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.example.pdfitextmgr.generator.SectionedPDFGenerator;

@SpringBootTest
public class GeneratePDFWithSectionsTest {

    @Autowired
    private SectionedPDFGenerator sectionedPdfGenerator;

    @ParameterizedTest
    @CsvSource({
        "sectionedPDF1.pdf, Title1, Section1|Section2, Content1|Content2",
        "sectionedPDF2.pdf, Title2, SectionA|SectionB, ContentA|ContentB"
    })
    void shouldGeneratePdfWithSections(String fileName, String title, String sectionTitlesStr, String sectionContentsStr) {
        var sectionTitles = sectionTitlesStr.split("\\|");
        var sectionContents = sectionContentsStr.split("\\|");

        sectionedPdfGenerator.generatePdfWithSections(fileName, title, sectionTitles, sectionContents);

        var file = new File(BASE_PATH + fileName);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }
}