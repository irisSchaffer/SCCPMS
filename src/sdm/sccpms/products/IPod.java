package sdm.sccpms.products;

import sdm.sccpms.Product;

public class IPod extends Product {
	public static final String ID = "i-pod";

	@Override
	public String getName() {
		return ID;
	}
	
	@Override
	public String getDisplayName() {
		return "iPod";
	}

}
