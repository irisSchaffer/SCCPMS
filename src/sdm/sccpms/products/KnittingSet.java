package sdm.sccpms.products;

import sdm.sccpms.ProductInterface;

public class KnittingSet implements ProductInterface {
	public static final String ID = "knitting-set";
	
	@Override
	public String getName() {
		return "Knitting Set";
	}

}
