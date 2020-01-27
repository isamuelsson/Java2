package edu.ncsu.csc216.ticket_manager.model.ticket;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.ticket_manager.model.command.Command;
import edu.ncsu.csc216.ticket_manager.model.command.Command.CancellationCode;
import edu.ncsu.csc216.ticket_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.ticket_manager.model.command.Command.FeedbackCode;
import edu.ncsu.csc216.ticket_manager.model.command.Command.ResolutionCode;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Category;

import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Priority;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.TicketType;

/**
 * Tests the Ticket class in Project1.
 * 
 * @author Isabella Samuelsson
 */
public class TicketTest {
	String notCorrectCode = "Not a code";
	String note = "This is a note.";
	String caller = "caller";
	String subject = "food";
	String owner = "me";
	int ticketId = 123;
	ArrayList<String> notes = new ArrayList<String>();
	/**
	 * Setup method for all tests.
	 */
	@Before
	public void setUp() {
		Ticket.setCounter(1);
	}
	/**
	 * Tests the setCounter method.
	 */
	@Test
	public void testSetCounter() { 
		Ticket tic = new Ticket(TicketType.INCIDENT, subject, caller, Category.DATABASE, Priority.HIGH, note);
		assertEquals(tic.getTicketId(), 1);
		
	}
	/**
	 * Tests creating invalid tickets using the create a new ticket ticket constructor.
	 */
	@Test
	public void testInvalidTicketConstructor1() {
		Ticket tic = null;
		try {
			tic = new Ticket(null, subject, caller, Category.DATABASE, Priority.HIGH, note);
		}
		catch(IllegalArgumentException e) {
			assertNull(tic);
		}
		try {
			tic = new Ticket(TicketType.INCIDENT, "", caller, Category.DATABASE, Priority.HIGH, note);
		}
		catch(IllegalArgumentException e) {
			assertNull(tic);
		}
		try {
			tic = new Ticket(TicketType.INCIDENT, subject, "", Category.DATABASE, Priority.HIGH, note);
		}
		catch(IllegalArgumentException e) {
			assertNull(tic);
		}
		
	}

