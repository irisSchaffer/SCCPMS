package sdm.sccpms.products;

import sdm.sccpms.ProductFactoryInterface;
import sdm.sccpms.ProductInterface;

public class TVSetFactory implements ProductFactoryInterface {

	@Override
	public ProductInterface create() {
		System.out.println("Creating TV set.");
		return new TVSet();
	}

	@Override
	public String getCreatedProductId() {
		return TVSet.ID;
	}

}
