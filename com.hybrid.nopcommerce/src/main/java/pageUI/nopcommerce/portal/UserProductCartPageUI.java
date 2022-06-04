package pageUI.nopcommerce.portal;

public class UserProductCartPageUI {
	public static final String DYNAMIC_PRODUCT_LINK_BY_PRODUCT_NAME = "//table[@class='cart']//a[@class='product-name' and text()='%s']";
	public static final String DYNAMIC_PRODUCT_DETAIL_BY_PRODUCT_NAME = "//table[@class='cart']//a[@class='product-name' and text()='%s']//following-sibling::div[@class='attributes']";
}
