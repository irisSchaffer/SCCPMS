package sdm.sccpms;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ChildRecordTest {
	ChildRecord childRecord;
	Child child;
	ChristmasRecord christmasRecord2009;
	List<String> wishList2009;
	
	ChristmasRecord currentChristmasRecord;
	List<String> currentWishList;
	
	@Before
	public void init() {
		this.child = new Child("Tom", "Street 15, 0000 City, Country");
		this.childRecord = new ChildRecord(child);
		
		this.wishList2009 = new LinkedList<String>();
		this.wishList2009.add("bicycle");
		this.christmasRecord2009 = new ChristmasRecord(this.wishList2009, child.getGoodness(), 2009);
		this.childRecord.addChristmasRecord(this.christmasRecord2009);

		this.currentWishList = new LinkedList<String>();
		this.currentWishList.add("tv-set");
		this.currentWishList.add("cat");
		this.currentChristmasRecord = new ChristmasRecord(this.currentWishList, child.getGoodness());
		this.childRecord.addChristmasRecord(this.currentChristmasRecord);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testAddChristmasRecordForSameYearTwice() {
		List<String> wishList = new LinkedList<String>();
		wishList.add("test-wish");
		ChristmasRecord christmasRecord = new ChristmasRecord(wishList, 2009);

		this.childRecord.addChristmasRecord(christmasRecord);
	}
	
	@Test
	public void testGetCurrentChristmasRecord() {
		assertEquals(this.currentChristmasRecord, this.childRecord.getCurrentChristmasRecord());
	}
	
	@Test
	public void testGetChristmasRecordForYear() {
		assertEquals(this.christmasRecord2009, this.childRecord.getChristmasRecordForYear(2009));
	}
	
	@Test
	public void testHasAndGetChristmasRecordForYearWithoutRecord() {
		assertFalse(this.childRecord.hasChristmasRecordForYear(2000));
		assertNull(this.childRecord.getChristmasRecordForYear(2000));
	}
}
