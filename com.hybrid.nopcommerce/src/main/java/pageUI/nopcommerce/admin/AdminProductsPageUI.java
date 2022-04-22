package pageUI.nopcommerce.admin;

public class AdminProductsPageUI {
	public static final String PRODUCT_NAME_TEXTBOX = "//input[@id='SearchProductName']";
	public static final String SEARCH_BUTTON = "//button[@id='search-products']";
	public static final String HEADER_NAME_INDEX = "//th[text()='%s']/preceding-sibling::th"; 
	public static final String DYNAMIC_EDIT_BUTTON_BY_VALUE = "//td[%s][text()='%s']//following-sibling::td[@class=' button-column']//a[text()='Edit']";
	public static final String SUCCESS_MESSAGE = "//div[contains(@class,'alert-success')]"; 
}
