package sdm.sccpms.child;

import sdm.sccpms.WishGranterInterface;

public class ChildCreator {
	private WishGranterInterface wishGranter;
	
	public ChildCreator(WishGranterInterface giftGranter) {
		super();
		this.wishGranter = giftGranter;
	}
	
	public ChildProxy create(String name, String address, float goodness) {
		return this.initChild(new ChildProxy(name, address, goodness));
	}
	
	public ChildProxy create(String name, String address) {		
		return this.initChild(new ChildProxy(name, address));
	}
	
	private ChildProxy initChild(ChildProxy child) {
		child.setWishGranter(this.wishGranter);
		
		child.setWishListClosedState(new WishListClosedState(child));
		child.setWishListOpenState(new WishListOpenState(child));
		child.setWishListState(child.getWishListOpenState());
		
		return child;
	}
	
	public WishGranterInterface getGiftGranter() {
		return wishGranter;
	}
	public void setGiftGranter(WishGranterInterface giftGranter) {
		this.wishGranter = giftGranter;
	}
	
}
