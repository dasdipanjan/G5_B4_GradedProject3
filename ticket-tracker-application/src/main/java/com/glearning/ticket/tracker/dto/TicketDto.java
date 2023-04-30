package com.glearning.ticket.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * This class is responsible to carry ticket details information.
 *
 * @author Dipanjan Das
 * @version 1.0
 * @since 30-04-2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {
    private long id;
    private String ticketTitle;
    private String ticketShortDes;
    private String content;
    private LocalDateTime ticketCreatedTime;
}
