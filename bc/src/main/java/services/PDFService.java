package services;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

import controllers.PlanningController;
import entities.Booking;

@Service
public class PDFService {

	private final static String USER_HOME = System.getProperty("user.home");
    private final static String PDF_FILES_ROOT = "/tpv/pdfs/";
    private final static String PDF_FILE_EXT = ".pdf";
    private PlanningController planningController;
    
    @Autowired
	public void setPlanningController(PlanningController planningController) {
		this.planningController = planningController;
	}
    
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
        Image image = new Image(ImageDataFactory.create(getClass().getClassLoader().getResource("logo_bc.png")));
        image.setWidth(180);
        image.setMargins(12, 0, 25, 150);
        pdfDocument.add(image);
        pdfDocument.add(new Paragraph("Esto es una prueba."));
        pdfDocument.close();
    }
    
    public void createBookingConfimation(Booking booking) throws FileNotFoundException {
        String fileName = "Reserva_" + booking.getId();
    	String path = USER_HOME + PDF_FILES_ROOT + fileName + PDF_FILE_EXT;
        makeDirectories(path);
        Document pdfDocument = getPdfDocument(path, PageSize.A4);
        //Logo
        Image image = new Image(ImageDataFactory.create(getClass().getClassLoader().getResource("logo_bc.png")));
        image.setWidth(180);
        image.setMargins(12, 0, 25, 150);
        pdfDocument.add(image);
        pdfDocument.add(new Paragraph("CONFIMACIÓN DE RESERVA " + "TODO: DIA DE HOY"));
        //Cliente
        pdfDocument.add(new Paragraph("Cliente: " + booking.getClient().getName() + " " + booking.getClient().getSurname()));
        //Fechas
        pdfDocument.add(new Paragraph("Llegada: " + planningController.convertCalendarToString(booking.getArrivalDate())));
        pdfDocument.add(new Paragraph("Salida: " + planningController.convertCalendarToString(booking.getDepartureDate())));
        //Noches
        //TODO Numero de noches
        long totalNights = (booking.getDepartureDate().getTimeInMillis() - booking.getArrivalDate().getTimeInMillis()) / (24 * 60 * 60 * 1000)+1;
        BigDecimal totalPrice = (booking.getTotalPrice().multiply(new BigDecimal(0.07))).add(booking.getTotalPrice()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        pdfDocument.add(new Paragraph("Noches: " + totalNights));
        //Tipo bungalow
        pdfDocument.add(new Paragraph("Tipo bungalow: " + booking.getBungalow().getType().getType()));
        //Precio
        pdfDocument.add(new Paragraph("Precio neto: " + booking.getTotalPrice() + "||||| Precio Total +7% IGIC= " + totalPrice));
       
        pdfDocument.close();
    }
}
