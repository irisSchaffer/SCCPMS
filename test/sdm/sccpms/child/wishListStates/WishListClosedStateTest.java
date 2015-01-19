package sdm.sccpms.child.wishListStates;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import sdm.sccpms.child.ChildProxy;
import sdm.sccpms.child.WishListState;
import sdm.sccpms.child.wishListStates.WishListClosedState;
import sdm.sccpms.child.wishListStates.WishListOpenState;
import sdm.sccpms.products.Bicycle;
import sdm.sccpms.products.IPod;

public class WishListClosedStateTest {
	ChildProxy child;
	
	@Before
	public void init() {
		this.child = new ChildProxy("Alex", "Address");
		WishListState openState = new WishListOpenState(child);
		WishListState closeState = new WishListClosedState(child);
		this.child.setWishListClosedState(closeState);
		this.child.setWishListOpenState(openState);
		this.child.setWishListState(this.child.getWishListClosedState());
	}
	
	@Test(expected=IllegalStateException.class)
	public void testAddToWishList() {
		this.child.addToWishList(IPod.ID);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testPutWishListOnWindowSill() {
		this.child.putWishListOnWindowSill();
	}

	@Test
	public void testTakeWishList() {
		this.child.getWishList().add(Bicycle.ID);
		List<String> wishList = this.child.takeWishList();
		assertEquals(1, wishList.size());
		assertEquals(0, this.child.getWishList().size());
		assertTrue(this.child.getWishListState() instanceof WishListOpenState);
	}
}
