package sdm.sccpms;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import sdm.sccpms.child.Child;

public class ChildRecord {
	private Child child;
	private Map<Integer, ChristmasRecord> christmasRecords;

	public ChildRecord(Child child) {
		this.child = child;
		this.christmasRecords = new HashMap<Integer, ChristmasRecord>();
	}

	public Child getChild() {
		return child;
	}

	public void setChild(Child child) {
		this.child = child;
	}

	public void addChristmasRecord(ChristmasRecord christmasRecord) {
		if (this.christmasRecords.containsKey(christmasRecord.getYear())) {
			throw new IllegalArgumentException(String.format("Child record already has a christmas record for year %d", christmasRecord.getYear()));
		}
		
		this.christmasRecords.put(christmasRecord.getYear(), christmasRecord);
	}

	public ChristmasRecord getCurrentChristmasRecord() {
		return this.getChristmasRecordForYear(Calendar.getInstance().get(Calendar.YEAR));
	}

	public ChristmasRecord getChristmasRecordForYear(int year) {
		return this.christmasRecords.get(year);
	}
	
	public boolean hasChristmasRecordForYear(int year) {
		return this.christmasRecords.containsKey(year);
	}
}
