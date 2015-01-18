package sdm.sccpms;

import java.util.LinkedList;
import java.util.List;

import sdm.sccpms.exceptions.UnsupportedMethodException;

/**
 * TODO: Add state pattern to child depending on if wish list has already been put on window sill or not.
 * @author iris
 *
 */
public class Child {
	public static final float INITIAL_GOODNESS = .5f;
	public static final float GOODNESS_CHANGE = .1f;
	
	private String name;
	private String address;
	private float goodness;
	private boolean finishedWishList = false;
	private List<String> wishList = new LinkedList<String>();
	private WishGranterInterface wishGranter;
	
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
	}

	public List<String> getWishList() {
		return wishList;
	}
	
	public void addToWishList(String wish) {
		if (this.childState == this.wishListClosed) {
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
		List<String> wishList = new LinkedList<String>(this.wishList);		
		this.wishList.clear();
				
		this.changeState();
		
		return wishList;
	}

	public void putWishListOnWindowSill() {		
		this.finishedWishList = true;
		this.wishGranter.onWishListFinished(this);
		
		// state not changed for showing exception
		// this.changeState();
	}	
	
	public void changeState() {
		if (this.childState == this.wishListClosed)
			this.setChildState(this.wishListOpen);
		else
			this.setChildState(this.wishListClosed);
	}
}
