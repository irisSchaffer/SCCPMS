package sdm.sccpms;

import java.util.Calendar;
import java.util.List;

public class ChristmasRecord {
	private List<String> wishList;
	private int year;
	private float goodness;

	public ChristmasRecord(List<String> wishList, float goodness) {
		this(wishList, goodness, Calendar.getInstance().get(Calendar.YEAR));
	}

	public ChristmasRecord(List<String> wishList, float goodness, int year) {
		if (wishList.size() < 1) {
			throw new IllegalArgumentException("Can only add christmas records with wish lists of at least one wish.");
		}
		
		this.wishList = wishList;
		this.year = year;
		this.goodness = goodness;
	}

	public List<String> getWishList() {
		return wishList;
	}

	public int getYear() {
		return year;
	}

	public float getGoodness() {
		return goodness;
	}

	public void setGoodness(float goodness) {
		this.goodness = goodness;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((wishList == null) ? 0 : wishList.hashCode());
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChristmasRecord other = (ChristmasRecord) obj;
		if (wishList == null) {
			if (other.wishList != null)
				return false;
		} else if (!wishList.equals(other.wishList))
			return false;
		if (year != other.year)
			return false;
		return true;
	}
}
