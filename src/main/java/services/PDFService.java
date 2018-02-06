package services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.layout.element.Text;

import controllers.PlanningController;
import entities.Booking;

@Service
public class PDFService {

	private final static String USER_HOME = System.getProperty("user.home");
    private final static String PDF_FILES_ROOT = "/tpv/pdfs/";
    private final static String PDF_FILE_EXT = ".pdf";
    private PlanningController planningController;
    private String client_title, nights_title, arrival_title, departure_title, totalPrice_title, netoPrice_title, main_title, type_title, deposit_title, stillToPay_title;
    
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
    
    public void translateTitles (String language){
    	switch (language){
	     	case "esp":
	     		client_title = "Cliente: ";
	     		nights_title = "Noches: "; 
	     		arrival_title = "Fecha entrada: ";
	     		departure_title = "Fecha salida: ";
	     		totalPrice_title = "Precio total: ";
	     		netoPrice_title = "Precio neto: ";
	     		main_title = "CONFIRMACIÓN DE RESERVA"; 
	     		type_title = "Bungalow Tipo ";
	     		deposit_title = "Depósito: ";
	     		stillToPay_title = "Por pagar: ";
	     		break;
	     	case "eng":
	     		client_title = "Client: ";
	     		nights_title = "Nights: "; 
	     		arrival_title = "Arrival Date: ";
	     		departure_title = "Departure Date: ";
	     		totalPrice_title = "Total Price (w/ 7% taxes): ";
	     		netoPrice_title = "Price (w/out taxes): ";
	     		main_title = "BOOKING CONFIRMATION"; 
	     		type_title = "Bungalow Type ";
	     		deposit_title = "Deposit: ";
	     		stillToPay_title = "Still: ";
	     		break;
	     	case "deu":
	     		client_title = "Klient: ";
	     		nights_title = "Nächte: "; 
	     		arrival_title = "Ankuft: ";
	     		departure_title = "Abreise: ";
	     		totalPrice_title = "Preise (7% taxes): ";
	     		netoPrice_title = "Preise (w/out taxes): ";
	     		main_title = "RESERVIERUNGSBESTÄTIGUNG"; 
	     		type_title = "Bungalow Typ ";
	     		deposit_title = "Anzahlung: ";
	     		stillToPay_title = "Por pagar: ";
	     		break;
	 		default:
	 			break;
	 	}	
    }
        
    public void createBookingConfimation(Booking booking, String pdfLanguage) throws IOException {
        translateTitles(pdfLanguage);
        
        String fileName = "Reserva_" + booking.getId();
    	String path = USER_HOME + PDF_FILES_ROOT + fileName + PDF_FILE_EXT;
        makeDirectories(path);
        Document pdfDocument = getPdfDocument(path, PageSize.A4);

    	Style title = new Style();
    	PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
    	title.setFont(font).setFontSize(12); 	
        
        Image image = new Image(ImageDataFactory.create(getClass().getClassLoader().getResource("logo_bc.png")));
        image.setWidth(180).setHorizontalAlignment(HorizontalAlignment.CENTER);
        pdfDocument.add(image);
        
        Paragraph lineSeparator = new Paragraph("______________________________________________________________________________").addStyle(title);
        pdfDocument.add(lineSeparator);
        
        Paragraph mainTitle = new Paragraph();
        mainTitle.add(new Text(main_title)).addStyle(title).setTextAlignment(TextAlignment.CENTER).setFontSize(16).add("\n");
    	pdfDocument.add(mainTitle);
    	
    	Paragraph client = new Paragraph();
    	client.add(new Text(client_title).addStyle(title));
    	client.add(new Text(booking.getClient().getName() + " " + booking.getClient().getSurname()));
    	
    	Paragraph dates = new Paragraph();
    	dates.add(new Text(arrival_title).addStyle(title));
    	dates.add(new Text(planningController.convertCalendarToString(booking.getArrivalDate()))).add("\n");
    	dates.add(new Text(departure_title).addStyle(title));
    	dates.add(new Text(planningController.convertCalendarToString(booking.getDepartureDate()))).add("\n");
    	long totalNights = (booking.getDepartureDate().getTimeInMillis() - booking.getArrivalDate().getTimeInMillis()) / (24 * 60 * 60 * 1000)+1;
        dates.add(new Text(nights_title).addStyle(title));
        dates.add(new Text(totalNights+"")).add("\n");
        
        Paragraph bungType = new Paragraph();
        bungType.add(new Text(type_title).addStyle(title));
        bungType.add(new Text(booking.getBungalow().getType().getType())).add(new Text("-"+booking.getBungalow().getNumber()));

    	Paragraph price = new Paragraph();
        BigDecimal totalPrice = (booking.getTotalPrice().multiply(new BigDecimal(0.07))).add(booking.getTotalPrice()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal stillToPay = totalPrice.subtract(booking.getDeposit());
        price.add(new Text(netoPrice_title).addStyle(title)).add(booking.getTotalPrice()+"€").add("\n");
        price.add(new Text(totalPrice_title).addStyle(title)).add(totalPrice+"€").add("\n");
        price.add(new Text(deposit_title).addStyle(title)).add(booking.getDeposit()+"€").add("\n\n");
        price.add(new Text(stillToPay_title).addStyle(title)).add(stillToPay+"€");
        
        
        @SuppressWarnings("deprecation")
        Table table = new Table(2).setMarginTop(20);
    	Cell leftCell = new Cell().setPadding(10);
    	leftCell.add(client).add("\n").add(dates).add("\n").add(bungType);
    	Cell rightCell = new Cell().setPadding(10);
    	rightCell.add(price);
    	table.addCell(leftCell).addCell(rightCell);
    	pdfDocument.add(table);
        
        Paragraph currentDate = new Paragraph();
        currentDate.add(planningController.convertCalendarToString(Calendar.getInstance())).addStyle(title);
        pdfDocument.showTextAligned(currentDate, 500, 70, 1, TextAlignment.LEFT, VerticalAlignment.BOTTOM, 0);
        
        pdfDocument.close();
    }
}
