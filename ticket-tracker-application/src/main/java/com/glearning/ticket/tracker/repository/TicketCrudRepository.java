package com.glearning.ticket.tracker.repository;

import com.glearning.ticket.tracker.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is the repository interface which is responsible to do all CURD operation with underneath database.
 *
 * @author Dipanjan Das
 * @version 1.0
 * @since 30-04-2023
 */
@Repository
public interface TicketCrudRepository extends JpaRepository<Ticket, Long> {
    /**
     * This method is responsible to find list of tickets by using search string in like query.
     *
     * @param searchText String variable.
     * @return List of {@link Ticket}
     */
    @Query("select ticket from Ticket ticket where ticket.ticketShortDes like  %:searchText%")
    List<Ticket> findTicketByTitleText(@Param("searchText") String searchText);
}
