package controllers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({TokenControllerIT.class, BungalowControllerIT.class, BungalowTypeControllerIT.class, ClientControllerIT.class,
	PlanningControllerIT.class, BookingControllerIT.class})

public class AllControllersIntegrationTests {

}
