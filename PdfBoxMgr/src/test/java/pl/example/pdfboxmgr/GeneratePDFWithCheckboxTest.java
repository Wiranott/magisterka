package pl.example.pdfboxmgr;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.example.pdfboxmgr.generator.CheckboxPDFGenerator;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GeneratePDFWithCheckboxTest {

    @Autowired
    private CheckboxPDFGenerator checkboxPdfGenerator;

    @ParameterizedTest
    @MethodSource("provideParametersForCheckboxPdf")
    void shouldGeneratePdfWithCheckbox(String fileName, float xPosition, float yPosition, boolean isChecked) {
        checkboxPdfGenerator.generatePdfWithCheckbox(fileName, xPosition, yPosition, isChecked);
    }

    private static Stream<Arguments> provideParametersForCheckboxPdf() {
        return IntStream.range(0, 20)
            .mapToObj(i -> Arguments.of("checkboxPDF.pdf", 50f, 750f, false));
    }
}