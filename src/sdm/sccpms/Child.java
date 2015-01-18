package sdm.sccpms;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class Child extends Observable {
	public static final float INITIAL_GOODNESS = .5f;
	public static final float GOODNESS_CHANGE = .1f;
	
	private String name;
	private String address;
	private float goodness;
	private boolean finishedWishList = false;
	private List<String> wishList = new LinkedList<String>();
	
	public List<String> getWishList() {
		return wishList;
	}

	public void addToWishList(String wish) {
		this.wishList.add(wish);
	}

	public Child(String name, String address) {
		this(name, address, Child.INITIAL_GOODNESS);
	}
	
	public Child(String name, String address, float goodness) {
		this.name = name;
		this.address = address;
		this.goodness = goodness;
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

	public boolean isFinishedWishList() {
		return finishedWishList;
	}

	public void setFinishedWishList(boolean finishedWishList) {
		this.finishedWishList = finishedWishList;
	}

	public void putWishListOnWindowSill() {
		this.setFinishedWishList(true);
		this.setChanged();
		this.notifyObservers();	
	}		
}
