package com.glearning.ticket.tracker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This class is responsible to accept request for Welcome page in this application.
 *
 * @author Dipanjan Das
 * @version 1.0
 * @since 30-04-2023
 */
@Controller
@Slf4j
public class HomeController {
    /**
     * This method is responsible to accept request for welcome page under root directory.
     *
     * @return the name of the Welcome page.
     */
    @GetMapping("/")
    public String getHomePage() {
        log.info("Welcome page has been requested.");
        return "welcome-page";
    }
}
