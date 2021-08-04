package rough;

import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class RoughTest {

	public static Logger log = Logger.getLogger(RoughTest.class);
	
	public static void main(String[] args) {
		Date d = new Date();
		
		String filename = d.toString().replace(" ", "_").replace(":", "_");
		System.setProperty("current.date", filename);
		
//		System.setProperty("log4j.configurationFile", "./src/test/resources/properties/log4j2.properties");
		PropertyConfigurator.configure("./src/test/resources/properties/log4j.properties");
		
		log.info("Testing Logs");

	}

}
