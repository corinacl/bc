package daos;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import services.DataService;

@Service
public class DaosServiceIntegrationTests {

    @Autowired
    private DataService dataService;

    @PostConstruct
    public void populate() {
        dataService.populate();
    }

    public void deleteAll() {
        dataService.deleteAllExceptAdmin();
    }

}