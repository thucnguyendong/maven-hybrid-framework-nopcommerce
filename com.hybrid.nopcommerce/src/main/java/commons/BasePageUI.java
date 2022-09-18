package commons;

public class BasePageUI {
	public static final String DYNAMIC_TEXTBOX_BY_ID = "//input[@id='%s']";
	public static final String DYNAMIC_TEXTBOX_BY_NAME = "//input[@name='%s']";
	public static final String DYNAMIC_DROPDOWN_LIST_BY_NAME = "//select[@name='%s']";
	public static final String DYNAMIC_BUTTON_BY_TEXT = "//button[normalize-space(text())='%s']";
	public static final String DYNAMIC_CHECKBOX_BY_ID = "//input[@id='%s' and @type='checkbox']";
}
