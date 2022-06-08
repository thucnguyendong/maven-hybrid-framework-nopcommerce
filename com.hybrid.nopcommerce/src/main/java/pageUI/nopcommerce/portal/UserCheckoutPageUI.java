package pageUI.nopcommerce.portal;

public class UserCheckoutPageUI {
	public static final String BILLING_CONTINUE_BUTTON = "//div[@id='billing-buttons-container']//button[@class='button-1 new-address-next-step-button']";
	public static final String SHIPPING_METHOD_RADIO_BUTTON_BY_METHOD_NAME = "//div[@class='method-name']/label[starts-with(text(),'%s')]";
	public static final String SHIPPING_METHOD_CONTINUE_BUTTON = "//button[@class='button-1 shipping-method-next-step-button']";
	public static final String PAYMENT_METHOD_RADIO_BUTTON_BY_METHOD_NAME = "//div[@class='payment-details']//label[text()='%s']";
	public static final String PAYMENT_METHOD_CONTINUE_BUTTON = "//button[@class='button-1 payment-method-next-step-button']";
	public static final String PAYMENT_INFO_CONTINUE_BUTTON = "//button[@class='button-1 payment-info-next-step-button']";
	public static final String BILLING_DETAIL_TEXT = "//div[@class='billing-info']/ul[@class='info-list']";
	public static final String SUCCESS_ORDER_TEXT = "//strong[text()='Your order has been successfully processed!']";
	public static final String ORDER_NUMBER_TEXT = "//strong[starts-with(text(),'Order number:')]";
	public static final String PAYMENT_INFO_FOR_CHECK_TEXT = "//p[text()=\"Mail Personal or Business Check, Cashier's Check or money order to:\"]";
	public static final String PAYMENT_METHOD_TEXT = "//li[@class='payment-method']";
	public static final String HEADER_NAME_INDEX = "//th[normalize-space(text())='%s']/preceding-sibling::th"; 
	public static final String DYNAMIC_TABLE_ITEM_BY_PRODUCT_NAME = "//table[@class='cart']//a[@class='product-name' and text() = '%s']//ancestor::tr/td[%s]";
	
}
