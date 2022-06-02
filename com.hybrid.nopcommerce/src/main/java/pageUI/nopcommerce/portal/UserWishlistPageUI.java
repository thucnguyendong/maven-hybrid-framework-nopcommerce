package pageUI.nopcommerce.portal;

public class UserWishlistPageUI {
	public static final String DYNAMIC_ITEM_IN_TABLE = "//table[@class='cart']//td[@class='%s']";
	public static final String DYNAMIC_PRODUCT_BUTTON_BY_PRODUCT_NAME = "//table[@class='cart']//a[@class='product-name' and text()='%s']";
	public static final String DYNAMIC_REMOVE_BUTTON_BY_PRODUCT_NAME = "//table[@class='cart']//a[@class='product-name' and text()='%s']/..//following-sibling::td[@class='remove-from-cart']/button[@class='remove-btn']";
	public static final String DYNAMIC_ADD_CART_CHECKBOX_BY_PRODUCT_NAME = "//table[@class='cart']//a[@class='product-name' and text()='%s']/..//preceding-sibling::td[@class='add-to-cart']/input[@name='addtocart']";
	public static final String ADD_TO_CART_BUTTON = "//button[@name='addtocartbutton']";
	public static final String EMPTY_WISHLIST_TEXT = "//div[@class='no-data' and normalize-space(text())='The wishlist is empty!']";
}
