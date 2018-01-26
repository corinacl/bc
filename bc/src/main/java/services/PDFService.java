package services;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import org.springframework.stereotype.Service;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

@Service
public class PDFService {

	private final static String USER_HOME = System.getProperty("user.home");
    private final static String PDF_FILES_ROOT = "/tpv/pdfs/";
    private final static String PDF_FILE_EXT = ".pdf";
    private static final String LOGO_IMG = "/webapp/assets/img/logo_bc.png";
    
    private void makeDirectories(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
    }

    private Document getPdfDocument(String path, PageSize pageSize) throws FileNotFoundException {
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        return new Document(pdfDocument, pageSize);
    }

    public void makePdf(String fileName, PageSize pageSize) throws FileNotFoundException {
        String path = USER_HOME + PDF_FILES_ROOT + fileName + PDF_FILE_EXT;
        makeDirectories(path);
        Document pdfDocument = getPdfDocument(path, pageSize);
        pdfDocument.add(new Paragraph("Esto es una prueba."));
        Image image = new Image(ImageDataFactory.create(getClass().getClassLoader().getResource("logo_bc.png")));
        pdfDocument.add(image);
        pdfDocument.close();
    }
}
