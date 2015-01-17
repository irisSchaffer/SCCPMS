package sdm.sccpms;

public class Child {
	public static final float INITIAL_GOODNESS = .5f;
	public static final float GOODNESS_CHANGE = .1f;
	
	private String name;
	private String address;
	private float goodness;
	
	public Child(String name, String address) {
		this(name, address, Child.INITIAL_GOODNESS);
	}
	
	public Child(String name, String address, float goodness) {
		this.name = name;
		this.address = address;
		this.goodness = goodness;
	}
	
	public void doGood(float good) {
		this.setGoodness(this.goodness + good);
	}
	
	public void doGood() {
		this.doGood(Child.GOODNESS_CHANGE);
	}
	
	public void doBad(float bad) {
		this.setGoodness(this.goodness - bad);
	}
	
	public void doBad() {
		this.doBad(Child.GOODNESS_CHANGE);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public float getGoodness() {
		return goodness;
	}

	public void setGoodness(float goodness) {
		if (goodness > 1f) {
			this.goodness = 1f;
		} else if (goodness < 0f) {
			this.goodness = 0f;
		} else {
			this.goodness = goodness;			
		}
	}
}
