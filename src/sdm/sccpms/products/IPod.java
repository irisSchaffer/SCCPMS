package sdm.sccpms.products;

import sdm.sccpms.ProductInterface;

public class IPod implements ProductInterface {
	public static final String ID = "i-pod";
	
	@Override
	public String getName() {
		return "iPod";
	}

}
