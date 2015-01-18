package sdm.sccpms.products;

import sdm.sccpms.ProductFactoryInterface;
import sdm.sccpms.ProductInterface;

public class TVSetFactory implements ProductFactoryInterface {

	@Override
	public ProductInterface create() {
		System.out.println("12-year-old chinese elf is assembling tv set.");
		return new TVSet();
	}

	@Override
	public String getCreatedProductId() {
		return TVSet.ID;
	}

}
