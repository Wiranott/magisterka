package pl.example.pdfitextmgr.generator;

import static pl.example.pdfitextmgr.config.PdfItextConfig.BASE_PATH;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TablePdfGenerator {

    public void generatePdfWithTable(String fileName, String[][] data) {
        var fullPath = BASE_PATH + fileName;

        try {
            var document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(fullPath));
            document.open();

            if (data != null && data.length > 0) {
                var table = new PdfPTable(data[0].length);

                for (String[] row : data) {
                    for (String cellData : row) {
                        table.addCell(cellData);
                    }
                }

                document.add(table);
            }

            document.close();
        } catch (DocumentException | IOException e) {
            log.error("Error generating PDF with table", e);
        }
    }
}