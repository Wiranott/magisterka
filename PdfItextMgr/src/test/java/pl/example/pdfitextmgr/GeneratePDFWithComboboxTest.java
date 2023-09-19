package pl.example.pdfitextmgr;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.example.pdfitextmgr.config.PDFiTextConfig.BASE_PATH;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.example.pdfitextmgr.generator.ComboboxGenerator;

@SpringBootTest
public class GeneratePDFWithComboboxTest {

    @Autowired
    private ComboboxGenerator comboboxGenerator;

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void shouldGeneratePdfWithComboBox(String fileName, List<String> options) {
        comboboxGenerator.generatePdfWithComboBox(fileName, options);
        var file = new File(BASE_PATH + fileName);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    private static Stream<Arguments> provideDataForTest() {
        return IntStream.range(0, 20)
            .mapToObj(i -> Arguments.of("comboBoxPDF.pdf", generateRandomOptions())
            );
    }

    private static List<String> generateRandomOptions() {
        var random = new Random();
        return IntStream.range(0, 10000)
            .mapToObj(i -> "Option " + (char) ('A' + random.nextInt(1)))
            .collect(toList());
    }
}