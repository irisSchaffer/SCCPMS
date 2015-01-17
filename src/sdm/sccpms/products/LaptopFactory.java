package sdm.sccpms.products;

import sdm.sccpms.ProductFactoryInterface;
import sdm.sccpms.ProductInterface;

public class LaptopFactory implements ProductFactoryInterface {

	@Override
	public ProductInterface create() {
		System.out.println("Creating laptop.");
		return new Laptop();
	}

	@Override
	public String getCreatedProductId() {
		return Laptop.ID;
	}

}
