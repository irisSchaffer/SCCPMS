package sdm.sccpms.products;

import sdm.sccpms.Product;


public class Cat extends Product {
	public static final String ID = "cat";

	@Override
	public String getName() {
		return ID;
	}

}
