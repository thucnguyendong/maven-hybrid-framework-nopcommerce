package commons;

import java.io.File;

public class GlobalConstants {
	public static final int SHORT_TIMEOUT=5;
	public static final int LONG_TIMEOUT=30;
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String UPLOAD_FOLDER_PATH = System.getProperty("user.dir")+ File.separator+"uploadFile"+ File.separator;
	public static final String DOWNLOAD_FOLDER_PATH = System.getProperty("user.dir")+ File.separator+"downloadFile"+ File.separator;
	public static final String NOPCOMMERCE_USER_PORTAL_PAGE_URL="https://demo.nopcommerce.com";
	public static final String NOPCOMMERCE_ADMIN_PAGE_URL="https://admin-demo.nopcommerce.com";
	public static final String WORDPRESS_LOGIN_PAGE_URL="http://localhost/automation-web/wp-login.php";
	public static final String BANK_GURU_PAGE_URL="https://demo.guru99.com/v4";
	public static final String TEST_DATA_PATH =  PROJECT_PATH+File.separator+"testdata"+File.separator;
	public static final String TEST_DATA_DRIVEN_PATH =  TEST_DATA_PATH+"com"+File.separator+"nopcommerce"+File.separator+"datadriven"+File.separator;
	public static final String JAVA_VERSION = "11.0.11";
	
	public static String nopcommerce_Email ="";
	public static String nopcommerce_Password ="";
}
