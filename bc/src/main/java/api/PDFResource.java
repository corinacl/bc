package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

import controllers.PDFController;

@RestController
@RequestMapping(Uris.PDF)
public class PDFResource {

    private PDFController pdfController;

    @Autowired
    public void setPdfController(PDFController pdfController) {
        this.pdfController = pdfController;
    }
    //Sólo para testing
    @RequestMapping(method = RequestMethod.GET)
    public void generatePdf(@RequestParam(required = true) String fileName) throws FileNotFoundException {
        pdfController.generatePdf(fileName);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void createBookingConfirmation(@RequestParam(required = true) int id) throws FileNotFoundException {
    	pdfController.createBookingConfirmationPdf(id);
    }

}
