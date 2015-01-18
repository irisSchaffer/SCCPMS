package sdm.sccpms;


public class WishListClosedState implements State {

	@Override
	public boolean canUseAddToWishListMethod() {
		return false;
	}

	@Override
	public boolean canUseTakeWishListMethod() {
		return true;
	}

}
