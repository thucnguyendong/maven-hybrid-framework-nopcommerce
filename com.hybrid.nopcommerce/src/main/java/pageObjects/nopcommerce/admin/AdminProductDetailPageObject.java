package pageObjects.nopcommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopcommerce.admin.AdminProductDetailPageUI;

public class AdminProductDetailPageObject extends BasePage {
	private WebDriver driver;
	
	public AdminProductDetailPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToExpandPanel(String panel) {
		waitForElementClickable(driver, AdminProductDetailPageUI.DYNAMIC_PANEL_HEADER,panel);
		String toogleStatus = getElementAttribute(driver, AdminProductDetailPageUI.DYNAMIC_PANEL_HEADER, "class",panel);
		if(toogleStatus.contains("collapsed-card")) {
			clickElement(driver, AdminProductDetailPageUI.DYNAMIC_PANEL_HEADER,panel);
		}
	}

	public void clickToAddProductPictureButton(String...fileName) {
		inputIntoElement(driver, AdminProductDetailPageUI.UPLOAD_FILE_BUTTON,getMultipleFileNames(fileName));
	}

	public void addProductPicture() {
		waitForElementClickable(driver, AdminProductDetailPageUI.ADD_PRODUCT_PICTURE_BUTTON);
		clickElement(driver, AdminProductDetailPageUI.ADD_PRODUCT_PICTURE_BUTTON);
		sleepInSecond(1);
	}

	public void inputAltTextbox(String picture) {
		waitForElementVisible(driver, AdminProductDetailPageUI.PICTURE_ALT_TEXTBOX);
		inputIntoElement(driver, AdminProductDetailPageUI.PICTURE_ALT_TEXTBOX,picture);
	}

	public void inputTitleTextbox(String picture) {
		waitForElementVisible(driver, AdminProductDetailPageUI.PICTURE_TITLE_TEXTBOX);
		inputIntoElement(driver, AdminProductDetailPageUI.PICTURE_TITLE_TEXTBOX,picture);
	}

	public boolean isPictureDisplayed(String order, String alt, String title) {
		waitForElementVisible(driver, AdminProductDetailPageUI.DYNAMIC_PICTURE_BY_ORDER_ALT_TITLE,order,alt,title);
		return isElementDisplayed(driver, AdminProductDetailPageUI.DYNAMIC_PICTURE_BY_ORDER_ALT_TITLE,order,alt,title);
	}
	
	public boolean isPictureRemoved(String order, String alt, String title) {
		return waitForStaleness(driver, AdminProductDetailPageUI.DYNAMIC_PICTURE_BY_ORDER_ALT_TITLE,order,alt,title);
	}

	public void clickSaveButton() {
		waitForElementClickable(driver, AdminProductDetailPageUI.SAVE_BUTTON);
		clickElement(driver, AdminProductDetailPageUI.SAVE_BUTTON);
	}

	public void deletePictureByTitle(String picture) {
		waitForElementClickable(driver, AdminProductDetailPageUI.DYNAMIC_BUTTON_BY_TITLE,picture,"Delete");
		clickElement(driver, AdminProductDetailPageUI.DYNAMIC_BUTTON_BY_TITLE,picture,"Delete");
		acceptAlert(driver);
		sleepInSecond(1);
	}
}