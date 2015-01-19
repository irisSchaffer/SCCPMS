package sdm.sccpms.child;

import java.util.List;

import sdm.sccpms.WishGranterInterface;

public class ChildProxy extends Child {
	
	WishListStateInterface wishListOpenState;
	WishListStateInterface wishListClosedState;
	WishListStateInterface wishListState;
	WishGranterInterface wishGranter;

	public ChildProxy(String name, String address, float goodness) {
		super(name, address, goodness);
	}

	public ChildProxy(String name, String address) {
		super(name, address);
	}
	
	public void addToWishList(String wish) {
		this.wishListState.addToWishList(wish);
	}
	
	public List<String> takeWishList() {
		return this.wishListState.takeWishList();
	}
	
	public void putWishListOnWindowSill() {
		this.wishListState.putWishListOnWindowSill();
		
		if (null != this.wishGranter) {
			this.wishGranter.onWishListFinished(this);			
		}
		
		super.putWishListOnWindowSill();
	}

	public WishListStateInterface getWishListOpenState() {
		return wishListOpenState;
	}

	public void setWishListOpenState(WishListStateInterface wishListOpenState) {
		this.wishListOpenState = wishListOpenState;
	}

	public WishListStateInterface getWishListClosedState() {
		return wishListClosedState;
	}

	public void setWishListClosedState(WishListStateInterface wishListClosedState) {
		this.wishListClosedState = wishListClosedState;
	}

	public WishGranterInterface getWishGranter() {
		return wishGranter;
	}

	public void setWishGranter(WishGranterInterface wishGranter) {
		this.wishGranter = wishGranter;
	}

	public WishListStateInterface getWishListState() {
		return wishListState;
	}

	public void setWishListState(WishListStateInterface wishListState) {
		this.wishListState = wishListState;
	}
}
