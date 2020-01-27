package edu.ncsu.csc216.ticket_manager.model.command;

/**
 * Creates objects that encapsulate user actions that cause the state of a Ticket to update.
 * Also handles cancellation, feedback and resolution codes.
 *  
 * @author Isabella Samuelsson 
 */
public class Command {

	/** Feedback Code Awaiting Caller */
	public static final String F_CALLER = "Awaiting Caller";
	/** Feedback Code Awaiting Change */
	public static final String F_CHANGE = "Awaiting Change";
	/** Feedback Code Awaiting Provider */
	public static final String F_PROVIDER = "Awaiting Provider";
	/** Resolution Code Completed */
	public static final String RC_COMPLETED = "Completed";
	/** Resolution Code Not Completed */
	public static final String RC_NOT_COMPLETED = "Not Completed";
	/** Resolution Code Solved */
	public static final String RC_SOLVED = "Solved";
	/** Resolution Code Workaround */
	public static final String RC_WORKAROUND = "Workaround";
	/** Resolution Code Not Solved */
	public static final String RC_NOT_SOLVED = "Not Solved";
	/** Resolution Code Caller Closed */
	public static final String RC_CALLER_CLOSED = "Caller Closed";
	/** Cancellation Code Duplicate */
	public static final String CC_DUPLICATE = "Duplicate";
	/** Cancellation Code Inappropriate */
	public static final String CC_INAPPROPRIATE = "Inappropriate";
	
	/** Id of the IT person working on the Ticket */
	private String ownerId;
	/** Comment on what was accomplished */
	private String note;
	
	/** Command value of the ticket */
	private CommandValue commandValue;
	/** Ticket feedback code */
	private FeedbackCode feedbackCode;
	/** Ticket resolution code */
	private ResolutionCode resolutionCode;
	/** Ticket cancellation code */
	private CancellationCode cancellationCode;
	
	
	
	
	/**
	 * Creates a Command object that serves as a user action to transition a 
	 * Ticket to another state.
	 * 
	 * @param commandValue what state transition should occur
	 * @param ownerId the Id of the IT person working on the Ticket
	 * @param feedbackCode the Feedback Code of the Ticket
	 * @param resolutionCode the Resolution Code of the Ticket
	 * @param cancellationCode the Cancellation Code of the Ticket
	 * @param note of what was accomplished by the owner
	 */
	public Command(CommandValue commandValue, String ownerId, FeedbackCode feedbackCode, ResolutionCode resolutionCode, CancellationCode cancellationCode, String note) {
		
		if(commandValue == null) {
			throw new IllegalArgumentException();
		}
		if(commandValue.equals(CommandValue.PROCESS) &&  (ownerId == null || ownerId.equals(""))) {
			throw new IllegalArgumentException();
		}
		if(commandValue.equals(CommandValue.FEEDBACK) && feedbackCode == null) {
			throw new IllegalArgumentException();
		}
		if(commandValue.equals(CommandValue.RESOLVE) && resolutionCode == null) {
			throw new IllegalArgumentException();
		}
		if(commandValue.equals(CommandValue.CANCEL) && cancellationCode == null) {
			throw new IllegalArgumentException();
		}
		if(note == null || note.equals("")) {
			throw new IllegalArgumentException();
		}
		
		this.ownerId = ownerId;
		this.note = note;
		this.commandValue = commandValue;
		this.feedbackCode = feedbackCode;
		this.resolutionCode = resolutionCode;
		this.cancellationCode = cancellationCode;	
	}
	/**
	 * Obtains the CommandValue of a ticket.
	 * 
	 * @return the state transition should occur
	 */
	public CommandValue getCommand() {
		return commandValue;
	}
	/**
	 * Obtains the Id of the IT person who is working on the Ticket.
	 * 
	 * @return the owners' Id
	 */
	public String getOwnerId() {
		return ownerId;
	}
	/**
	 * Obtains the Resolution Code of a Ticket.
	 * 
	 * @return the Resolution Code of the Ticket
	 */
	public ResolutionCode getResolutionCode() {
		return resolutionCode;
	}
	/**
	 * Obtains the note that was written for a Ticket.
	 * 
	 * @return note that was written for the Ticket 
	 */
	public String getNote() {
		return note;
	}
	/**
	 * Obtains the Feedback Code of a Ticket.
	 * 
	 * @return the Feedback Code of the Ticket
	 */
	public FeedbackCode getFeedbackCode() {
		return feedbackCode;
	}
	/**
	 * Obtains the Cancellation Code of a Ticket.
	 * 
	 * @return the Cancellation Code of the Ticket
	 */
	public CancellationCode getCancellationCode() {
		return cancellationCode;
	}
	
	
	/**
	 * The different types of command.
	 * 
	 * @author Isabella Samuelsson
	 */
	public enum CommandValue { PROCESS, FEEDBACK, RESOLVE, CONFIRM, REOPEN, CANCEL }
	/**
	 * The different types of Feedback codes.
	 * 
	 * @author Isabella Samuelsson
	 */
	public enum FeedbackCode { AWAITING_CALLER, AWAITING_CHANGE, AWAITING_PROVIDER }
	/**
	 * The different types of Resolution codes.
	 * 
	 * @author Isabella Samuelsson
	 */
	public enum ResolutionCode { COMPLETED, NOT_COMPLETED, SOLVED, WORKAROUND, NOT_SOLVED, CALLER_CLOSED }
	/**
	 * The different types of Cancellation codes.
	 * 
	 * @author Isabella Samuelsson
	 */
	public enum CancellationCode { DUPLICATE, INAPPROPRIATE }

}
