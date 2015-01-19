package sdm.sccpms.child;

import sdm.sccpms.WishGranterInterface;

public class ChildCreator {
	private WishGranterInterface wishGranter;
	private WishListStateFactoryInterface openStateFactory;
	private WishListStateFactoryInterface closedStateFactory;
	
	public ChildCreator(
		WishGranterInterface giftGranter,
		WishListStateFactoryInterface openStateFactory,
		WishListStateFactoryInterface closedStateFactory
	) {
		super();
		this.wishGranter = giftGranter;
		this.openStateFactory = openStateFactory;
		this.closedStateFactory = closedStateFactory;
	}
	
	public ChildProxy create(String name, String address, float goodness) {
		return this.initChild(new ChildProxy(name, address, goodness));
	}
	
	public ChildProxy create(String name, String address) {		
		return this.initChild(new ChildProxy(name, address));
	}
	
	private ChildProxy initChild(ChildProxy child) {
		child.setWishGranter(this.wishGranter);
		
		child.setWishListClosedState(this.closedStateFactory.create(child));
		child.setWishListOpenState(this.openStateFactory.create(child));
		child.setWishListState(child.getWishListOpenState());
		
		return child;
	}
	
	public WishGranterInterface getGiftGranter() {
		return wishGranter;
	}
	
	public void setGiftGranter(WishGranterInterface giftGranter) {
		this.wishGranter = giftGranter;
	}

	public WishListStateFactoryInterface getOpenStateFactory() {
		return openStateFactory;
	}

	public void setOpenStateFactory(WishListStateFactoryInterface openStateFactory) {
		this.openStateFactory = openStateFactory;
	}

	public WishListStateFactoryInterface getClosedStateFactory() {
		return closedStateFactory;
	}

	public void setClosedStateFactory(
		WishListStateFactoryInterface closedStateFactory
	) {
		this.closedStateFactory = closedStateFactory;
	}

}
