package sdm.sccpms.products;

import sdm.sccpms.ProductInterface;

public class Bicycle implements ProductInterface {
	public static final String ID = "bicycle";
	
	@Override
	public String getName() {
		return "Bycicle";
	}

}
