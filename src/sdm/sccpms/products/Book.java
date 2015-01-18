package sdm.sccpms.products;

import sdm.sccpms.Product;


public class Book extends Product {
	public static final String ID = "book";

	@Override
	public String getName() {
		return ID;
	}

}
