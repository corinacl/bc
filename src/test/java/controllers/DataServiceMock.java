package controllers;

import services.DataService;

public class DataServiceMock extends DataService {

    private boolean executed = false;

    @Override
    public void deleteAllExceptAdmin() {
        executed = true;
    }

    public boolean isExecuted() {
        return executed;
    }

}
