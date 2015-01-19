package sdm.sccpms.child.wishListStates;

import java.util.LinkedList;
import java.util.List;

import sdm.sccpms.child.ChildProxy;
import sdm.sccpms.child.WishListState;

public class WishListClosedState extends WishListState {

	public WishListClosedState(ChildProxy child) {
		super(child);
	}

	@Override
	public void addToWishList(String wish) {
		throw new IllegalStateException("Cannot add wish to list wish, list is already lying on window sill waiting for gift wisher to grab it.");
	}

	@Override
	public void putWishListOnWindowSill() {
		throw new IllegalStateException("Cannot put wish list on window sill, because it's already there.");
	}

	@Override
	public List<String> takeWishList() {
		List<String> wishList = (List<String>) ((LinkedList<String>) this.child.getWishList()).clone();
		this.child.getWishList().clear();
		
		this.child.setWishListState(this.child.getWishListOpenState());
		
		return wishList;
	}

}
