package pl.example.pdfitextmgr;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.example.pdfitextmgr.config.PdfItextConfig.BASE_PATH;

import java.io.File;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.example.pdfitextmgr.generator.TablePdfGenerator;

@SpringBootTest
public class GeneratePdfWithTableTest {

    @Autowired
    private TablePdfGenerator tablePdfGenerator;

    @ParameterizedTest
    @MethodSource("provideTestData")
    void shouldGeneratePdfWithTable(String fileName, String[][] data) {
        tablePdfGenerator.generatePdfWithTable(fileName, data);

        var file = new File(BASE_PATH + fileName);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    private static Stream<Arguments> provideTestData() {
        return Stream.of(
            Arguments.of("tablePDF1.pdf", new String[][]{{"Header1", "Header2"}, {"Data1", "Data2"}}),
            Arguments.of("tablePDF2.pdf", new String[][]{{"HeaderA", "HeaderB"}, {"DataA", "DataB"}})
        );
    }
}