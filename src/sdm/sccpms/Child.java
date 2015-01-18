package sdm.sccpms;

import java.util.LinkedList;
import java.util.List;

import sdm.sccpms.gift.Gift;

public class Child {
	public static final float INITIAL_GOODNESS = .5f;
	public static final float GOODNESS_CHANGE = .1f;
	
	private String name;
	private String address;
	private float goodness;
	private boolean hasPutOutCookiesAndMilk;
	
	private List<String> wishList;
	private List<Gift> gifts;
	private WishGranterInterface wishGranter;
		
	public Child(String name, String address) {
		this(name, address, Child.INITIAL_GOODNESS);		
	}
	
	public Child(String name, String address, float goodness) {
		this.name = name;
		this.address = address;
		this.goodness = goodness;
		
		this.wishList = new LinkedList<String>();
		this.gifts = new LinkedList<Gift>();
		this.hasPutOutCookiesAndMilk = false;
	}

	public List<String> getWishList() {
		return wishList;
	}
	
	public List<String> takeWishList() {
		List<String> wishList = (List<String>) ((LinkedList<String>) this.wishList).clone();
		this.wishList.clear();
		
		return wishList;
	}
	
	public void addToWishList(String wish) {
		System.out.format("%s adds item '%s' to wish list\n", this.getName(), wish);
		this.wishList.add(wish);
	}
	
	public void doGood(float good) {
		this.setGoodness(this.goodness + good);
	}
	
	public void doGood() {
		this.doGood(Child.GOODNESS_CHANGE);
	}
	
	public void doBad(float bad) {
		this.setGoodness(this.goodness - bad);
	}
	
	public void doBad() {
		this.doBad(Child.GOODNESS_CHANGE);
	}
	
	public float getGoodness() {
		return goodness;
	}

	public void setGoodness(float goodness) {
		if (goodness > 1f) {
			this.goodness = 1f;
		} else if (goodness < 0f) {
			this.goodness = 0f;
		} else {
			this.goodness = goodness;			
		}
		
		System.out.format("%s's goodness was adjusted: %d%%\n", this.getName(), Math.round(this.getGoodness() * 100));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public boolean hasPutOutCookiesAndMilk() {
		return this.hasPutOutCookiesAndMilk;
	}
	
	public void putOutCookiesAndMilk() {
		System.out.format("%s puts cookies and milk out.\n", this.getName());
		this.hasPutOutCookiesAndMilk = true;
	}

	public List<Gift> getGifts() {
		return gifts;
	}

	public void receiveGifts(List<Gift> gifts) {
		if (0 == gifts.size()) {
			System.out.format("%s doesn't get any gifts this year :(\n", this.getName());
		}
		
		for (Gift gift: gifts) {
			System.out.format("%s receives %s.\n", this.getName(), gift);
			this.gifts.add(gift);			
		}
	}

	public WishGranterInterface getWishGranter() {
		return wishGranter;
	}

	public void setWishGranter(WishGranterInterface wishGranter) {
		this.wishGranter = wishGranter;
	}

	public void putWishListOnWindowSill() {
		System.out.format("%s puts wish list on window sill.\n", this.getName());
		this.wishGranter.onWishListFinished(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Child other = (Child) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	public String toString() {
		return this.getName() + " living at " + this.getAddress();
	}
}
