package com.glearning.ticket.tracker.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * This class is a configuration class. This class is responsible to apply all application related configuration.
 *
 * @author Dipanjan Das
 * @version 1.0
 * @since 30-04-2023
 */
@Configuration
@Slf4j
public class TicketTrackerAppConfig {
    /**
     * This is constructor of {@link TicketTrackerAppConfig} class.
     */
    public TicketTrackerAppConfig() {
        log.info("Configuration class has been loaded for Ticket Tracker Application.");
    }
}
