package edu.ncsu.csc216.ticket_manager.model.manager;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc216.ticket_manager.model.command.Command;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Category;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Priority;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.TicketType;

/**
 * The list of Tickets that exist in the system.
 * 
 * @author Isabella Samuelsson
 */
public class TicketList {

	/** Tickets in the Ticket Manager system */
	private ArrayList<Ticket> tickets;
	/**
	 * Constructs a TicketList object.
	 */
	public TicketList() {
		Ticket.setCounter(1);
		tickets = new ArrayList<Ticket>();
	}
	/**
	 * Adds a Ticket to a TicketList. Must provide Ticket information, ticketType, 
	 * subject, caller, category, priority, and note.
	 * 
	 * @param ticketType the type of Ticket to add
	 * @param subject of the Ticket
	 * @param caller person who made the Ticket
	 * @param category of the Ticket
	 * @param priority of the Ticket
	 * @param note a comment on what has been done to the Ticket
	 * @return the Ticket index
	 */
	public int addTicket(TicketType ticketType, String subject, String caller, Category category, Priority priority, String note) {
		Ticket ticket;
		try {
			ticket = new Ticket(ticketType, subject, caller, category, priority, note);
		}
		catch(IllegalArgumentException e) {
			throw new IllegalArgumentException("Ticket cannot be created");            //not sure if this is how supposed to implement
		}
		tickets.add(ticket);
		return ticket.getTicketId();
	}
	/**
	 * Adds a list of Tickets to a TicketList.
	 * 
	 * @param ticketsToAdd the tickets to add
	 */
	public void addTickets(List<Ticket> ticketsToAdd) {
		tickets.clear();
		Ticket.setCounter(1);
		
		int maxId = 0;
		for(int i = 0; i < ticketsToAdd.size(); i++) {
			if(ticketsToAdd.get(i).getTicketId() > maxId) {
				maxId = ticketsToAdd.get(i).getTicketId();
			}		
			tickets.add(ticketsToAdd.get(i));
			                                                  
		}
		Ticket.setCounter(maxId + 1);
	}
	/**
	 * Obtains a list of the current Tickets.
	 * 
	 * @return a list of Tickets
	 */
	public List<Ticket> getTickets() {
		return tickets;
	}
	/**
	 * Obtains a list of existing Tickets with a specified type.  
	 * 
	 * @param type of tickets to filter
	 * @return list of the Tickets with the specified type
	 */
	public List<Ticket> getTicketsByType(TicketType type) {
		if(type == null) {
			throw new IllegalArgumentException();
		}
		ArrayList<Ticket> sortedTickets = new ArrayList<Ticket>();
		for(int i = 0; i < tickets.size(); i++) {
			if(tickets.get(i).getTicketType() == type) {
				sortedTickets.add(tickets.get(i));
			}
		}
		return sortedTickets;
	}
	/**
	 * Obtains a Ticket using its Id.
	 * 
	 * @param id the Tickets Id
	 * @return the Ticket with the specified id
	 */
	public Ticket getTicketById(int id) {
		for(int i = 0; i < tickets.size(); i++) {
			if(tickets.get(i).getTicketId() == id) {
				return tickets.get(i);
			}
		}
		return null;
	}
	/**
	 * Executes a user specified command to update the state on a specified Ticket.
	 * 
	 * @param ticketId the id of the Ticket
	 * @param command the command to be executed
	 */
	public void executeCommand(int ticketId, Command command) {
		for(int i = 0; i < tickets.size(); i++) {
			if(tickets.get(i).getTicketId() == ticketId) {
				tickets.get(i).update(command);
			}
		}
	}
	/**
	 * Deletes a index specified Ticket.
	 * 
	 * @param id the id of the Ticket
	 */
	public void deleteTicketById(int id) {
		for(int i = 0; i < tickets.size(); i++) {
			if(tickets.get(i).getTicketId() == id) {
				tickets.remove(i);
				i--;
			}
		}
	}
}
