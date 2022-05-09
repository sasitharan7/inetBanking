package demo;

import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Log4jDemo {
	static Logger logger = Logger.getLogger(Log4jDemo.class);

	public static void main(String[] args) {
		System.out.println("Hello world ------\n");
		
		logger.info("This is info message");
		logger.error("This is err message");
		logger.warn("This is warn message");
		
	}
	

}
