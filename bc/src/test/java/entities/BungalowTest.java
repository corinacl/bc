package entities;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BungalowTest {
	
	@Autowired
	private BungalowType bungalowType;

	@Test
	public void testBungalow() {
		Bungalow bungalow = new Bungalow(23, bungalowType);
		Assert.assertEquals(bungalow.getNumber(), 23);
		Assert.assertEquals(bungalow.getType(), bungalowType);
	}

}
