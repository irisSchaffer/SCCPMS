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
		this.childCreator = new ChildCreator(santa);
	}
	
	@Test
	public void testCreation() {
		ChildProxy tom = this.childCreator.create("Tom", "Address");
		assertEquals("Tom", tom.getName());
		assertEquals("Address", tom.getAddress());
		assertEquals(Child.INITIAL_GOODNESS, tom.getGoodness(), 0.0f);
		
		ChildProxy alex = this.childCreator.create("Alex", "Address2", .7f);
		assertEquals(this.santa, alex.getWishGranter());
		assertEquals("Alex", alex.getName());
		assertEquals("Address2", alex.getAddress());
		assertEquals(.7f, alex.getGoodness(), 0.0f);
	}

}
