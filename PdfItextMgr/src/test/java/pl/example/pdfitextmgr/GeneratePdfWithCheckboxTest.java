package pl.example.pdfitextmgr;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.example.pdfitextmgr.generator.CheckboxPdfGenerator;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GeneratePdfWithCheckboxTest {

    @Autowired
    private CheckboxPdfGenerator checkboxPdfGenerator;

    @ParameterizedTest
    @MethodSource("provideParametersForCheckboxPdf")
    void shouldGeneratePdfWithCheckbox(String fileName, float xPosition, float yPosition, boolean isChecked) {
        checkboxPdfGenerator.generatePdfWithCheckbox(fileName, xPosition, yPosition, isChecked);
    }

    private static Stream<Arguments> provideParametersForCheckboxPdf() {
        return Stream.of(
            Arguments.of("checkboxPDF1.pdf", 50f, 750f, true),
            Arguments.of("checkboxPDF2.pdf", 100f, 700f, false),
            Arguments.of("checkboxPDF3.pdf", 150f, 650f, true)
        );
    }
}