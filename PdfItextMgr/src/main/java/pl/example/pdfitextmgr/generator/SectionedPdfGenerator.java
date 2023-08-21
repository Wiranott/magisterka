package pl.example.pdfitextmgr.generator;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.example.pdfitextmgr.config.PdfItextConfig;

@Slf4j
@Service
public class SectionedPdfGenerator {

    public void generatePdfWithSections(String fileName, String title, String[] sectionTitles, String[] sectionContents) {
        var fullPath = PdfItextConfig.BASE_PATH + fileName;

        try {
            var document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(fullPath));
            document.open();

            if (title != null && !title.isEmpty()) {
                Chapter chapter = new Chapter(new Paragraph(title), 1);
                for (int i = 0; i < sectionTitles.length; i++) {
                    Section section = chapter.addSection(new Paragraph(sectionTitles[i]));
                    section.add(new Paragraph(sectionContents[i]));
                }
                document.add(chapter);
            }

            document.close();
        } catch (DocumentException | IOException e) {
            log.error("Error generating PDF with sections", e);
        }
    }
}