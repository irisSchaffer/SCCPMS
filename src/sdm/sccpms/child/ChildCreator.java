package sdm.sccpms.child;

import sdm.sccpms.WishGranterInterface;

public class ChildCreator {
	private WishGranterInterface wishGranter;
	private WishListClosedState wishListClosedState;
	private WishListOpenState wishListOpenState;
	
	public ChildCreator(
		WishGranterInterface giftGranter,
		WishListClosedState wishListClosedState,
		WishListOpenState wishListOpenState
	) {
		super();
		this.wishGranter = giftGranter;
		this.wishListClosedState = wishListClosedState;
		this.wishListOpenState = wishListOpenState;
	}
	
	public Child create(String name, String address, float goodness) {
		return this.initChild(new ChildProxy(name, address, goodness));
	}
	
	public Child create(String name, String address) {		
		return this.initChild(new ChildProxy(name, address));
	}
	
	private Child initChild(ChildProxy child) {
		child.setWishGranter(this.wishGranter);
		child.setWishListClosedState(this.wishListClosedState);
		child.setWishListOpenState(this.wishListOpenState);
		
		return child;
	}
	
	public WishGranterInterface getGiftGranter() {
		return wishGranter;
	}
	public void setGiftGranter(WishGranterInterface giftGranter) {
		this.wishGranter = giftGranter;
	}
	public WishListClosedState getWishListClosedState() {
		return wishListClosedState;
	}
	public void setWishListClosedState(WishListClosedState wishListClosedState) {
		this.wishListClosedState = wishListClosedState;
	}
	public WishListOpenState getWishListOpenState() {
		return wishListOpenState;
	}
	public void setWishListOpenState(WishListOpenState wishListOpenState) {
		this.wishListOpenState = wishListOpenState;
	}
	
	
}
