package sdm.sccpms;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import sdm.sccpms.gift.BinaryGiftGivingStrategy;
import sdm.sccpms.gift.GiftFactory;
import sdm.sccpms.gift.GiftWrap;
import sdm.sccpms.exceptions.UnsupportedMethodException;
import sdm.sccpms.products.Bicycle;
import sdm.sccpms.products.BicycleFactory;
import sdm.sccpms.products.Cat;
import sdm.sccpms.products.CatFactory;
import sdm.sccpms.products.IPod;
import sdm.sccpms.products.IPodFactory;
import sdm.sccpms.products.KnittingSet;
import sdm.sccpms.products.KnittingSetFactory;
import sdm.sccpms.products.Laptop;
import sdm.sccpms.products.LaptopFactory;
import sdm.sccpms.products.TVSet;
import sdm.sccpms.products.TVSetFactory;

public class SantaClausTest {	
	SantaClaus santa;
	GiftFactory giftFactory;
	private Child child;

	@Before
	public void init() {
		this.child = new Child("Max", "Street 9, 1001 City, Country");		
		this.child.addToWishList(IPod.ID);		
		this.child.addToWishList(Cat.ID);	
		
		Map<String, ProductFactoryInterface> productFactories = new HashMap<String, ProductFactoryInterface>();
		productFactories.put(IPod.ID, new IPodFactory());
		productFactories.put(Bicycle.ID, new BicycleFactory());
		productFactories.put(Cat.ID, new CatFactory());
		productFactories.put(KnittingSet.ID, new KnittingSetFactory());
		productFactories.put(Laptop.ID, new LaptopFactory());
		productFactories.put(TVSet.ID, new TVSetFactory());

		List<GiftWrap> giftWraps = new LinkedList<GiftWrap>();
		giftWraps.add(new GiftWrap("blue", "white dots"));
		giftWraps.add(new GiftWrap("green", "yellow stripes"));
		
		this.giftFactory = new GiftFactory(productFactories, giftWraps);

		this.santa = new SantaClaus(giftFactory, new BinaryGiftGivingStrategy());						
	}
	
	@Test
	public void testAddChild() {
		this.santa.addChild(child);
		assertEquals(1, this.santa.getChildRecords().size());
		assertTrue(child.getWishGranter() instanceof SantaClaus);
	}
	
	@Test
	public void testAddChristmasRecordForExistingChild() {
		this.santa.addChild(child);
		
		ChristmasRecord record = new ChristmasRecord(child.getWishList(), child.getGoodness(), 2009);
		this.santa.addChristmasRecordforChild(record, child);
		
		assertEquals(record, this.santa.getChildRecords().get(child).getChristmasRecordForYear(2009));
	}
	
	@Test
	public void testAddChristmasRecordForNewChild() {
		ChristmasRecord record = new ChristmasRecord(child.getWishList(), child.getGoodness());
		this.santa.addChristmasRecordforChild(record, child);
		
		assertNotNull(this.santa.getChildRecords().get(child));
		assertEquals(record, this.santa.getChildRecords().get(child).getCurrentChristmasRecord());
	}
	
	@Test
	public void testOnWishListFinished() {
		this.santa.addChild(child);
		
		child.putWishListOnWindowSill();
		
		assertTrue(this.santa.getGifts().size() > 0);
	}
	
	@Test
	public void testDeliverGifts() {
		this.child.setGoodness(1f);
		this.santa.addChild(this.child);
		child.putWishListOnWindowSill();
		
		this.santa.deliverGifts();
		assertEquals(2, this.child.getGifts().size());
		assertEquals(0, this.santa.getGifts().size());
	}
}
