package sdm.sccpms;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;



public class GiftGranterTest {
	ChildRecord childRecord;
	Child child;	
	
	GiftGranter giftGranter;
	
	@Before
	public void init() {
		this.child = new Child("Max", "Street 9, 1001 City, Country");		
		this.childRecord = new ChildRecord(child); 
		
		this.child.addToWishList("i-pod");		
		this.child.addToWishList("cat");		
		
		this.giftGranter = new GiftGranter(this.childRecord);
		this.child.addObserver(this.giftGranter);
		
		this.child.putWishListOnWindowSill();				
	}
	
	@Test
	public void testIsWishGranted() {
		assertTrue(this.giftGranter.isWishGranted());
	}	
}
