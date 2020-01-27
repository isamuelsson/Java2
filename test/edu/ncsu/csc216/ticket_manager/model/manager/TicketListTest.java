package edu.ncsu.csc216.ticket_manager.model.manager;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.ticket_manager.model.command.Command;

import edu.ncsu.csc216.ticket_manager.model.command.Command.CommandValue;

import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Category;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Priority;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.TicketType;

/**
 * Tests the TicketList class in Project1.
 * 
 * @author Isabella Samuelsson
 */
public class TicketListTest {
	TicketList list;
	/**
	 * Sets up testing materials.
	 */
	@Before
	public void setUp() {
		list = new TicketList();	
	}
	/**
	 * Tests the DeleteTicketById method.
	 */
	@Test
	public void testDeleteTicketById() {
		list.addTicket(TicketType.INCIDENT, "subject", "caller", Category.DATABASE, Priority.HIGH, "note");
		list.deleteTicketById(1);
		
		assertEquals(list.getTickets().size(), 0);
	}
	/**
	 * Tests the executeCommand method.
	 */
	@Test
	public void testExecuteCommand() {
		list.addTicket(TicketType.INCIDENT, "subject", "caller", Category.DATABASE, Priority.HIGH, "note");
		assertTrue(list.getTicketById(1).getState() == Ticket.NEW_NAME);
		
		Command command = new Command(CommandValue.PROCESS, "insamu", null, null, null, "I am a note.");
		list.executeCommand(1, command);
		
		assertTrue(list.getTicketById(1).getState() == Ticket.WORKING_NAME);
	}
	/**
	 * Tests the getTicketById method.
	 */
	@Test
	public void testGetTicketById() {
		ArrayList<String> notes = new ArrayList<String>();
		notes.add("GitHub is not responding when I navigate to github.ncsu.edu");
		
		ArrayList<Ticket> ticket = new ArrayList<Ticket>();
		Ticket tic = new Ticket(8, Ticket.RESOLVED_NAME, Ticket.TT_INCIDENT, "GitHub down", "sesmith5", "Software", "Urgent", "insam2", Command.RC_NOT_SOLVED, notes);
		ticket.add(tic);
		
		list.addTickets(ticket);
		assertEquals(list.getTicketById(8), tic);   
		
		
	}
	/**
	 * Tests the getTicketsByType method.
	 */
	@Test
	public void testGetTicketsByType() {
		ArrayList<String> notes = new ArrayList<String>();
		notes.add("GitHub is not responding when I navigate to github.ncsu.edu");
		
		ArrayList<String> notes2 = new ArrayList<String>();
		notes2.add("Lights are not working in EB I 1011.");
		notes2.add("Cannot install dimmer switch.  Will leave on.");
		
		ArrayList<Ticket> ticket = new ArrayList<Ticket>();
		Ticket tic1 = new Ticket(1, Ticket.NEW_NAME, Ticket.TT_INCIDENT, "GitHub down", "sesmith5", "Software", "Urgent", null, null, notes);
		Ticket tic2 = new Ticket(4, Ticket.RESOLVED_NAME, Ticket.TT_INCIDENT, "Lights not working in EBI 1011", "jtking", "Hardware", "Medium", "facilities", "Workaround", notes2);
		ticket.add(tic1);  
		ticket.add(tic2);
		list.addTickets(ticket);
		
		ArrayList<Ticket> ticket2 = (ArrayList<Ticket>)list.getTickets();
		
		assertEquals(ticket2.get(0).getTicketId(), list.getTicketsByType(Ticket.TicketType.INCIDENT).get(0).getTicketId());
		assertEquals(ticket2.get(1).getTicketId(), list.getTicketsByType(Ticket.TicketType.INCIDENT).get(1).getTicketId());
	}
	/**
	 * Tests the getTickets method.
	 */
	@Test
	public void testGetTickets() {
		ArrayList<String> notes = new ArrayList<String>();
		notes.add("GitHub is not responding when I navigate to github.ncsu.edu");
		
		ArrayList<String> notes2 = new ArrayList<String>();
		notes2.add("Lights are not working in EB I 1011.");
		notes2.add("Cannot install dimmer switch.  Will leave on.");
		
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		Ticket tic1 = new Ticket(1, Ticket.NEW_NAME, Ticket.TT_INCIDENT, "GitHub down", "sesmith5", "Software", "Urgent", null, null, notes);
		Ticket tic2 = new Ticket(4, Ticket.RESOLVED_NAME, Ticket.TT_INCIDENT, "Lights not working in EBI 1011", "jtking", "Hardware", "Medium", "facilities", "Workaround", notes2);
		tickets.add(tic1);  
		tickets.add(tic2);
		
		list.addTickets(tickets);
		assertEquals(tickets, list.getTickets());		
	}
	/**
	 * Tests the addTickets method.
	 */
	@Test
	public void testAddTickets() {
		ArrayList<String> notes = new ArrayList<String>();
		notes.add("GitHub is not responding when I navigate to github.ncsu.edu");
		
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		Ticket tic1 = new Ticket(1, Ticket.NEW_NAME, Ticket.TT_INCIDENT, "GitHub down", "sesmith5", "Software", "Urgent", null, null, notes);
		tickets.add(tic1); 
		list.addTickets(tickets);	
		
		assertEquals(list.getTickets().size(), tickets.size());
	}
	

}
