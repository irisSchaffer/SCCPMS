package sdm.sccpms.child;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import sdm.sccpms.products.Bicycle;
import sdm.sccpms.products.IPod;

public class WishListOpenStateTest {
	ChildProxy child;
	
	@Before
	public void init() {
		this.child = new ChildProxy("Tom", "Address");
		WishListState openState = new WishListOpenState(child);
		WishListState closeState = new WishListClosedState(child);
		this.child.setWishListClosedState(closeState);
		this.child.setWishListOpenState(openState);
		this.child.setWishListState(this.child.getWishListOpenState());
	}
	
	@Test
	public void testAddToWishList() {
		this.child.addToWishList(IPod.ID);
		assertEquals(1, this.child.getWishList().size());
	}
	
	@Test(expected=IllegalStateException.class)
	public void testTakeWishList() {
		List<String> wishList = this.child.takeWishList();
	}
	
	@Test
	public void testPutWishListOnWindowSill() {
		this.child.putWishListOnWindowSill();
		assertTrue(this.child.getWishListState() instanceof WishListClosedState);
	}
}
