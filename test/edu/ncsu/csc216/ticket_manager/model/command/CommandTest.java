package edu.ncsu.csc216.ticket_manager.model.command;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.ticket_manager.model.command.Command.CancellationCode;
import edu.ncsu.csc216.ticket_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.ticket_manager.model.command.Command.FeedbackCode;
import edu.ncsu.csc216.ticket_manager.model.command.Command.ResolutionCode;

/**
 * Tests the Command class in Project1.
 * 
 * @author Isabella Samuelsson
 */
public class CommandTest {	
	String ownerId = "insam";
	String note = "This is a note.";
	/**
	 * Tests the getCommand method.
	 */
	@Test
	public void testgetCommand() {
		Command command = new Command(CommandValue.PROCESS, ownerId, null, null, null, note);
		assertEquals(command.getCommand(), CommandValue.PROCESS);
	}
	/**
	 * Tests the getOwnerId method.
	 */
	@Test
	public void testgetOwnerId() {
		Command command = new Command(CommandValue.FEEDBACK, ownerId, FeedbackCode.AWAITING_CALLER, null, null, note);
		assertEquals(command.getOwnerId(), ownerId);
	}
	/**
	 * Tests the getFeedbackCode method.
	 */
	@Test
	public void testgetFeedbackCode() {
		Command command = new Command(CommandValue.FEEDBACK, ownerId, FeedbackCode.AWAITING_CALLER, null, null, note);
		assertEquals(command.getFeedbackCode(), FeedbackCode.AWAITING_CALLER);
	}
	/**
	 * Tests the getResolutionCode method.
	 */
	@Test
	public void testgetResolutionCode() {
		Command command = new Command(CommandValue.RESOLVE, ownerId, null, ResolutionCode.WORKAROUND, null, note);
		assertEquals(command.getResolutionCode(), ResolutionCode.WORKAROUND);
	}
	/**
	 * Tests the getCancellationCode method.
	 */
	@Test
	public void testgetCancellationCode() {
		Command command = new Command(CommandValue.CANCEL, ownerId, null, null, CancellationCode.DUPLICATE, note);
		assertEquals(command.getCancellationCode(), CancellationCode.DUPLICATE);
	}
	/**
	 * Tests the getNote method.
	 */
	@Test
	public void testgetNote() {
		Command command = new Command(CommandValue.RESOLVE, ownerId, null, ResolutionCode.WORKAROUND, null, note);
		assertEquals(command.getNote(), note);
	}
	/**
	 * Tests creating multiple invalid commands.
	 */
	@Test
	public void testInvalidCommand() {
		Command command = null;
		try {
			command = new Command(null, ownerId, null, ResolutionCode.WORKAROUND, null, note);
		}
		catch(IllegalArgumentException e) {
			assertNull(command);
		}
		try {
			command = new Command(CommandValue.PROCESS, "", null, ResolutionCode.WORKAROUND, null, note);
		}
		catch(IllegalArgumentException e) {
			assertNull(command);
		}
		try {
			command = new Command(CommandValue.FEEDBACK, "", null, ResolutionCode.WORKAROUND, null, note);
		}
		catch(IllegalArgumentException e) {
			assertNull(command);
		}
	}

}
