package entities;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class BungalowTypeTest {

	@Test
	public void testBungalowType() {
		BigDecimal julToOctPrice = new BigDecimal (135);	
		BigDecimal octToDecPrice = new BigDecimal (95);
		BungalowType bungalowType = new BungalowType("A", julToOctPrice, octToDecPrice, julToOctPrice, octToDecPrice, octToDecPrice);
		Assert.assertEquals(bungalowType.getType(), "A");
		Assert.assertEquals(bungalowType.getJulToOctPrice(), julToOctPrice);
		Assert.assertEquals(bungalowType.getOctToDecPrice(), octToDecPrice);
	}

}
