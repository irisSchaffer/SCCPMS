package sdm.sccpms.child.wishListStates;

import java.util.LinkedList;
import java.util.List;

import sdm.sccpms.child.ChildProxy;
import sdm.sccpms.child.WishListState;

public class WishListOpenState extends WishListState {

	public WishListOpenState(ChildProxy child) {
		super(child);
	}

	@Override
	public void addToWishList(String wish) {
		this.child.getWishList().add(wish);
	}

	@Override
	public void putWishListOnWindowSill() {
		this.child.setWishListState(this.child.getWishListClosedState());
	}

	@Override
	public List<String> takeWishList() {
		throw new IllegalStateException("Cannot take wish list, because it's not finished and lying on the window sill yet.");
	}

}
