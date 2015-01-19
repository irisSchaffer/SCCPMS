package sdm.sccpms.child;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sdm.sccpms.SantaClausHQ;
import sdm.sccpms.child.Child;

public class ChildCreatorTest {
	private ChildCreator childCreator;
	private SantaClausHQ santa;
	
	@Before
	public void init() {
		this.santa = new SantaClausHQ(null, null);
		WishListState closedState = new ClosedWishListState();
		WishListState openState = new ClosedWishListState();
		
		this.childCreator = new ChildCreator(santa, openState, closedState);
	}
	
	@Test
	public void testCreation() {
		Child tom = this.childCreator.create("Tom", "Address");
		assertEquals(tom instanceof ChildProxy);
		assertEquals("Tom", tom.getName());
		assertEquals("Address", tom.getAddress());
		assertEquals(Child.INITIAL_GOODNESS, tom.getGoodness(), 0.0f);
		
		Child alex = this.childCreator.create("Alex", "Address2", .7f);
		assertEquals(alex instanceof ChildProxy);
		assertEquals(alex.getWishGranter() == this.santa);
		assertEquals("Alex", tom.getName());
		assertEquals("Address2", tom.getAddress());
		assertEquals(.7f, tom.getGoodness(), 0.0f);
	}

}
