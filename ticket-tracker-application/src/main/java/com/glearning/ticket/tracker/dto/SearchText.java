package com.glearning.ticket.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is responsible to carry search string for ticket search.
 *
 * @author Dipanjan Das
 * @version 1.0
 * @since 30-04-2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchText {
    private String searchText;
}
