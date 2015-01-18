package sdm.sccpms;

public class WishListOpenState implements State {

	@Override
	public boolean canUseAddToWishListMethod() {
		return true;
	}

	@Override
	public boolean canUseTakeWishListMethod() {
		return false;
	}

}
