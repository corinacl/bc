package controllers;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class AdminControllerTest {
    private AdminController adminController;

    private DataServiceMock dataServiceMock;

    @Before
    public void before() {
        adminController = new AdminController();
        dataServiceMock = new DataServiceMock();
        adminController.setDataService(dataServiceMock);
    }

    @Test
    public void testDeleteAllExceptAdmin() {
        assertFalse(dataServiceMock.isExecuted());
        adminController.deleteAllExceptAdmin();
        assertTrue(dataServiceMock.isExecuted());
    }

}
