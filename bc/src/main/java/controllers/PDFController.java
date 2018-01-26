package controllers;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import services.PDFService;

import com.itextpdf.kernel.geom.PageSize;

@Controller
public class PDFController {
	
	private PDFService pdfService;
	
	@Autowired
	public void setPDFService(PDFService pdfService){
		this.pdfService = pdfService;
	}
    
    public void generatePdf(String fileName) throws FileNotFoundException{
        pdfService.makePdf(fileName, PageSize.A4);       
    }

}
