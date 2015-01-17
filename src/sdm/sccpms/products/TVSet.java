package sdm.sccpms.products;

import sdm.sccpms.ProductInterface;

public class TVSet implements ProductInterface {
	public static final String ID = "tv-set";
	
	@Override
	public String getName() {
		return "TVSet";
	}

}
