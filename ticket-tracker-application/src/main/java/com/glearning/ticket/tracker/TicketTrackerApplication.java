package com.glearning.ticket.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class is the starter class of this application. This is class is having main method
 * which is called by main thread of the JVM to start execution of the application.
 *
 * @author Dipanjan Das
 * @version 1.0
 * @since 30-04-2023
 */
@SpringBootApplication
public class TicketTrackerApplication {

	/**
	 * This method is called by main thread of the JVM.
	 *
	 * @param args String array as command line argument.
	 */
	public static void main(String[] args) {
		SpringApplication.run(TicketTrackerApplication.class, args);
	}

}
