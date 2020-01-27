package edu.ncsu.csc216.ticket_manager.model.ticket;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import edu.ncsu.csc216.ticket_manager.model.command.Command;
import edu.ncsu.csc216.ticket_manager.model.command.Command.CancellationCode;
import edu.ncsu.csc216.ticket_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.ticket_manager.model.command.Command.FeedbackCode;
import edu.ncsu.csc216.ticket_manager.model.command.Command.ResolutionCode;
/**
 * Tests the TicketState class in Project1.
 * 
 * @author Isabella Samuelsson 
 */
public class TicketStateTest {
	private String note = "This is a note.";
	private String caller = "caller";
	private String subject = "food";
	private String owner = "me";
	private ArrayList<String> notes = new ArrayList<String>();
	/**
	 * Tests the Resolved state transitions.
	 */
	@Test
	public void testResolvedState() {
		notes.add(note);
		
		Ticket tic1 = new Ticket(123, Ticket.RESOLVED_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.RC_COMPLETED, notes);		
 		Command command1 = new Command(CommandValue.FEEDBACK, owner, FeedbackCode.AWAITING_CALLER, ResolutionCode.COMPLETED, null, note);		
 		tic1.update(command1);		
 		assertEquals(tic1.getState(), Ticket.FEEDBACK_NAME);	
 		
 		Ticket tic2 = new Ticket(123, Ticket.RESOLVED_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.RC_COMPLETED, notes);		
 		Command command2 = new Command(CommandValue.FEEDBACK, owner, FeedbackCode.AWAITING_CALLER, ResolutionCode.COMPLETED, null, note);		
 		tic2.update(command2);		
 		assertEquals(tic2.getState(), Ticket.FEEDBACK_NAME);
 		
 		Ticket tic3 = new Ticket(123, Ticket.RESOLVED_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.RC_COMPLETED, notes);		
 		Command command3 = new Command(CommandValue.CONFIRM, owner, null, ResolutionCode.COMPLETED, null, note);		
 		tic3.update(command3);		
 		assertEquals(tic3.getState(), Ticket.CLOSED_NAME);
 		
 		Ticket tic4 = new Ticket(123, Ticket.RESOLVED_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.RC_COMPLETED, notes);
		Command command4 = new Command(CommandValue.REOPEN, owner, null, ResolutionCode.WORKAROUND, null, note);
		try {
			tic4.update(command4);
		}
		catch(UnsupportedOperationException e) {
			assertEquals(tic4.getState(), Ticket.RESOLVED_NAME);
 		
		}
		
		Ticket tic5 = new Ticket(123, Ticket.RESOLVED_NAME, Ticket.TT_INCIDENT, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.RC_SOLVED, notes);
		Command command5 = new Command(CommandValue.REOPEN, owner, null, ResolutionCode.COMPLETED, null, note);
		try {
			tic5.update(command5);
		}
		catch(UnsupportedOperationException e) {
			assertEquals(tic5.getState(), Ticket.RESOLVED_NAME);
 		
		}

	}
	/**
	 * Tests the Closed state transitions.
	 */
	@Test
	public void testClosedState() {
		notes.add(note);
		
		Ticket tic2 = new Ticket(123, Ticket.CLOSED_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.RC_COMPLETED, notes);
		assertEquals(tic2.getState(), Ticket.CLOSED_NAME);
		Command command2 = new Command(CommandValue.REOPEN, owner, null, null, null, note);
		tic2.update(command2);
		assertEquals(tic2.getState(), Ticket.WORKING_NAME);
	}	
	/**
	 * Tests the New state transitions.
	 */
	@Test
	public void testNewState() {
		notes.add(note);
		
		Ticket tic3 = new Ticket(123, Ticket.NEW_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, null, notes);
		Command command3 = new Command(CommandValue.PROCESS, owner, null, null, null, note);
		tic3.update(command3);
		assertEquals(tic3.getState(), Ticket.WORKING_NAME);
		
		Ticket tic8 = new Ticket(123, Ticket.NEW_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, null, notes);
		Command command8 = new Command(CommandValue.CANCEL, owner, null, null, CancellationCode.DUPLICATE, note);
		tic8.update(command8);
		assertEquals(tic8.getState(), Ticket.CANCELED_NAME);
		
	}
	/**
	 * Tests the Feedback state transitions.
	 */
	@Test
	public void testFeedbackState() {
		notes.add(note);
		
		Ticket tic11 = new Ticket(123, Ticket.FEEDBACK_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.F_CALLER, notes);		
 		Command command11 = new Command(CommandValue.REOPEN, owner, null, ResolutionCode.CALLER_CLOSED, null, note);		
 		tic11.update(command11);		
 		assertEquals(tic11.getState(), Ticket.WORKING_NAME);
 		
 		Ticket tic9 = new Ticket(123, Ticket.FEEDBACK_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.F_CALLER, notes);
		Command command9 = new Command(CommandValue.CANCEL, owner, null, null, CancellationCode.INAPPROPRIATE, note);
		tic9.update(command9);
		assertEquals(tic9.getState(), Ticket.CANCELED_NAME);
		
		Ticket tic3 = new Ticket(123, Ticket.FEEDBACK_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.F_CALLER, notes);
		Command command3 = new Command(CommandValue.RESOLVE, owner, null, ResolutionCode.NOT_SOLVED, null, note);
		try {
			tic3.update(command3);
		}
		catch(UnsupportedOperationException e) {
			assertEquals(tic3.getState(), Ticket.FEEDBACK_NAME);
		}
		
		Ticket tic4 = new Ticket(123, Ticket.FEEDBACK_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.F_CALLER, notes);
		Command command4 = new Command(CommandValue.REOPEN, owner, null, null, null, note);
		tic4.update(command4);
		assertEquals(tic4.getState(), Ticket.WORKING_NAME);
		
		Ticket tic5 = new Ticket(123, Ticket.FEEDBACK_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.F_CALLER, notes);
		Command command5 = new Command(CommandValue.RESOLVE, owner, null, ResolutionCode.NOT_COMPLETED, null, note);
		tic5.update(command5);
		assertEquals(tic5.getState(), Ticket.RESOLVED_NAME);
	}
	/**
	 * Tests the working state transitions.
	 */
	@Test
	public void testWorkingState() {
		notes.add(note);
		
		Ticket tic6 = new Ticket(123, Ticket.WORKING_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.F_CALLER, notes);
		Command command6 = new Command(CommandValue.FEEDBACK, owner, FeedbackCode.AWAITING_CALLER, null, null, note);
		tic6.update(command6);
		assertEquals(tic6.getState(), Ticket.FEEDBACK_NAME);
		
		Ticket tic7 = new Ticket(123, Ticket.WORKING_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, null, notes);
		Command command7 = new Command(CommandValue.CANCEL, owner, null, null, CancellationCode.DUPLICATE, note);
		tic7.update(command7);
		assertEquals(tic7.getState(), Ticket.CANCELED_NAME);
		
		Ticket tic3 = new Ticket(123, Ticket.WORKING_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, null, notes);
		Command command3 = new Command(CommandValue.RESOLVE, owner, null, ResolutionCode.COMPLETED, null, note);
		tic3.update(command3);
		assertEquals(tic3.getState(), Ticket.RESOLVED_NAME);
		
	}
	/**
	 * Tests the Cancellation state transitions.
	 */
	@Test
	public void testCancellationState() {
		notes.add(note);
		
		Ticket tic5 = new Ticket(123, Ticket.CANCELED_NAME, Ticket.TT_REQUEST, subject, caller, Ticket.C_SOFTWARE, Ticket.P_HIGH, owner, Command.CC_DUPLICATE, notes);
		Command command5 = new Command(CommandValue.RESOLVE, owner, null, ResolutionCode.CALLER_CLOSED, null, note);
		try {
			tic5.update(command5);
		}
		catch(UnsupportedOperationException e){
			assertEquals(tic5.getState(), Ticket.CANCELED_NAME);
		}
	}
}
