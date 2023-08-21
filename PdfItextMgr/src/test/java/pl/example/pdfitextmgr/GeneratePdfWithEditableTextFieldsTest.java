package pl.example.pdfitextmgr;

import static java.util.stream.Stream.of;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.example.pdfitextmgr.generator.EditableTextFieldsPdfGenerator;

@SpringBootTest
public class GeneratePdfWithEditableTextFieldsTest {

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
            Arguments.of("textFieldsPDF2.pdf", 10),
            Arguments.of("textFieldsPDF3.pdf", 50),
            Arguments.of("textFieldsPDF4.pdf", 100)
        );
    }
}