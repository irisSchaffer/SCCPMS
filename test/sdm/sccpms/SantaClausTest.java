package sdm.sccpms;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import sdm.sccpms.child.Child;
import sdm.sccpms.child.ChildCreator;
import sdm.sccpms.child.wishListStates.WishListClosedState;
import sdm.sccpms.child.wishListStates.WishListClosedStateFactory;
import sdm.sccpms.child.wishListStates.WishListOpenState;
import sdm.sccpms.child.wishListStates.WishListOpenStateFactory;
import sdm.sccpms.gift.BinaryGiftGivingStrategy;
import sdm.sccpms.gift.GiftFactory;
import sdm.sccpms.gift.GiftWrap;
import sdm.sccpms.products.Bicycle;
import sdm.sccpms.products.BicycleFactory;
import sdm.sccpms.products.Book;
import sdm.sccpms.products.BookFactory;
import sdm.sccpms.products.IPod;
import sdm.sccpms.products.IPodFactory;
import sdm.sccpms.products.KnittingSet;
import sdm.sccpms.products.KnittingSetFactory;
import sdm.sccpms.products.Laptop;
import sdm.sccpms.products.LaptopFactory;
import sdm.sccpms.products.TVSet;
import sdm.sccpms.products.TVSetFactory;

public class SantaClausTest {	
	SantaClausHQ santa;
	GiftFactory giftFactory;
	private Child child;

	@Before
	public void init() {		
		Map<String, ProductFactoryInterface> productFactories = new HashMap<String, ProductFactoryInterface>();
		productFactories.put(IPod.ID, new IPodFactory());
		productFactories.put(Bicycle.ID, new BicycleFactory());
		productFactories.put(Book.ID, new BookFactory());
		productFactories.put(KnittingSet.ID, new KnittingSetFactory());
		productFactories.put(Laptop.ID, new LaptopFactory());
		productFactories.put(TVSet.ID, new TVSetFactory());

		List<GiftWrap> giftWraps = new LinkedList<GiftWrap>();
		giftWraps.add(new GiftWrap("blue", "white dots"));
		giftWraps.add(new GiftWrap("green", "yellow stripes"));
		
		this.giftFactory = new GiftFactory(productFactories, giftWraps);

		this.santa = new SantaClausHQ(giftFactory, new BinaryGiftGivingStrategy());
		
		ChildCreator creator = new ChildCreator(this.santa, new WishListOpenStateFactory(), new WishListClosedStateFactory());
		this.child = creator.create("Max", "Street 9, 1001 City, Country");		
		this.child.addToWishList(IPod.ID);		
		this.child.addToWishList(Book.ID);	
	}
	
	@Test
	public void testAddChild() {
		this.santa.addChild(child);
		assertEquals(1, this.santa.getChildRecords().size());
	}
	
	@Test
	public void testAddChristmasRecordForExistingChild() {
		this.santa.addChild(child);
		
		ChristmasRecord record = new ChristmasRecord(child.getWishList(), 2009);
		this.santa.addChristmasRecordforChild(record, child);
		
		assertEquals(record, this.santa.getChildRecords().get(child).getChristmasRecordForYear(2009));
	}
	
	@Test
	public void testAddChristmasRecordForNewChild() {
		ChristmasRecord record = new ChristmasRecord(child.getWishList());
		this.santa.addChristmasRecordforChild(record, child);
		
		assertNotNull(this.santa.getChildRecords().get(child));
		assertEquals(record, this.santa.getChildRecords().get(child).getCurrentChristmasRecord());
	}
	
	@Test
	public void testOnWishListFinished() {
		this.santa.addChild(this.child);
		
		this.child.putWishListOnWindowSill();
		
		assertTrue(this.santa.getGifts().size() > 0);
		assertEquals(0, this.child.getWishList().size());
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
