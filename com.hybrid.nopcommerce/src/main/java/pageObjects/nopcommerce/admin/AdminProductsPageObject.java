package pageObjects.nopcommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUI.nopcommerce.admin.AdminProductsPageUI;

public class AdminProductsPageObject extends BasePage {
	private WebDriver driver;
	
	public AdminProductsPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputProductName(String productName) {
		waitForElementVisible(driver, AdminProductsPageUI.PRODUCT_NAME_TEXTBOX);
		inputIntoElement(driver, AdminProductsPageUI.PRODUCT_NAME_TEXTBOX, productName);
	}

	public void clickSearchButton() {
		waitForElementClickable(driver, AdminProductsPageUI.SEARCH_BUTTON);
		clickElement(driver, AdminProductsPageUI.SEARCH_BUTTON);
	}

	public AdminProductDetailPageObject clickEditByValueOfColumn(String headerName, String value) {
		int columnIndex = getElementSize(driver, AdminProductsPageUI.HEADER_NAME_INDEX, headerName)+1;
		waitForElementVisible(driver, AdminProductsPageUI.DYNAMIC_EDIT_BUTTON_BY_VALUE,String.valueOf(columnIndex),value);
		clickElement(driver, AdminProductsPageUI.DYNAMIC_EDIT_BUTTON_BY_VALUE, String.valueOf(columnIndex),value);
		return PageGeneratorManager.getPageGenerator().getAdminProductDetailPage(driver);
	}

	public String getSuccessMessage() {
		waitForElementVisible(driver, AdminProductsPageUI.SUCCESS_MESSAGE);
		return getElementText(driver, AdminProductsPageUI.SUCCESS_MESSAGE);
	}
}
