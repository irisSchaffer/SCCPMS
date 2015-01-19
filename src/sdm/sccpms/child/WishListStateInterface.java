package sdm.sccpms.child;

import java.util.List;

public interface WishListStateInterface {
	public void addToWishList(String wish);
	public void putWishListOnWindowSill();
	public List<String> takeWishList();
}
