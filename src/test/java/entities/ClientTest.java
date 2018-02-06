package entities;

import org.junit.Assert;
import org.junit.Test;

public class ClientTest {

	@Test
	public void testClient() {
		Client client = new Client("Client", "Test", "666111222", null, null);
		Assert.assertEquals(client.getName(), "Client");
		Assert.assertEquals(client.getPhone(), "666111222");
	}

}
