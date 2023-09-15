package pl.example.pdfitextmgr;

import static java.util.stream.Collectors.*;
import static java.util.stream.Stream.of;
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
        return of(
            Arguments.of("comboBoxPDF1.pdf", generateRandomOptions(10)),
            Arguments.of("comboBoxPDF2.pdf", generateRandomOptions(50))
        );
    }

    private static List<String> generateRandomOptions(int count) {
        var random = new Random();
        return IntStream.range(0, count)
            .mapToObj(i -> "Option " + (char) ('A' + random.nextInt(26)))
            .collect(toList());
    }
}