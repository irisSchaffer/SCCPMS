package sdm.sccpms;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ChildTest {
	static final String NAME = "Tom";
	static final String STREET = "Teststreet 5";
	
	@Before
	public void init() {
		
	}
	
	@Test
	public void testChildConstructors() {
		Child child = new Child(NAME, STREET);
		assertEquals(NAME, child.getName());
		assertEquals(STREET, child.getGoodness());
		assertEquals(Child.INITIAL_GOODNESS, child.getGoodness());

		float goodness = .7f;
		child = new Child(NAME, STREET, goodness);
		assertEquals(goodness, child.getGoodness());
		assertEquals(NAME, child.getName());
		assertEquals(STREET, child.getGoodness());
	}

	@Test
	public void testChildDoesGoodAndBad() {
		Child child = new Child(NAME, STREET);
		float goodness = Child.INITIAL_GOODNESS;
		
		goodness += Child.GOODNESS_CHANGE;
		child.doGood();
		
		assertEquals(goodness, child.getGoodness());
		
		goodness += .2f;
		child.doGood(.2f);
		
		assertEquals(goodness, child.getGoodness());
		
		goodness -= Child.GOODNESS_CHANGE;
		child.doBad();
		
		assertEquals(goodness, child.getGoodness());
		
		goodness -= .01f;
		child.doBad(.01f);
		assertEquals(goodness, child.getGoodness());
		
		child.doBad(2f);
		assertEquals(0f, child.getGoodness());

		child.doGood(2f);
		assertEquals(1f, child.getGoodness());
	}
}
