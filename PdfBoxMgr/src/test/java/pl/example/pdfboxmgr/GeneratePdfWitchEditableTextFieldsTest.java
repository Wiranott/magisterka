package pl.example.pdfboxmgr;

import static java.util.stream.Stream.of;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.example.pdfboxmgr.generator.EditableTextFieldsPdfGenerator;

@SpringBootTest
public class GeneratePdfWitchEditableTextFieldsTest {

    @Autowired
    private EditableTextFieldsPdfGenerator editableTextFieldsPdfGenerator;

    @ParameterizedTest
    @MethodSource("provideParametersForEditableTextFieldsPdf")
    void shouldGeneratePdfWithEditableTextFields(String fileName, int numberOfFields) {
        editableTextFieldsPdfGenerator.generatePdfWithEditableTextFields(fileName, numberOfFields);
    }

    private static Stream<Arguments> provideParametersForEditableTextFieldsPdf() {
        return of(
            Arguments.of("textFieldsPDF1.pdf", 1),
            Arguments.of("textFieldsPDF2.pdf", 2),
            Arguments.of("textFieldsPDF3.pdf", 3),
            Arguments.of("textFieldsPDF4.pdf", 4)
        );
    }
}