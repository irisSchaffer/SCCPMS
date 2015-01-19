package sdm.sccpms.child;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import sdm.sccpms.products.Bicycle;
import sdm.sccpms.products.IPod;

public class WishListClosedStateTest {
	Child child;
	
	@Before
	public void init() {
		this.child = new ChildProxy("Alex", "Address");
		this.child.setWishListClosedState(new WishListClosedState());
		this.child.setWishListOpenState(new WishListOpenState());
		
		this.child.setWishState(this.child.getWishListClosedState());
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
		assertEquals(0, this.child.getWishList());
		assertTrue(this.child.getWishListState() instanceof WishListOpenState);
	}
}
