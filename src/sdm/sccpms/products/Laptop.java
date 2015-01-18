package sdm.sccpms.products;

import sdm.sccpms.Product;

public class Laptop extends Product {
	public static final String ID = "laptop";

	@Override
	public String getName() {
		return ID;
	}

}
