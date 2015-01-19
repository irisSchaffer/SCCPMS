package sdm.sccpms.gift;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import sdm.sccpms.ProductFactoryInterface;
import sdm.sccpms.child.Child;
import sdm.sccpms.exceptions.UndefinedProductException;
import sdm.sccpms.gift.Gift;
import sdm.sccpms.gift.GiftFactory;
import sdm.sccpms.gift.GiftWrap;
import sdm.sccpms.products.*;


public class GiftFactoryTest {
	GiftFactory giftFactory;
	GiftWrap giftWrap;
	Child child;
	Map<String, ProductFactoryInterface> productFactories;
	
	@Before
	public void init() {
		this.productFactories = new HashMap<String, ProductFactoryInterface>();
		this.productFactories.put(Bicycle.ID, new BicycleFactory());
		
		this.child = new Child("Tom", "Teststreet");
		
		this.giftWrap = new GiftWrap("blue", "yellow stripes");
		List<GiftWrap> giftWraps = new LinkedList<GiftWrap>();
		giftWraps.add(this.giftWrap);
		
		this.giftFactory = new GiftFactory(productFactories, giftWraps);
	}

	@Test
	public void testGetGift() throws UndefinedProductException {
		Gift gift = this.giftFactory.createGift(Bicycle.ID, child);
		assertEquals(this.giftWrap, gift.getGiftWrap());
		assertTrue(gift.getProduct() instanceof Bicycle);
	}
	
	@Test(expected=UndefinedProductException.class)
	public void testGetUndefinedGift() throws UndefinedProductException {
		this.giftFactory.createGift("peace", child);
	}

}
