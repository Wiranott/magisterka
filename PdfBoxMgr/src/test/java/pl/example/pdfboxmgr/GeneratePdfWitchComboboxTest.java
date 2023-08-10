package pl.example.pdfboxmgr;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.example.pdfboxmgr.config.PdfConfig.BASE_PATH;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
            Arguments.of("comboBoxPDF1.pdf", generateRandomOptions(10)),
            Arguments.of("comboBoxPDF2.pdf", generateRandomOptions(50))
        );
    }

    private static List<String> generateRandomOptions(int count) {
        Random random = new Random();
        return IntStream.range(0, count)
            .mapToObj(i -> "Option " + (char) ('A' + random.nextInt(26)))
            .collect(Collectors.toList());
    }
}