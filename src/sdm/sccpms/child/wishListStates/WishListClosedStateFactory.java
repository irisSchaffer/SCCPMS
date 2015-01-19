package sdm.sccpms.child.wishListStates;

import sdm.sccpms.child.ChildProxy;
import sdm.sccpms.child.WishListStateFactoryInterface;
import sdm.sccpms.child.WishListStateInterface;

public class WishListClosedStateFactory implements WishListStateFactoryInterface {

	@Override
	public WishListStateInterface create(ChildProxy child) {
		return new WishListClosedState(child);
	}

}
