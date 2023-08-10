package pl.example.pdfitextmgr.generator;

import static pl.example.pdfitextmgr.config.PdfItextConfig.BASE_PATH;
import static pl.example.pdfitextmgr.config.PdfItextConfig.IMAGE_PATH;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ImagePdfGenerator {

    public void generatePdfWithImage(String fileName, String imagePath) {
        var fullPath = BASE_PATH + fileName;
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fullPath));
            document.open();

            var img = Image.getInstance(IMAGE_PATH);
            img.scaleToFit(PageSize.A4.getWidth() - 100, PageSize.A4.getHeight() - 100);
            img.setAlignment(Element.ALIGN_CENTER);
            document.add(img);

            document.close();
        } catch (Exception e) {
            log.error("Error generating PDF with image", e);
        }
    }
}