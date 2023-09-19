package pl.example.pdfitextmgr;

import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.example.pdfitextmgr.config.PDFiTextConfig.BASE_PATH;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.example.pdfitextmgr.generator.SectionedPDFGenerator;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GeneratePDFWithSectionsTest {

    @Autowired
    private SectionedPDFGenerator sectionedPdfGenerator;

    @ParameterizedTest
    @MethodSource("provideSectionData")
    void shouldGeneratePdfWithSections(String fileName, String title, List<String> sectionTitles, List<String> sectionContents) {
        sectionedPdfGenerator.generatePdfWithSections(fileName, title, sectionTitles.toArray(new String[0]), sectionContents.toArray(new String[0]));

        var file = new File(BASE_PATH + fileName);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    private static Stream<Arguments> provideSectionData() {
        var fiftySectionTitles = IntStream.rangeClosed(1, 500000)
            .mapToObj(i -> "Sekcja " + i)
            .collect(Collectors.toList());

        var fiftySectionContents = IntStream.rangeClosed(1, 500000)
            .mapToObj(i -> "Tresc sekcji " + i)
            .collect(Collectors.toList());

        return range(0, 20)
            .mapToObj(i -> Arguments.of("sectionedPDF.pdf", "Tytul dokumentu", fiftySectionTitles, fiftySectionContents)
            );
    }
}