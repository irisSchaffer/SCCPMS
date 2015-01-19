package sdm.sccpms.child;

import static org.junit.Assert.*;

import org.junit.Test;

import sdm.sccpms.child.Child;

public class ChildTest {
	static final String NAME = "Tom";
	static final String STREET = "Teststreet 5";
	
	@Test
	public void testChildConstructors() {
		Child child = new Child(NAME, STREET);
		assertEquals(NAME, child.getName());
		assertEquals(STREET, child.getAddress());
		assertEquals(Child.INITIAL_GOODNESS, child.getGoodness(), 0.0f);

		float goodness = .7f;
		child = new Child(NAME, STREET, goodness);
		assertEquals(NAME, child.getName());
		assertEquals(STREET, child.getAddress());
		assertEquals(goodness, child.getGoodness(), 0.0f);
	}

	@Test
	public void testChildDoesGoodAndBad() {
		Child child = new Child(NAME, STREET);
		float goodness = Child.INITIAL_GOODNESS;
		
		goodness += Child.GOODNESS_CHANGE;
		child.doGood();
		
		assertEquals(goodness, child.getGoodness(), 0.0f);
		
		goodness += .2f;
		child.doGood(.2f);
		
		assertEquals(goodness, child.getGoodness(), 0.0f);
		
		goodness -= Child.GOODNESS_CHANGE;
		child.doBad();
		
		assertEquals(goodness, child.getGoodness(), 0.0f);
		
		goodness -= .01f;
		child.doBad(.01f);
		assertEquals(goodness, child.getGoodness(), 0.0f);
		
		child.doBad(2f);
		assertEquals(0f, child.getGoodness(), 0.0f);

		child.doGood(2f);
		assertEquals(1f, child.getGoodness(), 0.0f);
	}
}
