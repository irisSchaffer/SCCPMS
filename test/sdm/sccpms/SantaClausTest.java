package sdm.sccpms;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

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

	@Before
	public void init() {
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
		
		this.santa = new SantaClaus(giftFactory);				
	}
	
	@Test
	public void testAddChild() {
		Child child = this.getChild();
		this.santa.addChild(child);
		assertEquals(1, this.santa.getChildRecords().size());
		assertTrue(child.getWishGranter() instanceof SantaClaus);
	}
	
	@Test
	public void testOnWishListFinished() {
		Child child = this.getChild();
		this.santa.addChild(child);
		
		child.putWishListOnWindowSill();
		
		assertTrue(this.santa.getGifts().size() > 0);
	}
	
	private Child getChild() {
		Child child = new Child("Max", "Street 9, 1001 City, Country");		
		child.addToWishList("i-pod");		
		child.addToWishList("cat");
		
		return child;
	}
}
