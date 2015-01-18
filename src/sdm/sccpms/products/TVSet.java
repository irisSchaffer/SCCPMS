package sdm.sccpms.products;

import sdm.sccpms.Product;

public class TVSet extends Product {
	public static final String ID = "tv-set";

	@Override
	public String getName() {
		return ID;
	}
	
	@Override
	public String getDisplayName() {
		return "TV Set";
	}
}
