package pageObjects.nopcommerce.portal;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import pageUI.nopcommerce.portal.UserRegisterPageUI;

public class UserRegisterPageObject extends BasePage {
	private WebDriver driver;
	
	public UserRegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void selectMaleGender() {
		waitForElementClickable(driver, UserRegisterPageUI.MALE_RADIO_BUTTON);
		clickElement(driver,UserRegisterPageUI.MALE_RADIO_BUTTON);
	}
	
	public void selectFemaleGender() {
		waitForElementClickable(driver, UserRegisterPageUI.FEMALE_RADIO_BUTTON);
		clickElement(driver,UserRegisterPageUI.FEMALE_RADIO_BUTTON);
	}
	
	public void inputFirstName(String firstName) {
		waitForElementVisible(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX);
		inputIntoElement(driver,UserRegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
	}
	
	public void inputLastName(String lastName) {
		waitForElementVisible(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX);
		inputIntoElement(driver,UserRegisterPageUI.LAST_NAME_TEXTBOX, lastName);
	}
	
	public void selectDay(String day) {
		waitForElementVisible(driver, UserRegisterPageUI.DAY_DROPDOWN);
		selectItemInDefaultDropdown(driver,UserRegisterPageUI.DAY_DROPDOWN, day);
	}
	
	public void selectMonth(String month) {
		waitForElementVisible(driver, UserRegisterPageUI.MONTH_DROPDOWN);
		selectItemInDefaultDropdown(driver,UserRegisterPageUI.MONTH_DROPDOWN, month);
	}
	
	public void selectYear(String year) {
		waitForElementVisible(driver, UserRegisterPageUI.YEAR_DROPDOWN);
		selectItemInDefaultDropdown(driver,UserRegisterPageUI.YEAR_DROPDOWN, year);
	}
	@Step("Click input invalid email format")
	public void inputEmail(String email) {
		waitForElementVisible(driver, UserRegisterPageUI.EMAIL_TEXTBOX);
		inputIntoElement(driver,UserRegisterPageUI.EMAIL_TEXTBOX, email);
	}
	
	public void inputCompany(String company) {
		waitForElementVisible(driver, UserRegisterPageUI.COMPANY_TEXTBOX);
		inputIntoElement(driver,UserRegisterPageUI.COMPANY_TEXTBOX, company);
	}
	
	public void selectNewsletter() {
		waitForElementClickable(driver, UserRegisterPageUI.NEWSLETTER_CHECKBOX);
		checkToDefaultCheckboxRadio(driver, UserRegisterPageUI.NEWSLETTER_CHECKBOX);
	}
	
	public void unSelectNewsletter() {
		waitForElementClickable(driver, UserRegisterPageUI.NEWSLETTER_CHECKBOX);
		uncheckToDefaultCheckboxRadio(driver, UserRegisterPageUI.NEWSLETTER_CHECKBOX);
	}
	
	public void inputPassword(String password) {
		waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_TEXTBOX);
		inputIntoElement(driver, UserRegisterPageUI.PASSWORD_TEXTBOX, password);
	}
	
	public void inputConfirmPassword(String password) {
		waitForElementVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		inputIntoElement(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, password);
	}
	@Step("Click Register Button")
	public UserHomePageObject clickRegisterButton() {
		waitForElementVisible(driver, UserRegisterPageUI.REGISTER_BUTTON);
		clickElement(driver, UserRegisterPageUI.REGISTER_BUTTON);
		return PageGeneratorManager.getPageGenerator().getUserHomePage(driver);
	}
	
	public String getFirstNameErrorMessage() {
		waitForElementVisible(driver, UserRegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
	}
	
	
	public String getLastNameErrorMessage() {
		waitForElementVisible(driver, UserRegisterPageUI.LAST_NAME_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.LAST_NAME_ERROR_MESSAGE);
	}
	@Step("Verify email field")
	public String getEmailErrorMessage() {
		waitForElementVisible(driver, UserRegisterPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.EMAIL_ERROR_MESSAGE);
	}
	
	public String getPasswordErrorMessage() {
		waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.PASSWORD_ERROR_MESSAGE);
	}
	
	public String getConfirmPasswordErrorMessage() {
		waitForElementVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
	}
	
	public String getExistingEmailErrorMessage() {
		waitForElementVisible(driver, UserRegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
		return getElementAttribute(driver, UserRegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE,"textContent");
	}
	
	public String getSuccessMessage() {
		waitForElementVisible(driver, UserRegisterPageUI.SUCCESS_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.SUCCESS_MESSAGE);
	}
	
	public UserHomePageObject clickLogOutLink() {
		waitForElementClickable(driver, UserRegisterPageUI.LOGOUT_LINK);
		clickElement(driver, UserRegisterPageUI.LOGOUT_LINK);
		return PageGeneratorManager.getPageGenerator().getUserHomePage(driver);
	}
}
