package pageUI.nopcommerce.portal;

public class UserProductPageUI {
	public static final String ADD_REVIEW_LINK= "//div[@class='product-review-links']/a[text()='Add your review']";
	public static final String SUCCESS_MESSAGE = "//div[@class='bar-notification success']";
	public static final String CLOSE_SUCCESS_BUTTON = "//div[@class='bar-notification success']/span[@class='close']";
	public static final String WISHLIST_LINK_SUCCESS_MESSAGE = "//div[@class='bar-notification success']//a[text()='wishlist']";
	public static final String PRODUCT_COMPARISON_LINK_SUCCESS_MESSAGE = "//div[@class='bar-notification success']//a[text()='product comparison']";
	public static final String PRODUCT_CART_LINK_SUCCESS_MESSAGE = "//div[@class='bar-notification success']//a[text()='shopping cart']";
	public static final String PROCESSOR_DROPDOWN = "//select[@id='product_attribute_1']";
	public static final String RAM_DROPDOWN = "//select[@id='product_attribute_2']";
	public static final String HDD_RADIO_BUTTON = "//label[normalize-space(text())='HDD']/..//following-sibling::dd//label[text()='%s']/../input";
	public static final String OS_RADIO_BUTTON = "//label[normalize-space(text())='OS']/..//following-sibling::dd//label[text()='%s']/../input";
	public static final String SOFTWARE_CHECKBOX = "//label[normalize-space(text())='Software']/..//following-sibling::dd//label[text()='%s']/../input";
	public static final String TOTAL_PRICE_TEXT = "//div[@class='product-price']/span";
}
