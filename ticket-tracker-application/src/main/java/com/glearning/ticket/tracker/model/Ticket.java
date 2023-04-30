package com.glearning.ticket.tracker.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * This class is responsible to represent Ticket table in the database. This class is an entity class.
 *
 * @author Dipanjan Das
 * @version 1.0
 * @since 30-04-2023
 */
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_id_generator")
    @SequenceGenerator(
            name = "ticket_id_generator",
            sequenceName = "ticket_id_sequence",
            allocationSize = 1
    )
    @Column(name = "ticket_id")
    private long id;
    @Column(name = "ticket_title")
    private String ticketTitle;
    @Column(name = "ticket_short_des")
    private String ticketShortDes;
    @Column(name = "ticket_content", length = 65555)
    private String content;
    @CreationTimestamp
    @Column(name = "ticket_created_time")
    private LocalDateTime ticketCreatedTime;

    /**
     * Default constructor of this class.
     */
    public Ticket() {

    }

    /**
     * Parameterized Constructor of this class.
     *
     * @param id Ticket Id
     * @param ticketTitle Title of the {@link Ticket}
     * @param ticketShortDes Short description of the ticket
     * @param content Content of the ticket
     * @param ticketCreatedTime Ticket creation time.
     */
    public Ticket(long id, String ticketTitle, String ticketShortDes, String content, LocalDateTime ticketCreatedTime) {
        this.id = id;
        this.ticketTitle = ticketTitle;
        this.ticketShortDes = ticketShortDes;
        this.content = content;
        this.ticketCreatedTime = ticketCreatedTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTicketTitle() {
        return ticketTitle;
    }

    public void setTicketTitle(String ticketTitle) {
        this.ticketTitle = ticketTitle;
    }

    public String getTicketShortDes() {
        return ticketShortDes;
    }

    public void setTicketShortDes(String ticketShortDes) {
        this.ticketShortDes = ticketShortDes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTicketCreatedTime() {
        return ticketCreatedTime;
    }

    public void setTicketCreatedTime(LocalDateTime ticketCreatedTime) {
        this.ticketCreatedTime = ticketCreatedTime;
    }
}
