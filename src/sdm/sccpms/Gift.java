package sdm.sccpms;

public class Gift {
	public GiftWrap giftWrap;
	public ProductInterface product;

	public Gift(ProductInterface product, GiftWrap giftWrap) {
		super();
		this.giftWrap = giftWrap;
		this.product = product;
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
}
