package sdm.sccpms.products;

import sdm.sccpms.ProductInterface;

public class Laptop implements ProductInterface {
	public static final String ID = "laptop";
	
	@Override
	public String getName() {
		return "Laptop";
	}

}
