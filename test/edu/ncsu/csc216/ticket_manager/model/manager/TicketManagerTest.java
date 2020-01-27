package edu.ncsu.csc216.ticket_manager.model.manager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.ticket_manager.model.command.Command;
import edu.ncsu.csc216.ticket_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Category;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Priority;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.TicketType;

/**
 * Tests the TicketManager class in Project1.
 * 
 * @author Isabella Samuelsson
 */
public class TicketManagerTest {
	TicketManager manager = null;
	/**
	 * Testing setup.
	 */
	@Before
	public void setUp() {
		
		manager = TicketManager.getInstance();
		
		assertTrue(manager != null);
		manager.addTicketToList(TicketType.INCIDENT, "Lights not working in EBI 1011", "jtking", Category.HARDWARE, Priority.MEDIUM, "hi");
	}
	/**
	 * Testing loadTicketsFromFile method.
	 */
	@Test
	public void testLoadTicketsFromFile() {
		manager.loadTicketsFromFile("test-files/ticket1.txt");
		
		assertEquals(manager.getTicketsForDisplay().length, 6);
	}
	/**
	 * Testing getTicketById method.
	 */
	@Test
	public void testGetTicketById() {
		assertEquals(manager.getTicketById(1).getTicketId(), 1);
	}
	/**
	 * Testing executeCommand method.
	 */
	@Test
	public void testExecuteCommand() {
		Command command = new Command(CommandValue.PROCESS, "insam", null, null, null, "hola");
		
		manager.executeCommand(1, command);
		assertEquals(manager.getTicketById(1).getState(), Ticket.WORKING_NAME);
	}
	/**
	 * Testing deleteTicketById method.
	 */
	@Test
	public void testDeleteTicketById() {		
		manager.deleteTicketById(1);
		assertEquals(manager.getTicketsForDisplay().length, 9);  
	}
	/**
	 * Testing addTicketToList method.
	 */
	@Test
	public void testAddTicketToList() {
		assertEquals(manager.getTicketsForDisplay().length, 10); 
	}
	/**
	 * Testing getTicketsForDisplay method.
	 */
	@Test
	public void testGetTicketsForDisplay() {
		String[][] tickets = manager.getTicketsForDisplay();
		assertEquals(tickets.length, 8);
	}
	/**
	 * Testing getTicketsForDisplayByType method.
	 */
	@Test
	public void testGetTicketsForDisplayByType() {
		String[][] type = manager.getTicketsForDisplayByType(TicketType.INCIDENT);
		for(int i = 0; i < type.length - 1; i++) {
			assertEquals(type[i][1], "Incident");
		}
	}
	
	

}
