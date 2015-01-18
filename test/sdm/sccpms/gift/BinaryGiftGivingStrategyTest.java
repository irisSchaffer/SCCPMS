package sdm.sccpms.gift;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import sdm.sccpms.Child;
import sdm.sccpms.products.Bicycle;

public class BinaryGiftGivingStrategyTest {

	@Test
	public void testGetGifts() {
		GiftGivingStrategyInterface strategy = new BinaryGiftGivingStrategy();
		List<Gift> gifts = new LinkedList<Gift>();
		gifts.add(new Gift(
			new Bicycle(),
			new Child("Tom", "Teststreet"),
			new GiftWrap("green", "yellow dots")
		));
		
		assertEquals(gifts, strategy.getGifts(gifts, 1f));
		assertEquals(gifts, strategy.getGifts(gifts, .5f));
		
		assertEquals(0, strategy.getGifts(gifts, 0f).size());
		assertEquals(0, strategy.getGifts(gifts, 0.4f).size());
	}

}
