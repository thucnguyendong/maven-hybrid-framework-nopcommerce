package pageUI.nopcommerce.portal;

public class UserSearchPageUI {
	public static final String SEARCH_TEXTBOX = "//input[@id='q']";
	public static final String SEARCH_BUTTON = "//button[contains(@class,'search-button')]";
	public static final String PRODUCT_TITLE = "//h2[@class='product-title']";
	public static final String SEARCH_ERROR = "//div[@class='products-wrapper']/div[@class='warning']";
	public static final String SEARCH_NO_RESULT = "//div[@class='products-wrapper']/div[@class='no-result']";
	
	public static final String DYNAMIC_PRODUCT_LINK = "//h2[@class='product-title']//a[text()='%s']";
	public static final String CATEGORY_DROPDOWN = "//select[@id= 'cid']";
	public static final String ADVANCED_SEARCH_CHECKBOX = "//input[@id= 'advs']";
	public static final String SEARCH_SUB_CATEGORY_CHECKBOX = "//input[@id= 'isc']";
	public static final String MANUFACTURER_DROPDOWN = "//select[@id= 'mid']";

}
