package pageUI.nopcommerce.admin;

public class AdminBasePageUI {
	public static final String DYNAMIC_SIDE_MENU_ITEM = "//ul[contains(@class,'nav-sidebar')]//p[normalize-space(text()) = '%s']//parent::a";
	public static final String DYNAMIC_SIDE_SUB_MENU_ITEM = "//ul[contains(@class,'nav-treeview')]//p[normalize-space(text()) = '%s']//parent::a";

}
