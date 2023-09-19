package pl.example.pdfboxmgr;

import static java.util.stream.IntStream.range;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.example.pdfboxmgr.generator.EditableTextFieldsPDFGenerator;

@SpringBootTest
public class GeneratePDFWitchEditableTextFieldsTest {

    @Autowired
    private EditableTextFieldsPDFGenerator editableTextFieldsPdfGenerator;

    @ParameterizedTest
    @MethodSource("provideParametersForEditableTextFieldsPdf")
    void shouldGeneratePdfWithEditableTextFields(String fileName, int numberOfFields) {
        editableTextFieldsPdfGenerator.generatePdfWithEditableTextFields(fileName, numberOfFields);
    }

    private static Stream<Arguments> provideParametersForEditableTextFieldsPdf() {
        return range(0, 20)
            .mapToObj(i -> Arguments.of("emptyPDF.pdf", 1));
    }
}