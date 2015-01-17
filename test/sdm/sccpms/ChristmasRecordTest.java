package sdm.sccpms;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class ChristmasRecordTest {

	ChristmasRecord record;
	
	@Test
	public void testConstructors() {
		List<String> wishList = this.getWishList();
		this.record = new ChristmasRecord(wishList);
		assertEquals(wishList, this.record.getWishList());
		assertEquals(Calendar.getInstance().get(Calendar.YEAR), this.record.getYear());
		
		this.record = new ChristmasRecord(wishList, 2009);
		assertEquals(wishList, this.record.getWishList());
		assertEquals(2009, this.record.getYear());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructionWithEmptyWishList() {
		this.record = new ChristmasRecord(new LinkedList<String>());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetEmptyWishList() {
		this.record = new ChristmasRecord(this.getWishList());
		this.record.setWishList(new LinkedList<String>());
	}
	
	private List<String> getWishList() {
		List<String> wishList = new LinkedList<String>();
		wishList.add("wish");
		
		return wishList;
	}
}
