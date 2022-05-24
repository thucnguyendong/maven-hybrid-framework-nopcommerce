package pageUI.nopcommerce.portal;

public class UserProductCatgoryPageUI {
	public static final String DYNAMIC_CATEGORY_PRODUCT = "//div[@class='sub-category-item']/h2[@class='title']/a[normalize-space(text())='%s']";
	public static final String PRODUCT_CATEGORY_LABEL = "//div[@class='page category-page']/div[@class='page-title']/h1";
	public static final String PRODUCT_TITLE_LABEL = "//h2[@class='product-title']/a";
	public static final String PRODUCT_PRICE_LABEL = "//div[@class='prices']/span";
	public static final String PRODUCT_ORDER_DROPDOWN = "//select[@id='products-orderby']";
	public static final String PRODUCT_PAGE_SIZE_DROPDOWN = "//select[@id='products-pagesize']";
	public static final String PRODUCT_PAGERS = "//div[@class='pager']/ul/li";
	public static final String PRODUCT_NEXT_PAGE_BUTTON = "//div[@class='pager']//li[@class='next-page']";
	public static final String PRODUCT_PREVIOUS_PAGE_BUTTON = "//div[@class='pager']//li[@class='previous-page']";
	public static final String PRODUCT_CURRENT_PAGE_POS = "//div[@class='pager']//li[@class='current-page']//preceding-sibling::li";
}
