package sdm.sccpms.products;

import sdm.sccpms.ProductFactoryInterface;
import sdm.sccpms.ProductInterface;

public class BicycleFactory implements ProductFactoryInterface {

	@Override
	public ProductInterface create() {
		System.out.println("Assembling bicycle.");
		return new Bicycle();
	}

	@Override
	public String getCreatedProductId() {
		return Bicycle.ID;
	}

}
