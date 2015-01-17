package sdm.sccpms.products;

import sdm.sccpms.ProductFactoryInterface;
import sdm.sccpms.ProductInterface;

public class KnittingSetFactory implements ProductFactoryInterface {

	@Override
	public ProductInterface create() {
		System.out.println("Creating Knitting Set");
		return new KnittingSet();
	}

	@Override
	public String getCreatedProductId() {
		return KnittingSet.ID;
	}

}
