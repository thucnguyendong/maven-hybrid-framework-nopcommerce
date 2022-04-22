package pageUI.nopcommerce.admin;

public class AdminProductDetailPageUI {
	public static final String DYNAMIC_PANEL_HEADER = "//div[@class='card-title' and text()='%s']//ancestor::div[contains(@class,'card-outline')]";
	public static final String UPLOAD_FILE_BUTTON = "//div[@id='product-pictures']//input[@name='qqfile']";
	public static final String ADD_PRODUCT_PICTURE_BUTTON = "//button[@id='addProductPicture']";
	public static final String PICTURE_ALT_TEXTBOX = "//input[@id='AddPictureModel_OverrideAltAttribute']";
	public static final String PICTURE_TITLE_TEXTBOX = "//input[@id='AddPictureModel_OverrideTitleAttribute']";
	public static final String PRODUCT_NEW_UPLOAD_PICTURE_BY_FILE_NAME = "//input[@id='AddPictureModel_OverrideTitleAttribute']";
	public static final String DYNAMIC_PICTURE_BY_ORDER_ALT_TITLE =
			"//td[@data-columnname='DisplayOrder' and text()='%s']//following-sibling::td[@data-columnname='OverrideAltAttribute' and text()='%s']//following-sibling::td[@data-columnname='OverrideTitleAttribute' and text()='%s']";
	public static final String SAVE_BUTTON = "//button[@name='save']";
	public static final String DYNAMIC_BUTTON_BY_TITLE =
			"//td[@data-columnname='OverrideTitleAttribute' and text()='%s']//following-sibling::td[normalize-space(@class)='button-column']/a[text()='%s']";
}
