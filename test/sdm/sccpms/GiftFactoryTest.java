package sdm.sccpms;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import sdm.sccpms.exceptions.UndefinedProductException;
import sdm.sccpms.products.*;


public class GiftFactoryTest {
	GiftFactory giftFactory;
	GiftWrap giftWrap;
	Map<String, ProductFactoryInterface> productFactories;
	
	@Before
	public void init() {
		this.productFactories = new HashMap<String, ProductFactoryInterface>();
		this.productFactories.put("bicycle", new BicycleFactory());
		
		this.giftWrap = new GiftWrap("blue", "yellow stripes");
		List<GiftWrap> giftWraps = new LinkedList<GiftWrap>();
		giftWraps.add(this.giftWrap);
		
		this.giftFactory = new GiftFactory(productFactories, giftWraps);
	}

	@Test
	public void testGetGift() throws UndefinedProductException {
		Gift gift = this.giftFactory.createGift("bicycle");
		assertEquals(this.giftWrap, gift.getGiftWrap());
		assertTrue(gift.getProduct() instanceof Bicycle);
	}
	
	@Test(expected=UndefinedProductException.class)
	public void testGetUndefinedGift() throws UndefinedProductException {
		this.giftFactory.createGift("peace");
	}

}
