package sdm.sccpms.products;

import sdm.sccpms.ProductInterface;

public class Cat implements ProductInterface {
	public static final String ID = "cat";
	
	@Override
	public String getName() {
		return "Cat";
	}

}
