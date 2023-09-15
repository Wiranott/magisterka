package pl.example.pdfboxmgr;

import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.example.pdfboxmgr.config.PDFBoxConfig.PDF_PATH;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.example.pdfboxmgr.generator.TablePDFGenerator;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GeneratePDFWithTableTest {

    @Autowired
    private TablePDFGenerator tablePdfGenerator;

    @ParameterizedTest
    @MethodSource("provideTableData")
    void shouldGeneratePdfWithTable(String fileName, List<String[]> data) {
        tablePdfGenerator.generatePdfWithTable(fileName, data);

        var file = new File(PDF_PATH + fileName);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    private static Stream<Arguments> provideTableData() {

        return range(0, 10)
            .mapToObj(i -> Arguments.of("pdfWithTable1.pdf", Arrays.asList(
                new String[]{"Name", "Age", "Email"},
                new String[]{"Name", "Age", "Email"},
                new String[]{"Name", "Age", "Email"},
                new String[]{"Name", "Age", "Email"},
                new String[]{"Name", "Age", "Email"},
                new String[]{"Name", "Age", "Email"},
                new String[]{"Name", "Age", "Email"},
                new String[]{"Name", "Age", "Email"},
                new String[]{"Name", "Age", "Email"},
                new String[]{"Name", "Age", "Email"},
                new String[]{"Name", "Age", "Email"},
                new String[]{"Name", "Age", "Email"},
                new String[]{"Name", "Age", "Email"},
                new String[]{"Name", "Age", "Email"},
                new String[]{"Name", "Age", "Email"},
                new String[]{"Name", "Age", "Email"},
                new String[]{"Name", "Age", "Email"},
                new String[]{"Name", "Age", "Email"},
                new String[]{"Name", "Age", "Email"},
                new String[]{"Name", "Age", "Email"},
                new String[]{"Name", "Age", "Email"},
                new String[]{"Name", "Age", "Email"},
                new String[]{"Name", "Age", "Email"},
                new String[]{"Name", "Age", "Email"},
                new String[]{"Name", "Age", "Email"},
                new String[]{"Name", "Age", "Email"},
                new String[]{"Name", "Age", "Email"}
            )));
    }
}