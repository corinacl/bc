package controllers;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import services.PDFService;

import com.itextpdf.kernel.geom.PageSize;

import daos.BookingDao;
import entities.Booking;

@Controller
public class PDFController {
	
	private PDFService pdfService;
	
	private BookingDao bookingDao;
	
	@Autowired
	public void setPDFService(PDFService pdfService){
		this.pdfService = pdfService;
	}

	@Autowired
	public void setBookingDao(BookingDao bookingDao) {
		this.bookingDao = bookingDao;
	}
	
    public void generatePdf(String fileName) throws FileNotFoundException{
        pdfService.makePdf(fileName, PageSize.A4);       
    }

	public void createBookingConfirmationPdf(int id, String pdfLanguage) throws IOException {
		Booking booking = bookingDao.findOne(id);
		pdfService.createBookingConfimation(booking, pdfLanguage);
	}
    

}
