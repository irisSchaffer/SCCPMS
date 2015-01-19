package sdm.sccpms.child;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import sdm.sccpms.ProductFactoryInterface;
import sdm.sccpms.SantaClausHQ;
import sdm.sccpms.child.wishListStates.WishListClosedState;
import sdm.sccpms.child.wishListStates.WishListOpenState;
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

public class ChildProxyTest {
	ChildProxy child;
	SantaClausHQ santa;
	
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
		
		GiftFactory giftFactory = new GiftFactory(productFactories, giftWraps);

		this.santa = new SantaClausHQ(giftFactory, new BinaryGiftGivingStrategy());
	}
	
	@Test
	public void testObserver() {
		this.child = new ChildProxy("Tom", "Address");
		WishListState openState = new WishListOpenState(child);
		WishListState closeState = new WishListClosedState(child);
		this.child.setWishListClosedState(closeState);
		this.child.setWishListOpenState(openState);
		this.child.setWishListState(this.child.getWishListOpenState());
		
		this.child.setWishGranter(this.santa);
		
		this.child.addToWishList(IPod.ID);
		this.child.putWishListOnWindowSill();
		
		assertEquals(1, this.santa.getGifts().size());
	}

}
