package pageObjects.nopcommerce.portal;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUI.nopcommerce.portal.UserProductCartPageUI;

public class UserProductCartPageObject extends BasePage {
	private WebDriver driver;
	
	public UserProductCartPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isProductDisplayedByProductName(String productName) {
		return isElementDisplayed(driver, UserProductCartPageUI.DYNAMIC_PRODUCT_LINK_BY_PRODUCT_NAME,productName);
	}
	
	public boolean isProductUndisplayedByProductName(String productName) {
		return isElementUndisplayed(driver, UserProductCartPageUI.DYNAMIC_PRODUCT_LINK_BY_PRODUCT_NAME,productName);
	}

	public String getProductDetailByProductName(String productName) {
		return getElementText(driver, UserProductCartPageUI.DYNAMIC_PRODUCT_DETAIL_BY_PRODUCT_NAME,productName);
	}

	public UserProductPageObject clickEditLinkByProductName(String productName) {
		waitForElementClickable(driver, UserProductCartPageUI.DYNAMIC_EDIT_LINK_BY_PRODUCT_NAME, productName);
		clickElement(driver, UserProductCartPageUI.DYNAMIC_EDIT_LINK_BY_PRODUCT_NAME, productName);
		return PageGeneratorManager.getPageGenerator().getUserProductPage(driver);
	}

	public void clickRemoveButtonByProductName(String productName) {
		waitForElementClickable(driver, UserProductCartPageUI.DYNAMIC_REMOVE_BUTTON_BY_PRODUCT_NAME, productName);
		clickElement(driver, UserProductCartPageUI.DYNAMIC_REMOVE_BUTTON_BY_PRODUCT_NAME, productName);
	}

	public String getNoDataText() {
		areJQueryAndJSLoadedSuccess(driver);
		return getElementText(driver, UserProductCartPageUI.NO_DATA_TEXT);
	}
	
	public void inputQtyByProductName(String productName, String value) {
		waitForElementVisible(driver, UserProductCartPageUI.DYNAMIC_QTY_BY_PRODUCT_NAME,productName);
		inputIntoElement(driver, UserProductCartPageUI.DYNAMIC_QTY_BY_PRODUCT_NAME, value, productName);
	}
	
	public String getProductInfoByHeaderAndProductName(String headerName,String productName) {
		int columnIndex = getElementSize(driver, UserProductCartPageUI.HEADER_NAME_INDEX, headerName)+1;
		waitForElementVisible(driver, UserProductCartPageUI.DYNAMIC_TABLE_ITEM_BY_PRODUCT_NAME,productName,String.valueOf(columnIndex));
		return getElementText(driver, UserProductCartPageUI.DYNAMIC_TABLE_ITEM_BY_PRODUCT_NAME,productName,String.valueOf(columnIndex));
	}

	public boolean isTotalPriceDisplayedCorrectlyByProductName(String productName,float totalPrice) {
		return convertStringToFloat(getProductInfoByHeaderAndProductName("Total",productName))==totalPrice;
	}

	public boolean isPriceDisplayedCorrectlyByProductName(String productName, float price) {
		return convertStringToFloat(getProductInfoByHeaderAndProductName("Price",productName)) == price;
	}

	public void clickUpdateShoppingCart() {
		clickButtonByText(driver, "Update shopping cart");
		areJQueryAndJSLoadedSuccess(driver);
	}

	public void selectGiftWrappingDropdown(String giftWrapping) {
		selectDropdownListByName(driver, "checkout_attribute_1", giftWrapping);		
	}

	public void clickEstimateShippingButton() {
		waitForElementClickable(driver, UserProductCartPageUI.ESTIMATE_SHIPPING_BUTTON);
		clickElement(driver, UserProductCartPageUI.ESTIMATE_SHIPPING_BUTTON);
		areJQueryAndJSLoadedSuccess(driver);
	}

	public void selectShipToCountryDropdown(String country) {
		selectDropdownListByName(driver, "CountryId", country);
	}

	public void selectShipToStateDropdown(String state) {
		selectDropdownListByName(driver, "StateProvinceId", state);
	}

	public void inputShipToZipCodeTextbox(String zipCode) {
		inputToTextboxByID(driver, "ZipPostalCode", zipCode);
	}

	public void clickShippingMethodRadioButtonByMethodName(String methodName) {
		areJQueryAndJSLoadedSuccess(driver);
		waitForElementClickable(driver,UserProductCartPageUI.DYNAMIC_SHIPPING_METHOD_RADIO_BUTTON_BY_METHOD_NAME,methodName);
		clickElement(driver,UserProductCartPageUI.DYNAMIC_SHIPPING_METHOD_RADIO_BUTTON_BY_METHOD_NAME,methodName);
	}

	public void clickApplyButton() {
		clickButtonByText(driver, "Apply");
	}

	public UserCheckoutPageObject clickCheckOutButton() {
		clickButtonByText(driver, "Checkout");
		return PageGeneratorManager.getPageGenerator().getUserCheckoutPage(driver);
	}

	public void checkTermOfServiceCheckbox() {
		checkCheckboxByID(driver, "termsofservice");
	}
}