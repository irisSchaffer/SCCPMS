package sdm.sccpms;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SantaClaus implements WishGranterInterface {
	private Map<Child, ChildRecord> childRecords;
	private List<Gift> gifts;
	private GiftFactory giftFactory;
	private boolean wishGranted;

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
		}
	}
	
	@Override
	public void onWishListFinished(Child child) {
		if (child.getGoodness() >= 0.5f)
		{			
			ChristmasRecord christmasRecord = new ChristmasRecord(child.getWishList());
			this.addChristmasRecordforChild(christmasRecord, child);
			
			for (String wish: child.takeWishList()) {	
				this.gifts.add(this.giftFactory.createGift(wish));
			}
			
			this.wishGranted = true;
		}
	}
	
	public void addChristmasRecordforChild(ChristmasRecord christmasRecord, Child child) {
		if (! this.childRecords.containsKey(child)) {
			this.addChild(child);
		}
		
		ChildRecord childRecord = this.childRecords.get(child);
		childRecord.addChristmasRecord(christmasRecord);
	}

	public boolean isWishGranted() {
		return wishGranted;
	}

	public void setWishGranted(boolean wishGranted) {
		this.wishGranted = wishGranted;
	}

	public void setGiftFactory(GiftFactory giftFactory) {
		this.giftFactory = giftFactory;
	}
} 