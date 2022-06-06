package pageUI.nopcommerce.portal;

public class UserProductCartPageUI {
	public static final String HEADER_NAME_INDEX = "//th[normalize-space(text())='%s']/preceding-sibling::th"; 
	public static final String DYNAMIC_TABLE_ITEM_BY_PRODUCT_NAME = "//table[@class='cart']//a[@class='product-name' and text() = '%s']//ancestor::tr/td[%s]";
	public static final String DYNAMIC_QTY_BY_PRODUCT_NAME = "//table[@class='cart']//a[@class='product-name' and text() = '%s']/ancestor::tr/td[@class='quantity']/input"; 
	public static final String DYNAMIC_REMOVE_BUTTON_BY_PRODUCT_NAME = "//table[@class='cart']//a[@class='product-name' and text() = '%s']/ancestor::tr/td[@class='remove-from-cart']/button";
	public static final String DYNAMIC_PRODUCT_LINK_BY_PRODUCT_NAME = "//table[@class='cart']//a[@class='product-name' and text()='%s']";
	public static final String DYNAMIC_PRODUCT_DETAIL_BY_PRODUCT_NAME = "//table[@class='cart']//a[@class='product-name' and text()='%s']//following-sibling::div[@class='attributes']";
	public static final String DYNAMIC_EDIT_LINK_BY_PRODUCT_NAME = "//table[@class='cart']//a[@class='product-name' and text()='%s']//following-sibling::div[@class='edit-item']/a";
	public static final String NO_DATA_TEXT = "//div[@class='no-data']";
	public static final String DYNAMIC_SHIPPING_METHOD_RADIO_BUTTON_BY_METHOD_NAME = "//div[@class='estimate-shipping-row-item shipping-item' and text()='%s']//preceding-sibling::div";
	public static final String ESTIMATE_SHIPPING_BUTTON = "//a[@id='open-estimate-shipping-popup']";
}
