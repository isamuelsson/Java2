package edu.ncsu.csc216.ticket_manager.model.manager;

import java.util.ArrayList;

import edu.ncsu.csc216.ticket_manager.model.command.Command;
import edu.ncsu.csc216.ticket_manager.model.io.TicketReader;
import edu.ncsu.csc216.ticket_manager.model.io.TicketWriter;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Category;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Priority;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.TicketType;

/**
 * Controls the creation and modification of TicketList objects.
 * 
 * @author Isabella Samuelsson
 */
public class TicketManager {
	/** List of tickets for the Ticket Manager system */
	private TicketList ticketList;
	/** Singleton TicketManger instance */
	private static final TicketManager TICKET_MANAGER = new TicketManager();
	/**
	 * TicketManager constructor.
	 */
	private TicketManager() {
		ticketList = new TicketList();
	}
	/**
	 * Obtains an the instance of a TicketManager.
	 * 
	 * @return a TicketManager
	 */
	public static TicketManager getInstance() {
		return TICKET_MANAGER;
	}
	/**
	 * Saves a TicketList to a file.
	 * 
	 * @param filename the name of the file
	 */
	public void saveTicketsToFile(String  filename) {
		try {
			TicketWriter.writeTicketFile(filename, ticketList.getTickets());
		}
		catch(Exception e) {
			throw new IllegalArgumentException();           //any message?
		}
	}
	/**
	 * Loads a file of Tickets to a TicketList object.
	 * 
	 * @param filename the name of the file
	 */
	public void loadTicketsFromFile(String filename) {
		ArrayList<Ticket> tickets = null;
		try {
			tickets = TicketReader.readTicketFile(filename);
		}
		catch(Exception e) {
			throw new IllegalArgumentException();           //any message?
		}
		ticketList.addTickets(tickets);
	}
	/**
	 * Creates a new TicketList object.
	 */
	public void createNewTicketList() {
		ticketList = new TicketList();
	}
	/**
	 * Transfers the Tickets in a TicketList to an array that 
	 * can be displayed to the user.
	 * 
	 * @return Tickets to display
	 */
	public String[][] getTicketsForDisplay(){
		ArrayList<Ticket> tickets = (ArrayList<Ticket>) ticketList.getTickets();
		String[][] display = new String[tickets.size()][6];
		for(int i = 0; i < tickets.size(); i++) {
			display[i][0] = "" + tickets.get(i).getTicketId();
			display[i][1] = tickets.get(i).getTicketTypeString();
			display[i][2] = tickets.get(i).getState();
			display[i][3] = tickets.get(i).getSubject();
			display[i][4] = tickets.get(i).getCategory();
			display[i][5] = tickets.get(i).getPriority();
		}
		return display;
	}
	/**
	 * Transfers a specific type of Ticket in a TicketList to an array that 
	 * can be displayed to the user. 
	 * 
	 * @param type of Tickets to display
	 * @return Tickets to display
	 */
	public String[][] getTicketsForDisplayByType(TicketType type){
		if(type == null) {
			throw new IllegalArgumentException();
		}
		ArrayList<Ticket> sortedTickets = (ArrayList<Ticket>) ticketList.getTicketsByType(type);
		String[][] sortedDisplay = new String[sortedTickets.size()][6];
		for(int i = 0; i < sortedTickets.size(); i++) {
			sortedDisplay[i][0] = "" + sortedTickets.get(i).getTicketId();
			sortedDisplay[i][1] = sortedTickets.get(i).getTicketTypeString();
			sortedDisplay[i][2] = sortedTickets.get(i).getState();
			sortedDisplay[i][3] = sortedTickets.get(i).getSubject();
			sortedDisplay[i][4] = sortedTickets.get(i).getCategory();
			sortedDisplay[i][5] = sortedTickets.get(i).getPriority();
		}
		return sortedDisplay;
	}
	/**
	 * Obtains a Ticket by id.
	 * 
	 * @param id of the Ticket
	 * @return the specified Ticket
	 */
	public Ticket getTicketById(int id) {
		return ticketList.getTicketById(id);
	}
	/**
	 * Executes a specified command on a specified Ticket.
	 * 
	 * @param index of the Ticket
	 * @param command that should be executed
	 */
	public void executeCommand(int index, Command command) {
		ticketList.executeCommand(index, command);
	}
	/**
	 * Deletes a Ticket from a TicketList by index.
	 * 
	 * @param index of the Ticket to delete
	 */
	public void deleteTicketById(int index) {
		ticketList.deleteTicketById(index);
	}
	/**
	 * Adds a Ticket to a TicketList. Requires Ticket type, subject, caller, category, priority, and a note.
	 * 
	 * @param type of Ticket
	 * @param subject of the Ticket
	 * @param caller of the Ticket
	 * @param category of the Ticket
	 * @param priority of the Ticket
	 * @param note on what has been done to resolve the Ticket
	 */
	public void addTicketToList(TicketType type, String subject, String caller, Category category, Priority priority, String note) {
		ticketList.addTicket(type, subject, caller, category, priority, note);
	}
	
}