	/**
	 * Tests the getCaller method.
	 */
	@Test
	public void testGetCaller() {
		Ticket tic = new Ticket(123, Ticket.CLOSED_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.RC_COMPLETED, notes);
		assertTrue(tic.getCaller().equals(caller));
	}
	/**
	 * Tests the getCancellationCode method. 
	 */
	@Test
	public void testGetCancellationCode() { 
		notes.add("I am a note.");
		Ticket tic = new Ticket(123, Ticket.CANCELED_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.CC_INAPPROPRIATE, notes);
		assertEquals(tic.getCancellationCode(), Command.CC_INAPPROPRIATE);
		
		Ticket tic1 = new Ticket(123, Ticket.CANCELED_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.CC_DUPLICATE, notes);
		assertEquals(tic1.getCancellationCode(), Command.CC_DUPLICATE);
		
	}
	/**
	 * Tests the getCategory method. 
	 */
	@Test
	public void testGetCategory() { 
		notes.add("I am a note.");
		Ticket tic = new Ticket(123, Ticket.NEW_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, null, notes);
		assertEquals(tic.getCategory(), Ticket.C_SOFTWARE);
		
		Ticket tic1 = new Ticket(123, Ticket.NEW_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_DATABASE, Ticket.P_HIGH, owner, null, notes);
		assertEquals(tic1.getCategory(), Ticket.C_DATABASE);
		
		Ticket tic2 = new Ticket(123, Ticket.NEW_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_NETWORK, Ticket.P_HIGH, owner, null, notes);
		assertEquals(tic2.getCategory(), Ticket.C_NETWORK);
		
		Ticket tic3 = new Ticket(123, Ticket.NEW_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_HARDWARE, Ticket.P_HIGH, owner, null, notes);
		assertEquals(tic3.getCategory(), Ticket.C_HARDWARE);
		
		Ticket tic4 = new Ticket(123, Ticket.NEW_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_INQUIRY, Ticket.P_HIGH, owner, null, notes);
		assertEquals(tic4.getCategory(), Ticket.C_INQUIRY);
		
	}
	/**
	 * Tests the getFeedbackCode method. 
	 */
	@Test
	public void testGetFeedbackCode() { 
		notes.add(note);
		Ticket tic = new Ticket(123, Ticket.FEEDBACK_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.F_CALLER, notes);
		assertEquals(tic.getFeedbackCode(), Command.F_CALLER);
		
		Ticket tic1 = new Ticket(123, Ticket.FEEDBACK_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.F_CHANGE, notes);
		assertEquals(tic1.getFeedbackCode(), Command.F_CHANGE);
		
		Ticket tic2 = new Ticket(123, Ticket.FEEDBACK_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.F_PROVIDER, notes);
		assertEquals(tic2.getFeedbackCode(), Command.F_PROVIDER);
	}
	/**
	 * Tests the getNotes method. 
	 */
	@Test
	public void testGetNotes() { 
		notes.add(note);
		Ticket tic = new Ticket(123, Ticket.CANCELED_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.CC_INAPPROPRIATE, notes);
		assertEquals(tic.getNotes(), "-" + notes.get(0) + "\n");
	}
	/**
	 * Tests the getOwner method. 
	 */
	@Test
	public void testGetOwner() { 
		notes.add(note);
		Ticket tic = new Ticket(123, Ticket.CANCELED_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.CC_INAPPROPRIATE, notes);
		assertEquals(tic.getOwner(), owner);
	}
	/**
	 * Tests the getPrioirity method. 
	 */
	@Test
	public void testGetPriority() { 
		notes.add(note);
		Ticket tic = new Ticket(123, Ticket.CANCELED_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.CC_INAPPROPRIATE, notes);
		assertEquals(tic.getPriority(), Ticket.P_HIGH);
	}
	/**
	 * Tests the getCategory method. 
	 */
	@Test
	public void testGetResolutionCode() { 
		notes.add(note);
		Ticket tic = new Ticket(123, Ticket.RESOLVED_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.RC_COMPLETED, notes);
		assertTrue(tic.getResolutionCode().equals(Command.RC_COMPLETED));
		
		Ticket tic1 = new Ticket(123, Ticket.RESOLVED_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.RC_NOT_COMPLETED, notes);
		assertTrue(tic1.getResolutionCode().equals(Command.RC_NOT_COMPLETED));
		
		Ticket tic2 = new Ticket(123, Ticket.RESOLVED_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.RC_CALLER_CLOSED, notes);
		assertTrue(tic2.getResolutionCode().equals(Command.RC_CALLER_CLOSED));
		
		Ticket tic3 = new Ticket(123, Ticket.RESOLVED_NAME, Ticket.TT_INCIDENT, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.RC_CALLER_CLOSED, notes);
		assertTrue(tic3.getResolutionCode().equals(Command.RC_CALLER_CLOSED));
		
		Ticket tic4 = new Ticket(123, Ticket.RESOLVED_NAME, Ticket.TT_INCIDENT, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.RC_WORKAROUND, notes);
		assertTrue(tic4.getResolutionCode().equals(Command.RC_WORKAROUND));
		
		Ticket tic5 = new Ticket(123, Ticket.RESOLVED_NAME, Ticket.TT_INCIDENT, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.RC_NOT_SOLVED, notes);
		assertTrue(tic5.getResolutionCode().equals(Command.RC_NOT_SOLVED));
		
		Ticket tic6 = new Ticket(123, Ticket.RESOLVED_NAME, Ticket.TT_INCIDENT, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.RC_SOLVED, notes);
		assertTrue(tic6.getResolutionCode().equals(Command.RC_SOLVED));
	}
	/**
	 * Tests the getState method. 
	 */
	@Test
	public void testGetState() { 
		notes.add(note);
		Ticket tic = new Ticket(123, Ticket.CANCELED_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.CC_DUPLICATE, notes);
		assertTrue(tic.getState().equals(Ticket.CANCELED_NAME));
		
		Ticket tic1 = new Ticket(123, Ticket.RESOLVED_NAME, Ticket.TT_INCIDENT, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.RC_WORKAROUND, notes);
		assertTrue(tic1.getState().equals(Ticket.RESOLVED_NAME));
		
		Ticket tic2 = new Ticket(123, Ticket.WORKING_NAME, Ticket.TT_INCIDENT, subject, caller, Ticket.C_DATABASE, Ticket.P_HIGH, owner, null, notes);
		assertTrue(tic2.getState().equals(Ticket.WORKING_NAME));
		
	}
	/**
	 * Tests the getSubject method. 
	 */
	@Test
	public void testGetSubject() { 
		notes.add(note);
		Ticket tic = new Ticket(123, Ticket.RESOLVED_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.RC_COMPLETED, notes);
		assertTrue(tic.getSubject().equals(subject));
	}
	/**
	 * Tests the getTicketId method. 
	 */
	@Test
	public void testGetTicketId() { 
		notes.add(note);
		Ticket tic = new Ticket(ticketId, Ticket.RESOLVED_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_INQUIRY, Ticket.P_HIGH, owner, Command.RC_COMPLETED, notes);
		assertEquals(tic.getTicketId(), ticketId);
	}
	/**
	 * Tests the getTicketType method. 
	 */
	@Test
	public void testGetTicketType() { 
		notes.add(note);
		Ticket tic = new Ticket(ticketId, Ticket.FEEDBACK_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_HARDWARE, Ticket.P_HIGH, owner, Command.F_CHANGE, notes);
		assertEquals(tic.getTicketType(), TicketType.REQUEST);
		
	}
	/**
	 * Tests the getTicketTypeString method. 
	 */
	@Test
	public void testGetTicketTypeString() { 
		notes.add(note);
		Ticket tic = new Ticket(ticketId, Ticket.FEEDBACK_NAME, Ticket.TT_INCIDENT, subject, caller, Ticket.C_NETWORK, Ticket.P_HIGH, owner, Command.F_PROVIDER, notes);
		assertEquals(tic.getTicketTypeString(), Ticket.TT_INCIDENT);
		
		Ticket tic1 = new Ticket(ticketId, Ticket.FEEDBACK_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.F_CHANGE, notes);
		assertEquals(tic1.getTicketTypeString(), Ticket.TT_REQUEST);
	}
	/**
	 * Tests the setPriority method. 
	 */
	@Test
	public void testSetPriority() { 
		notes.add(note);
		Ticket tic = new Ticket(ticketId, Ticket.FEEDBACK_NAME, Ticket.TT_INCIDENT, subject, caller, Ticket.C_NETWORK, Ticket.P_HIGH, owner, Command.F_PROVIDER, notes);
		assertEquals(tic.getPriority(), Ticket.P_HIGH);
		
		Ticket tic1 = new Ticket(ticketId, Ticket.FEEDBACK_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_LOW, owner, Command.F_CHANGE, notes);
		assertEquals(tic1.getPriority(), Ticket.P_LOW);
		
		Ticket tic2 = new Ticket(ticketId, Ticket.FEEDBACK_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_MEDIUM, owner, Command.F_CHANGE, notes);
		assertEquals(tic2.getPriority(), Ticket.P_MEDIUM);
		
		Ticket tic3 = new Ticket(ticketId, Ticket.FEEDBACK_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_URGENT, owner, Command.F_CHANGE, notes);
		assertEquals(tic3.getPriority(), Ticket.P_URGENT);
	}
	/**
	 * Tests the setFeedbackCode method. 
	 */
	@Test
	public void testSetFeedbackCode() { 
		notes.add(note);
		Ticket tic = null;
		try {
			tic = new Ticket(ticketId, Ticket.FEEDBACK_NAME, Ticket.TT_INCIDENT, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, notCorrectCode, notes);
		}
		catch(IllegalArgumentException e) {
			assertNull(tic);
		}
		
		
	}
	/**
	 * Tests the setState method. 
	 */
	@Test
	public void testSetState() { 
		notes.add(note);
		Ticket tic = null;
		try {
			tic = new Ticket(ticketId, "InvalidState", Ticket.TT_INCIDENT, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, notCorrectCode, notes);
		}
		catch(IllegalArgumentException e) {
			assertNull(tic);
		}
		
		
	}
	
