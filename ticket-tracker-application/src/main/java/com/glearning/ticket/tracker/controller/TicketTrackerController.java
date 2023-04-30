package com.glearning.ticket.tracker.controller;

import com.glearning.ticket.tracker.dto.SearchText;
import com.glearning.ticket.tracker.dto.TicketDto;
import com.glearning.ticket.tracker.services.TicketTrackerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

/**
 * This class is responsible to accept request for different web page in this application.
 *
 * @author Dipanjan Das
 * @version 1.0
 * @since 30-04-2023
 */
@Controller
@RequestMapping("/ticket/tracker")
@Slf4j
public class TicketTrackerController {
    @Autowired
    private TicketTrackerService ticketTrackerService;
    private SearchText searchText;

    /**
     * This is the constructor of TicketTrackerController
     */
    public TicketTrackerController() {
        this.searchText = new SearchText();
    }

    /**
     * This method is responsible to return ticket list page along with list of {@link com.glearning.ticket.tracker.model.Ticket}
     *
     * @param model Object of {@link Model} class.
     * @return ticket list page  name.
     */
    @GetMapping("/list")
    public String showTicketList(Model model) {
        log.info("Tickets display page has been invoked.");
        model.addAttribute("tickets", ticketTrackerService.getListOfTickets());
        model.addAttribute("searchString", this.searchText);
        return "ticket-list";
    }

    /**
     * This method is responsible to accept request for new ticket page and process this request.
     *
     * @param model Object of {@link Model} class.
     * @return create-ticket page.
     */
    @GetMapping("/create-ticket")
    public String createTicket(Model model) {
        model.addAttribute("ticketDto", new TicketDto());
        return "create-ticket";
    }

    /**
     * This method is responsible to accept ticket create request along with Ticket information in form of {@link TicketDto} object.
     *
     * @param model Object of {@link Model} class.
     * @param ticketDto Object of {@link TicketDto} class
     * @return the path of ticket list page.
     */
    @PostMapping("/saveTicket")
    public String saveTicket(Model model, @ModelAttribute("ticketDto") TicketDto ticketDto) {
        TicketDto savedDto = ticketTrackerService.saveTicketDetails(ticketDto);
        log.info("Ticket information have been saved. Information := {}", savedDto.toString());
        return "redirect:/ticket/tracker/list";
    }

    /**
     * This method is responsible to accept ticket update request along with updated information about ticket.
     *
     * @param model Object of {@link Model} class.
     * @param id
     * @return name of edit ticket page.
     */
    @GetMapping("/update/{id}")
    public String updateTicket(Model model, @PathVariable long id) {
        TicketDto ticketDto  = ticketTrackerService.getTicketDetails(id);
        model.addAttribute("ticketDto", ticketDto);
        return "edit-ticket";
    }

    /**
     * This method is responsible to accept save ticket request during new ticket creation.
     *
     * @param id ID of the selected ticket
     * @param ticketDto Object of {@link TicketDto}
     * @param model Object of {@link Model} class
     * @return  the name of the ticket list page.
     */
    @PostMapping("/update/ticket/{id}")
    public String saveTicket(@PathVariable long id, @ModelAttribute("ticketDto") TicketDto ticketDto, Model model) {
        TicketDto savedDto = ticketTrackerService.saveTicketDetails(ticketDto);
        log.info("Ticket information have been saved. Information := {}", savedDto.toString());
        return "redirect:/ticket/tracker/list";
    }

    /**
     * This method is responsible to accept ticket delete request by ID of the ticket.
     *
     * @param id ID value of the ticket.
     * @return the name of the ticket list page.
     */
    @GetMapping("/delete/{id}")
    public String deleteTicketById(@PathVariable long id) {
        ticketTrackerService.deleteTicketById(id);
        return "redirect:/ticket/tracker/list";
    }

    /**
     * This method is responsible to accept request to show the detail information of a particular ticket.
     *
     * @param model Object of the {@link Model} class.
     * @param id Ticket ID.
     * @return Ticket detail information page of application.
     */
    @GetMapping("/view/{id}")
    public String viewTicket(Model model, @PathVariable long id) {
        TicketDto ticketDto = ticketTrackerService.getTicketDetails(id);
        model.addAttribute("ticketDto", ticketDto);
        return "view-ticket";
    }

    /**
     * This method is responsible to accept ticket search request and supply searched ticket list page along with search results.
     *
     * @param model Object of {@link Model}
     * @param searchText Object of {@link SearchText}
     * @return Ticket List page.
     */
    @PostMapping("/search")
    public String searchTicket(Model model, @ModelAttribute("searchString") SearchText searchText){
        if (searchText != null) {
            model.addAttribute("tickets", ticketTrackerService.searchTickets(searchText.getSearchText()));
        } else {
            model.addAttribute("tickets", new ArrayList<TicketDto>());
        }
        model.addAttribute("searchString", this.searchText);
        return "ticket-list";
    }

}
