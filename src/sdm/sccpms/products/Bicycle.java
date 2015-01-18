package sdm.sccpms.products;

import sdm.sccpms.Product;

public class Bicycle extends Product {
	public static final String ID = "bicycle";

	@Override
	public String getName() {
		return ID;
	}
}
