package pl.example.pdfboxmgr;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.example.pdfboxmgr.config.PdfConfig.BASE_PATH;

import java.io.File;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.example.pdfboxmgr.generator.ComboboxPdfGenerator;

@SpringBootTest
public class GeneratePdfWitchComboboxTest {

    @Autowired
    private ComboboxPdfGenerator comboboxPdfGenerator;

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void shouldGeneratePdfWithComboBox(String fileName, List<String> options) {
        comboboxPdfGenerator.generatePdfWithComboBox(fileName, options);
        File file = new File(BASE_PATH + fileName);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    private static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of("comboBoxPDF1.pdf", asList("Opcja 1", "Opcja 2", "Opcja 3")),
            Arguments.of("comboBoxPDF2.pdf", asList("Option A", "Option B")),
            Arguments.of("comboBoxPDF3.pdf", asList("Choice X", "Choice Y", "Choice Z", "Choice W"))
        );
    }
}