	/**
	 * Tests the setTicketType method. 
	 */
	@Test
	public void testSetTicketType() { 
		notes.add(note);
		Ticket tic = null;
		try {
			tic = new Ticket(ticketId, Ticket.FEEDBACK_NAME, "invalidTicketType", subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, notCorrectCode, notes);
		}
		catch(IllegalArgumentException e) {
			assertNull(tic);
		}
		
		
	}
	/**
	 * Tests the setResolutionCode method. 
	 */
	@Test
	public void testSetResolutionCode() { 
		notes.add(note);
		Ticket tic = null;
		try {
			tic = new Ticket(ticketId, Ticket.RESOLVED_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, notCorrectCode, notes);
		}
		catch(IllegalArgumentException e) {
			assertNull(tic);
		}
		try {
			tic = new Ticket(ticketId, Ticket.RESOLVED_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.RC_NOT_SOLVED, notes);
		}
		catch(IllegalArgumentException e) {
			assertNull(tic);
		}
		try {
			tic = new Ticket(ticketId, Ticket.RESOLVED_NAME, Ticket.TT_INCIDENT, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.RC_NOT_COMPLETED, notes);
		}
		catch(IllegalArgumentException e) {
			assertNull(tic);
		}
		
	}	
	/**
	 * Tests the setCancellationCode method. 
	 */
	@Test
	public void testSetCancellationCode() { 
		notes.add(note);
		Ticket tic = null;
		try {
			tic = new Ticket(ticketId, Ticket.CANCELED_NAME, Ticket.TT_INCIDENT, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, notCorrectCode, notes);
		}
		catch(IllegalArgumentException e) {
			assertNull(tic);
		}
		
		
	}	
	
