package sdm.sccpms;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import sdm.sccpms.gift.Gift;
import sdm.sccpms.gift.GiftFactory;
import sdm.sccpms.gift.GiftGivingStrategyInterface;

public class SantaClaus implements WishGranterInterface {
	private Map<Child, ChildRecord> childRecords;
	private List<Gift> gifts;
	private GiftFactory giftFactory;
	private GiftGivingStrategyInterface giftGivingStrategy;

	public SantaClaus(GiftFactory giftFactory, GiftGivingStrategyInterface giftGivingStrategy) {		
		this.giftFactory = giftFactory;
		this.giftGivingStrategy = giftGivingStrategy;
		this.childRecords = new HashMap<Child, ChildRecord>();
		this.gifts = new LinkedList<Gift>();
	}
	
	public void addChild(Child child) {
		if (! this.childRecords.containsKey(child)) {
			ChildRecord cr = new ChildRecord(child);
			this.childRecords.put(child, cr);
			child.setWishGranter(this);
		}
	}
	
	@Override
	public void onWishListFinished(Child child) {
		ChristmasRecord christmasRecord = new ChristmasRecord(child.takeWishList(), child.getGoodness());
		this.addChristmasRecordforChild(christmasRecord, child);
		
		for (String wish: christmasRecord.getWishList()) {
			this.gifts.add(this.giftFactory.createGift(wish, child));
		}
	}
	
	public void addChristmasRecordforChild(ChristmasRecord christmasRecord, Child child) {
		if (! this.childRecords.containsKey(child)) {
			this.addChild(child);
		}
		
		ChildRecord childRecord = this.childRecords.get(child);
		childRecord.addChristmasRecord(christmasRecord);
	}
	
	public void deliverGifts() {
		for (Map.Entry<Child, ChildRecord> entry: this.childRecords.entrySet()) {
			if( null != entry.getValue().getCurrentChristmasRecord()) {
				Child child = entry.getValue().getChild();
				ChristmasRecord christmasRecord = entry.getValue().getCurrentChristmasRecord();				
				
				this.flyToAddress(child.getAddress());
				
				if (child.hasPutOutCookiesAndMilk()) {
					child.doGood();
					christmasRecord.setGoodness(child.getGoodness());
				}
				
				List<Gift> gifts = this.findGiftsForChild(child);
				gifts = this.giftGivingStrategy.getGifts(gifts, christmasRecord.getGoodness());
				
				for (Gift gift: gifts) {
					child.addGift(gift);
				}
			}
		}
		
		this.gifts.clear();
	}

	private void flyToAddress(String address) {
		System.out.println(String.format("Santa Claus is flying to %s", address));
	}
	
	private List<Gift> findGiftsForChild(Child child) {
		List<Gift> gifts = new LinkedList<Gift>();

		for (Gift gift: this.gifts) {
			if (gift.getChild().equals(child)) {
				gifts.add(gift);
			}
		}
		
		return gifts;
	}

	public List<Gift> getGifts() {
		return this.gifts;
	}
	
	public GiftFactory getGiftFactory() {
		return this.giftFactory;
	}

	public void setGiftFactory(GiftFactory giftFactory) {
		this.giftFactory = giftFactory;
	}

	public GiftGivingStrategyInterface getGiftGivingStrategy() {
		return giftGivingStrategy;
	}

	public void setGiftGivingStrategy(GiftGivingStrategyInterface giftGivingStrategy) {
		this.giftGivingStrategy = giftGivingStrategy;
	}

	public Map<Child, ChildRecord> getChildRecords() {
		return this.childRecords;
	}
} 
