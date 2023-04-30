package com.glearning.ticket.tracker.services.impl;

import com.glearning.ticket.tracker.dto.TicketDto;
import com.glearning.ticket.tracker.model.Ticket;
import com.glearning.ticket.tracker.repository.TicketCrudRepository;
import com.glearning.ticket.tracker.services.TicketTrackerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This is the implementation class of {@link TicketTrackerService} interface. This class is having all business logic implementation
 * of Ticket Tracker Application.
 *
 * @author Dipanjan Das
 * @version 1.0
 * @since 30-04-2023
 */
@Service
@Slf4j
public class TicketTrackerServiceImpl implements TicketTrackerService {
    @Autowired
    private TicketCrudRepository ticketCrudRepository;

    /**
     * This method is responsible provide list of {@link TicketDto} object.
     *
     * @return List of {@link TicketDto} object.
     */
    @Override
    public List<TicketDto> getListOfTickets() {
        List<TicketDto> ticketDtoList = new ArrayList<>();
        List<Ticket> ticketList = ticketCrudRepository.findAll();
        if (!ticketList.isEmpty()) {
            log.info("Ticket List Size := {}", ticketList.size());
            ticketList.forEach(ticket -> {
                TicketDto details = new TicketDto();
                BeanUtils.copyProperties(ticket, details);
                ticketDtoList.add(details);
            });
        }
        log.info("Ticket Details List Size := {}", ticketDtoList.size());
        return ticketDtoList;
    }

    /**
     * This method is responsible to save ticket information and return {@link TicketDto} along with Ticket ID.
     *
     * @param details Object of {@link TicketDto}
     * @return Object of {@link TicketDto} along with ID
     */
    @Override
    public TicketDto saveTicketDetails(TicketDto details) {
        Ticket saveOrUpdatedTicket = null;
        Optional<Ticket> optional = ticketCrudRepository.findById(details.getId());
        if (optional.isPresent()) {
            LocalDateTime localDateTime = optional.get().getTicketCreatedTime();
            BeanUtils.copyProperties(details, optional.get());
            optional.get().setTicketCreatedTime(localDateTime);
            saveOrUpdatedTicket = ticketCrudRepository.saveAndFlush(optional.get());
            log.info("Ticket has been successfully updated.");
        } else {
            Ticket ticket = new Ticket();
            BeanUtils.copyProperties(details, ticket);
            saveOrUpdatedTicket = ticketCrudRepository.saveAndFlush(ticket);
            log.info("New ticket record has been created.");
        }
        BeanUtils.copyProperties(saveOrUpdatedTicket, details);
        return details;
    }

    /**
     * This method is responsible to retrieve Ticket information from database by using ticket id.
     *
     * @param id Ticket ID.
     * @return Object of {@link TicketDto}
     */
    @Override
    public TicketDto getTicketDetails(long id) {
        TicketDto details = new TicketDto();
        Optional<Ticket> optional = ticketCrudRepository.findById(id);
        if (optional.isPresent()) {
            BeanUtils.copyProperties(optional.get(), details);
            return details;
        }
        return null;
    }

    /**
     * This method is responsible to delete Ticket from database record by using ticket id.
     *
     * @param id Ticket ID
     */
    @Override
    public void deleteTicketById(long id) {
        Optional<Ticket> optional = ticketCrudRepository.findById(id);
        if (optional.isPresent()) {
            ticketCrudRepository.delete(optional.get());
            log.info("Ticket has been successfully deleted.");
        } else {
            log.info("Requested Ticket ID not found in database.");
        }
    }

    /**
     * This method is responsible to search Tickets from database by using search string.
     *
     * @param searchText Search String
     * @return List of {@link TicketDto} object if search string found otherwise Empty {@link TicketDto} list.
     */
    @Override
    public List<TicketDto> searchTickets(String searchText) {
        List<TicketDto> searchedTicketList = new ArrayList<>();
        List<Ticket> searchedTicket = ticketCrudRepository.findTicketByTitleText(searchText);
        if (searchedTicket != null && !searchedTicket.isEmpty()) {
            log.info("Tickets with searched title have been found.");
            searchedTicket.forEach(ticket -> {
                TicketDto ticketDto = new TicketDto();
                BeanUtils.copyProperties(ticket, ticketDto);
                searchedTicketList.add(ticketDto);
            });
        } else {
            log.info("Tickets not found with searched title.");
        }
        return searchedTicketList;
    }
}
