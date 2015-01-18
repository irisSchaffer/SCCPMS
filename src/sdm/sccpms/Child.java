package sdm.sccpms;

import java.util.LinkedList;
import java.util.List;

import sdm.sccpms.gift.Gift;
import sdm.sccpms.exceptions.UnsupportedMethodException;

public class Child {
	public static final float INITIAL_GOODNESS = .5f;
	public static final float GOODNESS_CHANGE = .1f;
	
	private String name;
	private String address;
	private float goodness;
	private boolean finishedWishList = false;
	private List<String> wishList = new LinkedList<String>();
	private WishGranterInterface wishGranter;
	private List<Gift> gifts;
	private boolean hasPutOutCookiesAndMilk;
	
	private WishListClosedState wishListClosed = new WishListClosedState();
	private WishListOpenState wishListOpen = new WishListOpenState();
	public State childState = wishListOpen;
	
	public Child(String name, String address) {
		this(name, address, Child.INITIAL_GOODNESS);		
	}
	
	public Child(String name, String address, float goodness) {
		this.name = name;
		this.address = address;
		this.goodness = goodness;
		this.gifts = new LinkedList<Gift>();
		this.hasPutOutCookiesAndMilk = false;
	}

	public List<String> getWishList() {
		return wishList;
	}
	
	public void addToWishList(String wish) {
		if (! this.childState.canUseAddToWishListMethod()) {
			throw new UnsupportedMethodException(
				String.format("addToWishList method can't be called!")
			);
		}
		
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

	public List<Gift> getGifts() {
		return gifts;
	}

	public void addGift(Gift gift) {
		System.out.println(String.format("%s receives %s", this.getName(), gift));
		this.gifts.add(gift);
	}

	public WishGranterInterface getWishGranter() {
		return wishGranter;
	}

	public void setWishGranter(WishGranterInterface wishGranter) {
		this.wishGranter = wishGranter;
	}

	public boolean isFinishedWishList() {
		return finishedWishList;
	}

	public State getChildState() {
		return childState;
	}

	public void setChildState(State childState) {
		this.childState = childState;
	}
	
	public List<String> takeWishList() {
		if (! this.childState.canUseTakeWishListMethod()) {
			throw new UnsupportedMethodException(
				String.format("takeWishList method can't be called!")
			);
		}
		
		List<String> wishList = new LinkedList<String>(this.wishList);		
		this.wishList.clear();
				
		this.changeState();
		
		return wishList;
	}

	public void putWishListOnWindowSill() {	
		// state not changed for showing exception
		this.changeState();
		
		this.finishedWishList = true;
		this.wishGranter.onWishListFinished(this);
	}
	
	public void changeState() {
		if (this.childState == this.wishListClosed)
			this.setChildState(this.wishListOpen);
		else
			this.setChildState(this.wishListClosed);
	}

	public boolean hasPutOutCookiesAndMilk() {
		return this.hasPutOutCookiesAndMilk;
	}
	
	public void putOutCookiesAndMild() {
		this.hasPutOutCookiesAndMilk = true;
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
