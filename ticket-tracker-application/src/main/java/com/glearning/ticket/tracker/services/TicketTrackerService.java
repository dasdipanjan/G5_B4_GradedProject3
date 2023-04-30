package com.glearning.ticket.tracker.services;

import com.glearning.ticket.tracker.dto.TicketDto;

import java.util.List;
/**
 * This is the repository interface which is responsible to do all CURD operation with underneath database.
 *
 * @author Dipanjan Das
 * @version 1.0
 * @since 30-04-2023
 */
public interface TicketTrackerService {
    public List<TicketDto> getListOfTickets();
    public TicketDto saveTicketDetails(TicketDto details);
    public TicketDto getTicketDetails(long id);
    public void deleteTicketById(long id);
    public List<TicketDto> searchTickets(String searchText);
}
