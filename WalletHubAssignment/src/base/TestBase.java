package base;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {
	
	public static WebDriver driver;
	private static FileInputStream file;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static WebDriverWait wait;
	public static Actions act;
	public static Select select;
	
	public static void setUp() {
		
		System.setProperty("webdriver.chrome.driver", ".\\src\\executables\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		try {
			file = new FileInputStream(".\\src\\properties\\config.properties");
			config.load(file);
		}catch(Exception e) {
			System.out.println("Configuration File does not exist!");
		}
		
		try {
			file = new FileInputStream(".\\src\\properties\\OR.properties");
			OR.load(file);
			file.close();
		}catch(Exception e) {
			System.out.println("Object Repository does not exist!");
		}
		
		wait = new WebDriverWait(driver, 10);
		act = new Actions(driver);
		
	}

}
