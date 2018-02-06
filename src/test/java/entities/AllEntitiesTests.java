package entities;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({EncryptTest.class, TokenTest.class, ClientTest.class, BookingTest.class, BungalowTypeTest.class})

public class AllEntitiesTests {

}
