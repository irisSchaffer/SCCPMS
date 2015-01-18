package sdm.sccpms;

import java.util.Calendar;
import java.util.List;

public class ChristmasRecord {
	private List<String> wishList;
	private int year;

	public ChristmasRecord(List<String> wishList) {
		this(wishList, Calendar.getInstance().get(Calendar.YEAR));
	}

	public ChristmasRecord(List<String> wishList, int year) {
		this.year = year;
		this.setWishList(wishList);
	}

	public List<String> getWishList() {
		return wishList;
	}

	public void setWishList(List<String> wishList) {
		if (wishList.size() < 1) {
			throw new IllegalArgumentException("Can only add christmas records with wish lists of at least one wish.");
		}
		
		this.wishList = wishList;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}
