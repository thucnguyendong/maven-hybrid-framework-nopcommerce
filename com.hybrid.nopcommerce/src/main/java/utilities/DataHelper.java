package utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Faker;

public class DataHelper {
	
	private Locale locale = new Locale("en");
	private Faker faker = new Faker(locale);
	
	public static DataHelper getData() {
		return new DataHelper();
	}
	public int getRandomNumber(int minimum, int maximum) {
		Random rand = new Random();
		return minimum + rand.nextInt(maximum - minimum);
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999);
	}
	
	public String getDateToString() {
		Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");  
		return dateFormat.format(date);
	}
	
	public String getRandomEmail(String prefix, String domain) {
		return prefix+getDateToString()+"@"+domain+".com";
	}
	
	public String getFirstname() {
		return faker.address().firstName();
	}
	
	public String getLastname() {
		return faker.address().lastName();
	}
	
	public String getFullname() {
		return getFirstname() + " " + getLastname();
	}
	
	public String getEmailAddress() {
		return faker.internet().emailAddress();
	}
	
	public String getAddress() {
		return faker.address().fullAddress();
	}
	
	public String getUsername() {
		return faker.name().username();
	}
	
	public String getPassword() {
		return faker.internet().password();
	}
	
	public String getNationality() {
		return faker.nation().capitalCity();
	}
	
	public String getCardNumber() {
		return faker.finance().creditCard();
	}
}
