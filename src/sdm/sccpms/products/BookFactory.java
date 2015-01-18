package sdm.sccpms.products;

import sdm.sccpms.ProductFactoryInterface;
import sdm.sccpms.ProductInterface;

public class BookFactory implements ProductFactoryInterface {

	@Override
	public ProductInterface create() {
		System.out.println("Writing book.");
		return new Book();
	}

	@Override
	public String getCreatedProductId() {
		return Book.ID;
	}

}
