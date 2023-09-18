package pl.example.pdfitextmgr;

import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.example.pdfitextmgr.config.PDFiTextConfig.BASE_PATH;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.example.pdfitextmgr.generator.TablePDFGenerator;

@SpringBootTest
public class GeneratePDFWithTableTest {

    @Autowired
    private TablePDFGenerator tablePdfGenerator;

    @ParameterizedTest
    @MethodSource("provideTableData")
    void shouldGeneratePdfWithTable(String fileName, List<String[]> data) {
        tablePdfGenerator.generatePdfWithTable(fileName, data);

        var file = new File(BASE_PATH + fileName);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    private static Stream<Arguments> provideTableData() {
        List<String[]> repeatedData = new ArrayList<>(Collections.nCopies(100, new String[]{"HeaderA", "HeaderB"}));
        repeatedData.addAll(Collections.nCopies(100, new String[]{"DataA", "DataB"}));

        return range(0, 10)
            .mapToObj(i -> Arguments.of("pdfWithTable.pdf", repeatedData));
    }
}