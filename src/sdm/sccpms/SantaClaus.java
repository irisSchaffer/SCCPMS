package sdm.sccpms;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import sdm.sccpms.gift.Gift;
import sdm.sccpms.gift.GiftFactory;

public class SantaClaus implements WishGranterInterface {
	private Map<Child, ChildRecord> childRecords;
	private List<Gift> gifts;
	private GiftFactory giftFactory;

	public SantaClaus(GiftFactory giftFactory) {		
		this.giftFactory = giftFactory;
		this.childRecords = new HashMap<Child, ChildRecord>();
		this.gifts = new LinkedList<Gift>();
	}
	
	public SantaClaus(GiftFactory giftFactory, Iterable<Child> children) {
		this(giftFactory);
		for (Child c: children) {
			this.addChild(c);
		}
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
		if (child.getGoodness() >= 0.5f)
		{			
			ChristmasRecord christmasRecord = new ChristmasRecord(child.getWishList());
			this.addChristmasRecordforChild(christmasRecord, child);
			
			for (String wish: child.getWishList()) {								
				this.gifts.add(this.giftFactory.createGift(wish));
			}			
		}
	}
	
	public void addChristmasRecordforChild(ChristmasRecord christmasRecord, Child child) {
		if (! this.childRecords.containsKey(child)) {
			this.addChild(child);
		}
		
		ChildRecord childRecord = this.childRecords.get(child);
		childRecord.addChristmasRecord(christmasRecord);
	}
	
	public List<Gift> getGifts() {
		return this.gifts;
	}

	public void setGiftFactory(GiftFactory giftFactory) {
		this.giftFactory = giftFactory;
	}

	public Map<Child, ChildRecord> getChildRecords() {
		return this.childRecords;
	}
} 