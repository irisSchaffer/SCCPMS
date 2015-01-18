package sdm.sccpms.gift;

import sdm.sccpms.Child;
import sdm.sccpms.ProductInterface;

public class Gift {
	private GiftWrap giftWrap;
	private ProductInterface product;
	private Child child;

	public Gift(ProductInterface product, Child child, GiftWrap giftWrap) {
		super();
		this.product = product;
		this.child = child;
		this.giftWrap = giftWrap;
	}

	public GiftWrap getGiftWrap() {
		return giftWrap;
	}

	public void setGiftWrap(GiftWrap giftWrap) {
		this.giftWrap = giftWrap;
	}

	public ProductInterface getProduct() {
		return product;
	}

	public void setProduct(ProductInterface product) {
		this.product = product;
	}

	public Child getChild() {
		return this.child;
	}
	
	public void setGiftTag(Child child) {
		this.child = child;
	}

	@Override
	public String toString() {
		return String.format("%s wrapped in %s.", this.getProduct(), this.getGiftWrap());
	}
	
}