	/**
	 * Tests the setToString method. 
	 */
	@Test
	public void testToString() { 
		notes.add(note);
		Ticket tic = new Ticket(123, Ticket.CANCELED_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.CC_INAPPROPRIATE, notes);
		assertEquals(tic.toString(), "*" + 123 + "#" + Ticket.CANCELED_NAME + "#" + Ticket.TT_REQUEST + "#" + subject + "#" + caller + "#" + Ticket.C_SOFTWARE + "#" + Ticket.P_HIGH + "#" + owner + "#" + Command.CC_INAPPROPRIATE + "\n" + "-" + notes.get(0) + "\n");
		
		Ticket tic1 = new Ticket(123, Ticket.RESOLVED_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.RC_COMPLETED, notes);
		assertEquals(tic1.toString(), "*" + 123 + "#" + Ticket.RESOLVED_NAME + "#" + Ticket.TT_REQUEST + "#" + subject + "#" + caller + "#" + Ticket.C_SOFTWARE + "#" + Ticket.P_HIGH + "#" + owner + "#" + Command.RC_COMPLETED + "\n" + "-" + notes.get(0) + "\n");
		
		Ticket tic2 = new Ticket(123, Ticket.FEEDBACK_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.F_CHANGE, notes);
		assertEquals(tic2.toString(), "*" + 123 + "#" + Ticket.FEEDBACK_NAME + "#" + Ticket.TT_REQUEST + "#" + subject + "#" + caller + "#" + Ticket.C_SOFTWARE + "#" + Ticket.P_HIGH + "#" + owner + "#" + Command.F_CHANGE + "\n" + "-" + notes.get(0) + "\n");
		
	}
	
	
	/**
	 * Tests the update method.  
	 */
	@Test
	public void testUpdate() { 
		notes.add(note);
		
	
		Ticket tic2 = new Ticket(123, Ticket.CLOSED_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.RC_COMPLETED, notes);
		Command command2 = new Command(CommandValue.REOPEN, owner, null, null, null, note);
		tic2.update(command2);
		assertEquals(tic2.getState(), Ticket.WORKING_NAME);
		
		Ticket tic3 = new Ticket(123, Ticket.NEW_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, null, notes);
		Command command3 = new Command(CommandValue.PROCESS, owner, null, null, null, note);
		tic3.update(command3);
		assertEquals(tic3.getState(), Ticket.WORKING_NAME);
		
		Ticket tic4 = new Ticket(123, Ticket.FEEDBACK_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.F_CALLER, notes);
		Command command4 = new Command(CommandValue.REOPEN, owner, null, null, null, note);
		tic4.update(command4);
		assertEquals(tic4.getState(), Ticket.WORKING_NAME);
		
		Ticket tic5 = new Ticket(123, Ticket.CANCELED_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.CC_DUPLICATE, notes);
		Command command5 = new Command(CommandValue.RESOLVE, owner, null, ResolutionCode.CALLER_CLOSED, null, note);
		try {
			tic5.update(command5);
		}
		catch(UnsupportedOperationException e){
			assertEquals(tic5.getState(), Ticket.CANCELED_NAME);
		}
		
		Ticket tic6 = new Ticket(123, Ticket.WORKING_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.F_CALLER, notes);
		Command command6 = new Command(CommandValue.FEEDBACK, owner, FeedbackCode.AWAITING_CALLER, null, null, note);
		tic6.update(command6);
		assertEquals(tic6.getState(), Ticket.FEEDBACK_NAME);
		
		Ticket tic7 = new Ticket(123, Ticket.WORKING_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, null, notes);
		Command command7 = new Command(CommandValue.CANCEL, owner, null, null, CancellationCode.DUPLICATE, note);
		tic7.update(command7);
		assertEquals(tic7.getState(), Ticket.CANCELED_NAME);
		
		Ticket tic8 = new Ticket(123, Ticket.NEW_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, null, notes);
		Command command8 = new Command(CommandValue.CANCEL, owner, null, null, CancellationCode.DUPLICATE, note);
		tic8.update(command8);
		assertEquals(tic8.getState(), Ticket.CANCELED_NAME);
		
		Ticket tic9 = new Ticket(123, Ticket.FEEDBACK_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.F_CALLER, notes);
		Command command9 = new Command(CommandValue.CANCEL, owner, null, null, CancellationCode.INAPPROPRIATE, note);
		tic9.update(command9);
		assertEquals(tic9.getState(), Ticket.CANCELED_NAME);
		
		Ticket tic10 = new Ticket(123, Ticket.RESOLVED_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.RC_COMPLETED, notes);		
 		Command command10 = new Command(CommandValue.FEEDBACK, owner, FeedbackCode.AWAITING_CALLER, ResolutionCode.COMPLETED, null, note);		
 		tic10.update(command10);		
 		assertEquals(tic10.getState(), Ticket.FEEDBACK_NAME);		

 
  		Ticket tic11 = new Ticket(123, Ticket.FEEDBACK_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.F_CALLER, notes);		
 		Command command11 = new Command(CommandValue.REOPEN, owner, null, ResolutionCode.CALLER_CLOSED, null, note);		
 		tic11.update(command11);		
 		assertEquals(tic11.getState(), Ticket.WORKING_NAME);
 		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	

}
