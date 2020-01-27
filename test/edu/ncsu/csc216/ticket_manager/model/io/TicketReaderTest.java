package edu.ncsu.csc216.ticket_manager.model.io;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket;
/**
 * Tests the TicketReader class in Project1.
 * 
 * @author Isabella Samuelsson
 */
public class TicketReaderTest {
	/** Valid ticket records */
	private final String validTestFile = "test-files/ticket1.txt";

	
	/** Expected results for valid ticket1 */
	private final String validTicket1 = "*1#New#Incident#GitHub down#sesmith5#Software#Urgent##" + 
			"\n-GitHub is not responding when I navigate to github.ncsu.edu\n";
	/** Expected results for valid ticket2 */
	private final String validTicket2 = "*2#Working#Request#Workshop account#sesmith5#Inquiry#Low#ccgurley#" + 
			"\n-Create a workshop account for access to a GitHub repo" + 
			"\n-Assigned to ccgurley." + 
			"\n-How long is the account needed for?" + 
			"\n-Until November 1\n";
	/** Expected results for valid ticket3 */
	private final String validTicket3 = "*3#Feedback#Request#Add Gradescope plugin to Moodle#ahoward#Software#Medium#itecs#Awaiting Provider" + 
			"\n-Add Gradescope plugin to Moodle to import grades" + 
			"\n-Checking with plugin provider\n";
	/** Expected results for valid ticket4 */
	private final String validTicket4 = "*4#Resolved#Incident#Lights not working in EBI 1011#jtking#Hardware#Medium#facilities#Workaround" + 
			"\n-Lights are not working in EB I 1011. " + 
			"\n-Cannot install dimmer switch.  Will leave on.\n";
	/** Expected results for valid ticket5 */
	private final String validTicket5 = "*5#Closed#Request#New VM#sesmith5#Inquiry#High#jtking#Completed" + 
			"\n-I would like to request a new VM for my class" + 
			"\n-Assigned to jtking" + 
			"\n-VM created" + 
			"\n-Request completed\n";
	/** Expected results for valid ticket6 */
	private final String validTicket6 = "*6#Canceled#Request#Pizza#wpack#Inquiry#Urgent##Inappropriate" + 
			"\n-Deliver a large pizza to EBII 1221!" + 
			"\n-No!\n";


	/**
	 * Tests readTicketFile.
	 */
	@Test
	public void testReadValidTicketRecords() {
		try {
			ArrayList<Ticket> tickets = TicketReader.readTicketFile(validTestFile);
			assertEquals(6, tickets.size());
			
			
			assertEquals(validTicket1, tickets.get(0).toString());
			assertEquals(validTicket2, tickets.get(1).toString());
			assertEquals(validTicket3, tickets.get(2).toString());
			assertEquals(validTicket4, tickets.get(3).toString());
			assertEquals(validTicket5, tickets.get(4).toString());
			assertEquals(validTicket6, tickets.get(5).toString());
			
			
			
		} catch (Exception e) {
			fail("Unexpected error reading " + validTestFile);
		}
	}


}
