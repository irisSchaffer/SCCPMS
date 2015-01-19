package sdm.sccpms;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import sdm.sccpms.gift.Gift;
import sdm.sccpms.gift.GiftFactory;
import sdm.sccpms.gift.GiftGivingStrategyInterface;

public class SantaClausHQ implements WishGranterInterface {
	private Map<Child, ChildRecord> childRecords;
	private List<Gift> gifts;
	private GiftFactory giftFactory;
	private GiftGivingStrategyInterface giftGivingStrategy;

	public SantaClausHQ(GiftFactory giftFactory, GiftGivingStrategyInterface giftGivingStrategy) {		
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
		System.out.format("Santa Clause takes %s's wish list.\n", child.getName());
		ChristmasRecord christmasRecord = new ChristmasRecord(child.takeWishList());
		this.addChristmasRecordforChild(christmasRecord, child);
		
		System.out.format("\nElves work on gifts for %s:\n", child.getName());
		for (String wish: christmasRecord.getWishList()) {
			System.out.print(" - ");
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
		this.putGiftsOnSleigh();
		
		for (Map.Entry<Child, ChildRecord> entry: this.childRecords.entrySet()) {
			if( null != entry.getValue().getCurrentChristmasRecord()) {
				ChildRecord childRecord = entry.getValue();
				deliverGiftsForChild(childRecord.getChild(), childRecord.getCurrentChristmasRecord());
			}
		}
		
		this.gifts.clear();
	}

	private void putGiftsOnSleigh() {
		System.out.println("Putting all gifts on sleigh.");
	}

	private void deliverGiftsForChild(Child child, ChristmasRecord christmasRecord) {
		this.sendSantaToAddress(child.getAddress());
		
		if (child.hasPutOutCookiesAndMilk()) {
			child.doGood();
		}
		
		christmasRecord.setGoodness(child.getGoodness());
		
		List<Gift> gifts = this.findGiftsForChild(child);
		gifts = this.giftGivingStrategy.getGifts(gifts, christmasRecord.getGoodness());
		
		child.receiveGifts(gifts);
	}

	private void sendSantaToAddress(String address) {
		System.out.format("\nSanta Claus is flying to %s\n", address);
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
