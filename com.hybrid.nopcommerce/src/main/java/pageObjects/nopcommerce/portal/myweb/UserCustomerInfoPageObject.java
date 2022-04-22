package pageObjects.nopcommerce.portal.myweb;

import org.openqa.selenium.WebDriver;
import commons.BasePage;
import pageUI.nopcommerce.portal.UserCustomerInfoPageUI;

public class UserCustomerInfoPageObject extends BasePage {
	private WebDriver driver;
	public UserCustomerInfoPageObject (WebDriver driver) {
		this.driver = driver;
	}
	
	public void selectMaleGender() {
		waitForElementClickable(driver, UserCustomerInfoPageUI.MALE_RADIO_BUTTON);
		clickElement(driver,UserCustomerInfoPageUI.MALE_RADIO_BUTTON);
	}
	
	public void selectFemaleGender() {
		waitForElementClickable(driver, UserCustomerInfoPageUI.FEMALE_RADIO_BUTTON);
		clickElement(driver,UserCustomerInfoPageUI.FEMALE_RADIO_BUTTON);
	}
	
	public void inputFirstName(String firstName) {
		waitForElementVisible(driver, UserCustomerInfoPageUI.FIRSTNAME_TEXTBOX);
		inputIntoElement(driver,UserCustomerInfoPageUI.FIRSTNAME_TEXTBOX, firstName);
	}
	
	public void inputLastName(String lastName) {
		waitForElementVisible(driver, UserCustomerInfoPageUI.LASTNAME_TEXTBOX);
		inputIntoElement(driver,UserCustomerInfoPageUI.LASTNAME_TEXTBOX, lastName);
	}
	
	public void selectDay(String day) {
		waitForElementVisible(driver, UserCustomerInfoPageUI.DAY_DROPDOWN);
		selectItemInDefaultDropdown(driver,UserCustomerInfoPageUI.DAY_DROPDOWN, day);
	}
	
	public void selectMonth(String month) {
		waitForElementVisible(driver, UserCustomerInfoPageUI.MONTH_DROPDOWN);
		selectItemInDefaultDropdown(driver,UserCustomerInfoPageUI.MONTH_DROPDOWN, month);
	}
	
	public void selectYear(String year) {
		waitForElementVisible(driver, UserCustomerInfoPageUI.YEAR_DROPDOWN);
		selectItemInDefaultDropdown(driver,UserCustomerInfoPageUI.YEAR_DROPDOWN, year);
	}
	
	public void inputEmail(String email) {
		waitForElementVisible(driver, UserCustomerInfoPageUI.EMAIL_TEXTBOX);
		inputIntoElement(driver,UserCustomerInfoPageUI.EMAIL_TEXTBOX, email);
	}
	
	public void inputCompany(String company) {
		waitForElementVisible(driver, UserCustomerInfoPageUI.COMPANY_TEXTBOX);
		inputIntoElement(driver,UserCustomerInfoPageUI.COMPANY_TEXTBOX, company);
	}
	
	public void selectNewsletter() {
		waitForElementClickable(driver, UserCustomerInfoPageUI.NEWSLETTER_CHECKBOX);
		checkToDefaultCheckboxRadio(driver, UserCustomerInfoPageUI.NEWSLETTER_CHECKBOX);
	}
	
	public void clickSave() {
		waitForElementClickable(driver, UserCustomerInfoPageUI.SAVE_BUTTON);
		clickElement(driver, UserCustomerInfoPageUI.SAVE_BUTTON);
	}
	
    public boolean isFemaleSelected()
    {
        waitForElementVisible(driver, UserCustomerInfoPageUI.FEMALE_RADIO_BUTTON);
        return isElementSelected(driver, UserCustomerInfoPageUI.FEMALE_RADIO_BUTTON);
    }
    
    public boolean isMaleSelected()
    {
        waitForElementVisible(driver, UserCustomerInfoPageUI.MALE_RADIO_BUTTON);
        return isElementSelected(driver, UserCustomerInfoPageUI.MALE_RADIO_BUTTON);
    }
	
    public String getFirstNameTextboxValue()
    {
        waitForElementVisible(driver, UserCustomerInfoPageUI.FIRSTNAME_TEXTBOX);
        return getElementAttribute(driver, UserCustomerInfoPageUI.FIRSTNAME_TEXTBOX,"value");
    }

    public String getLastNameTextboxValue()
    {
    	waitForElementVisible(driver, UserCustomerInfoPageUI.LASTNAME_TEXTBOX);
        return getElementAttribute(driver, UserCustomerInfoPageUI.LASTNAME_TEXTBOX,"value");
    }

    public String getEmailTextboxValue()
    {
    	waitForElementVisible(driver, UserCustomerInfoPageUI.EMAIL_TEXTBOX);
        return getElementAttribute(driver, UserCustomerInfoPageUI.EMAIL_TEXTBOX,"value");
    }
    
    public String getCompanyTextboxValue()
    {
    	waitForElementVisible(driver, UserCustomerInfoPageUI.COMPANY_TEXTBOX);
        return getElementAttribute(driver, UserCustomerInfoPageUI.COMPANY_TEXTBOX,"value");
    }
    
    public String getSelectedDayValue()
    {
    	waitForElementVisible(driver, UserCustomerInfoPageUI.DAY_DROPDOWN);
        return getItemInDefaultDropdown(driver, UserCustomerInfoPageUI.DAY_DROPDOWN);
    }
    
    public String getSelectedMonthValue()
    {
    	waitForElementVisible(driver, UserCustomerInfoPageUI.MONTH_DROPDOWN);
        return getItemInDefaultDropdown(driver, UserCustomerInfoPageUI.MONTH_DROPDOWN);
    }
    
    public String getSelectedYearValue()
    {
    	waitForElementVisible(driver, UserCustomerInfoPageUI.YEAR_DROPDOWN);
        return getItemInDefaultDropdown(driver, UserCustomerInfoPageUI.YEAR_DROPDOWN);
    }
    
    public String getMyAccountPageTitle() {
    	waitForElementVisible(driver, UserCustomerInfoPageUI.MY_ACCOUNT_TITLE);
        return getElementText(driver, UserCustomerInfoPageUI.MY_ACCOUNT_TITLE);
    }
}
