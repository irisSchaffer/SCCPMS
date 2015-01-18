package sdm.sccpms.gift;

public class GiftWrap {
	public String color;
	public String pattern;
	
	public GiftWrap(String color, String pattern) {
		super();
		this.color = color;
		this.pattern = pattern;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String colour) {
		this.color = colour;
	}
	
	public String getPattern() {
		return pattern;
	}
	
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
}
