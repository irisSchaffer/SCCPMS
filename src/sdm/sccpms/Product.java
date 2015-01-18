package sdm.sccpms;

import com.sun.xml.internal.ws.util.StringUtils;

public abstract class Product implements ProductInterface{

	public Product() {
		super();
	}
	
	public String getDisplayName() {
		String displayName = this.getName().replace('-', ' ');
		StringUtils.capitalize(displayName);
		
		return displayName;
	}
	
	public String toString() {
		return this.getDisplayName();
	}

}