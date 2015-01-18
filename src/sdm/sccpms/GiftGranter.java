package sdm.sccpms;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import sdm.sccpms.exceptions.UndefinedProductException;
import sdm.sccpms.products.BicycleFactory;
import sdm.sccpms.products.CatFactory;
import sdm.sccpms.products.IPodFactory;
import sdm.sccpms.products.KnittingSetFactory;
import sdm.sccpms.products.LaptopFactory;
import sdm.sccpms.products.TVSetFactory;

public class GiftGranter implements Observer {
	private ChildRecord childRecord;
	private GiftFactory giftFactory;
	private boolean wishGranted;
	private Map<String, ProductFactoryInterface> productFactories = new HashMap<String, ProductFactoryInterface>();

	public GiftGranter(ChildRecord childRecord) {		
		this.childRecord = childRecord;
	}

	public void onWishListFinished(Child child) throws UndefinedProductException {
		if (child.getGoodness() >= 0.5f)
		{
			this.setWishGranted(true);
			
			// ADD CHILD RECORD
			ChristmasRecord currentChristmasRecord = new ChristmasRecord(child.getWishList());
			this.childRecord.addChristmasRecord(currentChristmasRecord);
			
			// CREATE GIFT
			for (int i = 0; i < child.getWishList().size(); i++)
			{								
				String productName = child.getWishList().get(i);
				checkWhichFactory(productName);
				
				GiftWrap giftWrap = new GiftWrap("green", "blue stripes");
				List<GiftWrap> giftWraps = new LinkedList<GiftWrap>();
				giftWraps.add(giftWrap);

				this.giftFactory = new GiftFactory(this.productFactories, giftWraps);				
				this.giftFactory.createGift(productName);
			}
		}
	}
	
	private void checkWhichFactory(String productName) {
		switch (productName) {
			case "i-pod":
				this.productFactories.put(productName, new IPodFactory());
				break;
			case "bicycle":
				this.productFactories.put(productName, new BicycleFactory());
				break;
			case "cat":
				this.productFactories.put(productName, new CatFactory());
				break;
			case "knitting-set":
				this.productFactories.put(productName, new KnittingSetFactory());
				break;
			case "laptop":
				this.productFactories.put(productName, new LaptopFactory());
				break;
			case "tv-set":
				this.productFactories.put(productName, new TVSetFactory());
				break;
			default:
				break;
		}		
	}

	public boolean isWishGranted() {
		return wishGranted;
	}

	public void setWishGranted(boolean wishGranted) {
		this.wishGranted = wishGranted;
	}

	@Override
	public void update(Observable o, Object arg) {					
		try {
			this.onWishListFinished(((Child)o));
		} catch (UndefinedProductException e) {
			e.printStackTrace();
		}
	}
} 