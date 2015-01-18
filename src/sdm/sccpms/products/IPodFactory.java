package sdm.sccpms.products;

import sdm.sccpms.ProductFactoryInterface;
import sdm.sccpms.ProductInterface;

public class IPodFactory implements ProductFactoryInterface {

	@Override
	public ProductInterface create() {
		System.out.println("Loading music on iPod.");
		return new IPod();
	}

	@Override
	public String getCreatedProductId() {
		return IPod.ID;
	}

}
