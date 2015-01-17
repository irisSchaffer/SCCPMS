package sdm.sccpms;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ChildRecord {
	private Child child;
	private Map<Integer, ChristmasRecord> christmasRecords;

	public ChildRecord(Child child) {
		this.child = child;
		this.christmasRecords = new HashMap<Integer, ChristmasRecord>();
	}

	public void addChristmasRecord(ChristmasRecord christmasRecord) {
		if (this.christmasRecords.containsKey(christmasRecord.getYear())) {
			throw new IllegalArgumentException(String.format("Child record already has a christmas record for year %d", christmasRecord.getYear()));
		}
		
		this.christmasRecords.put(christmasRecord.getYear(), christmasRecord);
	}

	public Object getCurrentChristmasRecord() {
		return this.christmasRecords.get(Calendar.getInstance().get(Calendar.YEAR));
	}

	public Object getChristmasRecordForYear(int year) {
		return this.christmasRecords.get(year);
	}
}
