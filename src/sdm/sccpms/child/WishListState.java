package sdm.sccpms.child;

public abstract class WishListState implements WishListStateInterface {
	protected ChildProxy child;

	public Child getChild() {
		return child;
	}

	public void setChild(ChildProxy child) {
		this.child = child;
	}

	
}
