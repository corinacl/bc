package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import services.DataService;

@Controller
public class AdminController {

    private DataService dataService;

    @Autowired
    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }

    public void deleteAllExceptAdmin() {
        dataService.deleteAllExceptAdmin();
    }
    
    public void populate() {
        dataService.populate();
    }


}
