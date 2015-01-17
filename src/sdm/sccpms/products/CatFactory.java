package sdm.sccpms.products;

import sdm.sccpms.ProductFactoryInterface;
import sdm.sccpms.ProductInterface;

public class CatFactory implements ProductFactoryInterface {

	@Override
	public ProductInterface create() {
		System.out.println("Creating Cat.");
		return new Cat();
	}

	@Override
	public String getCreatedProductId() {
		return Cat.ID;
	}

}
