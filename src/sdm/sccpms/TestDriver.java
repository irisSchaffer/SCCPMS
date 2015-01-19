package sdm.sccpms;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import sdm.sccpms.child.Child;
import sdm.sccpms.child.ChildCreator;
import sdm.sccpms.gift.BinaryGiftGivingStrategy;
import sdm.sccpms.gift.GiftFactory;
import sdm.sccpms.gift.GiftGivingStrategyInterface;
import sdm.sccpms.gift.GiftWrap;
import sdm.sccpms.products.*;

public class TestDriver {
	
	public static void main(String[] args) {
		Map<String, ProductFactoryInterface> productFactories = getProductFactories();
		List<GiftWrap> giftWraps = getGiftWraps();
		
		GiftFactory giftFactory = new GiftFactory(productFactories, giftWraps);
		
		GiftGivingStrategyInterface giftGivingStrategy = new BinaryGiftGivingStrategy();
		
		SantaClausHQ santaHQ = new SantaClausHQ(giftFactory, giftGivingStrategy);
		
		ChildCreator creator = new ChildCreator(santaHQ);
		
		Child tim = creator.create("Tim", "Street 10, AA1 BB23 City, UK");
		Child chloe = creator.create("Chloé", "Rue 945, 12345 Ville, France", 0.0f);
		Child manuel = creator.create("Manuel", "Straße 1, 1000 Stadt, Österreich");
		
		santaHQ.addChild(tim);
		santaHQ.addChild(chloe);
		santaHQ.addChild(manuel);
		
		System.out.println("TIM:\n");
		
		tim.doGood();
		tim.doGood();
		tim.addToWishList(IPod.ID);
		tim.doBad();
		tim.addToWishList(TVSet.ID);
		tim.doGood();
		tim.addToWishList(Laptop.ID);
		
		tim.putWishListOnWindowSill();
		
		tim.doBad();
		tim.doBad();

		System.out.println("\n\nCHLOÉ:\n");

		
		chloe.doGood();
		chloe.doGood();
		chloe.addToWishList(Book.ID);
		chloe.doGood();
		chloe.addToWishList(Bicycle.ID);
		chloe.doGood();
		chloe.addToWishList(Laptop.ID);
		
		chloe.putWishListOnWindowSill();

		System.out.println("\n\nMANUEL:\n");
		
		manuel.doBad();
		manuel.addToWishList(KnittingSet.ID);
		manuel.addToWishList(TVSet.ID);
		manuel.putWishListOnWindowSill();
		
		System.out.println("\n\nCHRISTMAS EVE!\n");
		tim.putOutCookiesAndMilk();
		chloe.putOutCookiesAndMilk();
		
		System.out.println("\nSANTA IS STARTING TO DELIVER PRESENTS...:\n");
		
		santaHQ.deliverGifts();
	}
	
	private static Map<String, ProductFactoryInterface> getProductFactories() {
		Map<String, ProductFactoryInterface> productFactories = new HashMap<String, ProductFactoryInterface>();
		productFactories.put(IPod.ID, new IPodFactory());
		productFactories.put(Bicycle.ID, new BicycleFactory());
		productFactories.put(Book.ID, new BookFactory());
		productFactories.put(KnittingSet.ID, new KnittingSetFactory());
		productFactories.put(Laptop.ID, new LaptopFactory());
		productFactories.put(TVSet.ID, new TVSetFactory());
		
		return productFactories;
	}

	private static List<GiftWrap> getGiftWraps() {
		List<GiftWrap> giftWraps = new LinkedList<GiftWrap>();
		giftWraps.add(new GiftWrap("green", "yellow dots"));
		giftWraps.add(new GiftWrap("yellow", "red stripes"));
		giftWraps.add(new GiftWrap("red", "white santas"));
		giftWraps.add(new GiftWrap("black", "silver stars"));
		giftWraps.add(new GiftWrap("white", "blue snowflakes"));
		
		return giftWraps;
	}
}
