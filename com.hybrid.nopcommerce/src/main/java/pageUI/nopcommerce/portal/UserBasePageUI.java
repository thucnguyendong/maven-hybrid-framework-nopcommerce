package pageUI.nopcommerce.portal;

public class UserBasePageUI {
	/** Header */
	public static final String REGISTER_LINK = "//div[@class='header-links']//a[text()='Register']";
	public static final String LOGOUT_LINK_AT_USER = "//a[text()='Log out']";
	public static final String LOGOUT_LINK_AT_ADMIN = "//a[text()='Logout']";
	public static final String LOGIN_LINK = "//div[@class='header-links']//a[text()='Log in']";
	public static final String MY_ACCOUNT_LINK = "//div[@class='header-links']//a[text()='My account']";

	
	/** Myweb menu list */
	public static final String ADDRESS_LINK = "//ul[@class='list']//a[text()='Addresses']";
	public static final String CHANGE_PASSWORD_LINK = "//ul[@class='list']//a[text()='Change password']";
	public static final String CUSTOMER_INFO_LINK = "//ul[@class='list']//a[text()='Customer info']";
	public static final String MY_PRODUCT_REVIEW_LINK = "//ul[@class='list']//a[text()='My product reviews']";
	public static final String REWARD_POINT_LINK = "//ul[@class='list']//a[text()='Reward points']";
	public static final String ORDER_LINK = "//ul[@class='list']//a[text()='Orders']";
	public static final String DOWNLOADABLE_PRODUCTS_LINK = "//ul[@class='list']//a[text()='Downloadable products']";
	public static final String BACK_IN_STOCK_SUBSCRIPTION_LINK = "//ul[@class='list']//a[text()='Back in stock subscriptions']";
	
	/** My Dynamic page list */
	public static final String DYNAMIC_MYWEB_MENU = "//ul[@class='list']//a[normalize-space(text())='%s']";
	public static final String DYNAMIC_PAGE_FOOTER = "//div[@class='footer']//a[normalize-space(text())='%s']";
	public static final String DYNAMIC_MENU_HEADER = "//div[@class='header-menu']/ul[contains(@class,'notmobile')]/li/a[normalize-space(text())='%s']";
	public static final String DYNAMIC_USER_MENU_LINK = "//div[@class='header-links']//li//span[starts-with(text(),'Wishlist')]/parent::a";
}
