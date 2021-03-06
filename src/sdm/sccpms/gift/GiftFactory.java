package sdm.sccpms.gift;

import java.util.List;
import java.util.Map;

import sdm.sccpms.Child;
import sdm.sccpms.ProductFactoryInterface;
import sdm.sccpms.ProductInterface;
import sdm.sccpms.exceptions.UndefinedProductException;

public class GiftFactory {
	
	Map<String, ProductFactoryInterface> productFactories;
	List<GiftWrap> giftWraps;

	public GiftFactory(Map<String, ProductFactoryInterface> productFactories,
			List<GiftWrap> giftWraps) {
		this.productFactories = productFactories;
		this.giftWraps = giftWraps;
	}

	public Gift createGift(String productId, Child child) {
		if (! this.productFactories.containsKey(productId)) {
			throw new UndefinedProductException(
				String.format("No Product Factory is registered for the product id %s", productId)
			);
		}
		
		ProductInterface product = this.productFactories.get(productId).create();
		
		return new Gift(product, child, this.getGiftWrap());
	}

	private GiftWrap getGiftWrap() {
		int index = (int) Math.round(Math.random() * (this.giftWraps.size() - 1));
		return this.giftWraps.get(index);
	}

}
