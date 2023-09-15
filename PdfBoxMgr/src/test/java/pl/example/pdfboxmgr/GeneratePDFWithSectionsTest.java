package pl.example.pdfboxmgr;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.example.pdfboxmgr.config.PDFBoxConfig.PDF_PATH;

import java.io.File;
import java.util.Arrays;
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
import pl.example.pdfboxmgr.generator.SectionedPDFGenerator;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GeneratePDFWithSectionsTest {

    @Autowired
    private SectionedPDFGenerator sectionedPdfGenerator;

    @ParameterizedTest
    @MethodSource("provideSectionData")
    void shouldGeneratePdfWithSections(String fileName, List<String> sections) {
        sectionedPdfGenerator.generatePdfWithSections(fileName, sections);

        File file = new File(PDF_PATH + fileName);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    private static Stream<Arguments> provideSectionData() {
        List<String> fiftySections = IntStream.rangeClosed(1, 50)
            .mapToObj(i -> "Sekcja " + i)
            .collect(Collectors.toList());

        return Stream.of(
            Arguments.of("sectionedPDF1.pdf", Arrays.asList("Sekcja 1", "Sekcja 2", "Sekcja 3")),
            Arguments.of("sectionedPDF2.pdf", fiftySections)
        );
    }
}