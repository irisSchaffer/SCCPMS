package sdm.sccpms.products;

import sdm.sccpms.ProductFactoryInterface;
import sdm.sccpms.ProductInterface;

public class IPodFactory implements ProductFactoryInterface {

	@Override
	public ProductInterface create() {
		System.out.println("Creating iPod.");
		return new IPod();
	}

	@Override
	public String getCreatedProductId() {
		return IPod.ID;
	}

}
