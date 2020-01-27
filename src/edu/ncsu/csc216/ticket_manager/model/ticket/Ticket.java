package edu.ncsu.csc216.ticket_manager.model.ticket;

import java.util.ArrayList;

import edu.ncsu.csc216.ticket_manager.model.command.Command;
import edu.ncsu.csc216.ticket_manager.model.command.Command.CancellationCode;
import edu.ncsu.csc216.ticket_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.ticket_manager.model.command.Command.FeedbackCode;
import edu.ncsu.csc216.ticket_manager.model.command.Command.ResolutionCode;

/**
 * Creates a Ticket object which holds information for a Ticket, subject, caller, owner, and notes, and also the classes for Ticket Category, Priority and TicketType. 
 * This class also holds the abstract states of a Ticket object, NewState, FeedbackState, WorkingState, CanceledState, ResolvedState and ClosedState. 
 * 
 * @author Isabella Samuelsson
 */
public class Ticket {
	
	/** Request TicketType */
	public static final String TT_REQUEST = "Request";
	/** Incident TicketType */
	public static final String TT_INCIDENT = "Incident";
	/** Category Inquiry */
	public static final String C_INQUIRY = "Inquiry";
	/** Category Software */
	public static final String C_SOFTWARE = "Software";
	/** Category Hardware */
	public static final String C_HARDWARE = "Hardware";
	/** Category Network */
	public static final String C_NETWORK = "Network";
	/** Category Database */
	public static final String C_DATABASE = "Database";
	/** Priority Urgent */
	public static final String P_URGENT = "Urgent";
	/** Priority High */
	public static final String P_HIGH = "High";
	/** Priority Medium */
	public static final String P_MEDIUM = "Medium";
	/** Priority Low */
	public static final String P_LOW = "Low";
	/** State name New */
	public static final String NEW_NAME = "New";
	/** State name Working */
	public static final String WORKING_NAME  = "Working";
	/** State name Feedback */
	public static final String FEEDBACK_NAME = "Feedback";
	/** State name Resolved */
	public static final String RESOLVED_NAME = "Resolved";
	/** State name Closed */
	public static final String CLOSED_NAME = "Closed";
	/** State name Canceled */
	public static final String CANCELED_NAME = "Canceled";
	
	/** keeps track of the id that should be given to the next ticket */
	private static int counter = 1;
	/** Ticket Id*/
	private int ticketId;
	/** Ticket subject */
	private String subject;
	/** Ticket caller */
	private String caller;
	/** The IT person who handles the Ticket */
	private String owner;
	/** Notes on what has been done to the Ticket */
	private ArrayList<String> notes;
	
	private FeedbackCode feedbackCode;
	private ResolutionCode resolutionCode;
	private CancellationCode cancellationCode;
	
	private TicketState state;
	private Priority priority;
	private TicketType ticketType;
	private Category category;
	
	private final TicketState newState = new NewState();             
	private final TicketState workingState = new WorkingState();
	private final TicketState feedbackState = new FeedbackState();
	private final TicketState resolvedState = new ResolvedState();
	private final TicketState closedState = new ClosedState();
	private final TicketState canceledState = new CanceledState();
	
	
	
	
	
	
	
	
	
	/**
	 * Increments the counter by 1.
	 */
	public static void incrementCounter() {
		counter += 1;
	}
	/**
	 * Increments the counter by a specified number.
	 * 
	 * @param count the number the counter should be set to
	 */
	public static void setCounter(int count) {
		if(count <= 0) {
			throw new IllegalArgumentException("Ticket id must be a value greater than 0.");
		}
		Ticket.counter = count; 
	}
	/**
	 * Constructor for a new Ticket object, contains TicketType, 
	 * subject, caller, category, priority, notes.
	 * 
	 * @param ticketType the Tickets' type
	 * @param subject the subject of the Ticket
	 * @param caller the person who placed the Ticket
	 * @param category of the Ticket
	 * @param priority of the Ticket
	 * @param note a comment on what has been done to the Ticket
	 */
	public Ticket(TicketType ticketType, String subject, String caller, Category category, Priority priority, String note) {
		if(ticketType == null || subject == null || subject.equals("") || caller == null || caller.equals("") || category == null || priority == null || note == null || note.equals("")) {
			throw new IllegalArgumentException();
		}
		owner = "";
		ticketId = Ticket.counter;
		incrementCounter();	
		setSubject(subject);
		setCaller(caller);
		notes = new ArrayList<String>();
		notes.add(note);
		
		this.category = category;
		this.priority = priority;
		this.ticketType = ticketType;
		
		feedbackCode = null;
		resolutionCode = null;
		cancellationCode = null;
		
		state = newState;
		
		
	}
	/**
	 * Constructor of Ticket objects from a Ticket file, contains Ticket id, state, TicketType, 
	 * subject, caller, category, priority, owner, code and notes.
	 * 
	 * @param ticketId the id of the Ticket
	 * @param state the state of the Ticket
	 * @param ticketType the Tickets' type
	 * @param subject the subject of the Ticket
	 * @param caller the person who placed the Ticket
	 * @param category of the Ticket
	 * @param priority of the Ticket              
	 * @param owner the IT person who is working on the Ticket
	 * @param code the number given to the ticket
	 * @param notes a comment on what has been done to the Ticket
	 */
	public Ticket(int ticketId, String state, String ticketType, String subject, String caller, String category, String priority, String owner, String code, ArrayList<String> notes) {
		if(ticketId < 0 || subject.length() < 1 || caller.length() < 1) {
			throw new IllegalArgumentException();
		}
		if((state.equals("Working") || state.equals("Feedback") || state.equals("Resolved") || state.equals("Closed")) && owner.equals("")) { //should move to owner?
			throw new IllegalArgumentException();
		}	
													//something more with notes in UC1?
		this.ticketId = ticketId; 
		if(ticketId > counter) {
			setCounter(ticketId + 1);
		}	
		setState(state);
		setTicketType(ticketType);
		setSubject(subject);
		setCaller(caller);
		setCategory(category);
		setPriority(priority);
		setOwner(owner);
		this.notes = notes;
		
		feedbackCode = null;
		resolutionCode = null;
		cancellationCode = null;
		
		if(state.equals("Feedback")) {
			setFeedbackCode(code);
		}
		if(state.equals("Canceled")) {
			setCancellationCode(code);
		}
		if(state.equals("Resolved") || state.equals("Closed")) {
			setResolutionCode(code);
		}
			
	}
	/**
	 * Obtains the caller who created the Ticket.
	 * 
	 * @return the person who created the Ticket
	 */
	public String getCaller() {
		return caller;
	}
	/**
	 * Obtains the cancellation code of a Ticket
	 * 
	 * @return the cancellation code of a Ticket
	 */
	public String getCancellationCode() {
		if(cancellationCode == null) {
			return null;
		}
		if(cancellationCode.equals(CancellationCode.INAPPROPRIATE)) {
			return "Inappropriate";
		}
		if(cancellationCode.equals(CancellationCode.DUPLICATE)) {
			return "Duplicate";
		}
		return "";
	}
	/**
	 * Obtains the category of a Ticket.
	 * 
	 * @return the category of a Ticket
	 */
	public String getCategory() {
		if(category.equals(Category.DATABASE)) {
			return C_DATABASE;
		}
		if(category.equals(Category.HARDWARE)) {
			return C_HARDWARE;
		}
		if(category.equals(Category.INQUIRY)) {
			return C_INQUIRY;
		}
		if(category.equals(Category.NETWORK)) {
			return C_NETWORK;
		}
		if(category.equals(Category.SOFTWARE)) {
			return C_SOFTWARE;
		}
		return "";
	}
	/**
	 * Obtains a Tickets' feedback code.
	 * 
	 * @return Ticket feedback code
	 */
	public String getFeedbackCode() {
		if(feedbackCode == null) {
			return null;
		}
		if(feedbackCode.equals(FeedbackCode.AWAITING_CALLER)) {
			return Command.F_CALLER;
		}
		if(feedbackCode.equals(FeedbackCode.AWAITING_CHANGE)) {
			return Command.F_CHANGE;
		}
		if(feedbackCode.equals(FeedbackCode.AWAITING_PROVIDER)) {
			return Command.F_PROVIDER;
		}
		return "";
	}
	/**
	 * Obtains a Tickets notes.
	 * 
	 * @return notes on what has been done to resolve the Ticket
	 */
	public String getNotes() {                       //check if correct format
		String noteString = "";
		for(int i = 0; i < notes.size(); i++) {	
			noteString += "-" + notes.get(i) + "\n";	
		}
		return noteString;
	}
	/**
	 * Obtains the person who created the Ticket.
	 * 
	 * @return owner of the Ticket
	 */
	public String getOwner() {
		return owner;
	}
	/**
	 * Obtains the priority of the Ticket.
	 * 
	 * @return the priority of the Ticket
	 */
	public String getPriority() {
		if(priority.equals(Priority.LOW)) {
			return P_LOW;
		}
		if(priority.equals(Priority.MEDIUM)) {
			return P_MEDIUM;
		}
		if(priority.equals(Priority.HIGH)) {
			return P_HIGH;
		}
		if(priority.equals(Priority.URGENT)) {
			return P_URGENT;
		}
		return "";
	}
	/**
	 * Obtains a Tickets resolution code.
	 * 
	 * @return a Tickets resolution code
	 */
	public String getResolutionCode() {
		if(resolutionCode == null) {
			return null;
		}
		if(resolutionCode.equals(ResolutionCode.COMPLETED)) {
			return Command.RC_COMPLETED;
		}
		if(resolutionCode.equals(ResolutionCode.NOT_COMPLETED)) {
			return Command.RC_NOT_COMPLETED;
		}
		if(resolutionCode.equals(ResolutionCode.SOLVED)) {
			return Command.RC_SOLVED;
		}
		if(resolutionCode.equals(ResolutionCode.NOT_SOLVED)) {
			return Command.RC_NOT_SOLVED;
		}
		if(resolutionCode.equals(ResolutionCode.WORKAROUND)) {
			return Command.RC_WORKAROUND;
		}
		if(resolutionCode.equals(ResolutionCode.CALLER_CLOSED)) {
			return Command.RC_CALLER_CLOSED;
		}
		return "";
	}
	/**
	 * Obtains a Tickets state.
	 * 
	 * @return a Tickets state
	 */
	public String getState() {
		return state.getStateName();
	}
	/**
	 * Obtains a Tickets subject.
	 * 
	 * @return a Tickets subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * Obtains a Tickets Id.
	 * 
	 * @return a Tickets id
	 */
	public int getTicketId() {
		return ticketId;
	}
	/**
	 * Obtains a Tickets type.
	 * 
	 * @return a Tickets type
	 */
	public TicketType getTicketType() {
		return ticketType;
	}
	/**
	 * Obtains a Tickets type as a string.
	 * 
	 * @return a Tickets type
	 */
	public String getTicketTypeString() {
		if(ticketType.equals(TicketType.INCIDENT)) {
			return TT_INCIDENT;
		}
		if(ticketType.equals(TicketType.REQUEST)) {
			return TT_REQUEST;
		}
		return "";
	}
	
	
	
	
	
	/**
	 * Sets the Tickets caller.
	 * 
	 * @param caller the person who created the Ticket
	 */
	private void setCaller(String caller) {
		this.caller = caller;
	}
	/**
	 * Sets the Tickets cancellation code.
	 * 
	 * @param code the cancellation code
	 */
	private void setCancellationCode(String code) {
		if(!code.equals(Command.CC_DUPLICATE) && !code.equals(Command.CC_INAPPROPRIATE)) {
			throw new IllegalArgumentException();
		}
		if(code.equals(Command.CC_DUPLICATE)) {
			cancellationCode = CancellationCode.DUPLICATE;
		}
		if(code.equals(Command.CC_INAPPROPRIATE)) {
			cancellationCode = CancellationCode.INAPPROPRIATE;
		}
	}
	/**
	 * Sets the Tickets category.
	 * 
	 * @param category the Tickets category
	 */
	private void setCategory(String category) {
		if(!category.equals(C_INQUIRY) && !category.equals(C_SOFTWARE) && !category.equals(C_HARDWARE) && !category.equals(C_NETWORK) && !category.equals(C_DATABASE)) {
			throw new IllegalArgumentException();
		}
		if(category.equals(C_INQUIRY)) {
			this.category = Category.INQUIRY;
		}
		if(category.equals(C_SOFTWARE)) {
			this.category = Category.SOFTWARE;
		}
		if(category.equals(C_HARDWARE)) {
			this.category = Category.HARDWARE;
		}
		if(category.equals(C_NETWORK)) {
			this.category = Category.NETWORK;
		}
		if(category.equals(C_DATABASE)) {
			this.category = Category.DATABASE;
		}
	}
	/**
	 * Sets the Tickets owner.
	 * 
	 * @param owner the IT person who is working on the Ticket  
	 */
	private void setOwner(String owner) {    //should move some checks from constructor here??
		this.owner = owner;
	}
	/**
	 * Sets the Tickets feedback code.
	 * 
	 * @param code the feedback code
	 */
	private void setFeedbackCode(String code) {
		if(!code.equals(Command.F_CALLER) && !code.equals(Command.F_CHANGE) && !code.equals(Command.F_PROVIDER)) {
			throw new IllegalArgumentException();
		}
		if(code.equals(Command.F_CALLER)) {
			feedbackCode = FeedbackCode.AWAITING_CALLER;
		}
		if(code.equals(Command.F_CHANGE)) {
			feedbackCode = FeedbackCode.AWAITING_CHANGE;
		}
		if(code.equals(Command.F_PROVIDER)) {
			feedbackCode = FeedbackCode.AWAITING_PROVIDER;
		}
	}
	/**
	 * Sets the Tickets priority.
	 * 
	 * @param priority of the Ticket
	 */
	private void setPriority(String priority) {
		if(!priority.equals(P_URGENT) && !priority.equals(P_HIGH) && !priority.equals(P_MEDIUM) && !priority.equals(P_LOW)) {
			throw new IllegalArgumentException();
		}
		if(priority.equals(P_LOW)) {
			this.priority = Priority.LOW;
		}
		if(priority.equals(P_MEDIUM)) {
			this.priority = Priority.MEDIUM;
		}
		if(priority.equals(P_HIGH)) {
			this.priority = Priority.HIGH;
		}
		if(priority.equals(P_URGENT)) {
			this.priority = Priority.URGENT;
		}
	}
	/**
	 * Sets the Tickets resolution code.
	 * 
	 * @param code the resolution code
	 */
	private void setResolutionCode(String code) {
		if(!code.equals(Command.RC_COMPLETED) && !code.equals(Command.RC_NOT_COMPLETED) && !code.equals(Command.RC_SOLVED) && !code.equals(Command.RC_WORKAROUND) && !code.equals(Command.RC_NOT_SOLVED) && !code.equals(Command.RC_CALLER_CLOSED)) {
			throw new IllegalArgumentException();
		}
		if(ticketType == TicketType.INCIDENT && (!code.equals(Command.RC_SOLVED) && !code.equals(Command.RC_WORKAROUND) && !code.equals(Command.RC_NOT_SOLVED) && !code.equals(Command.RC_CALLER_CLOSED))) {
			throw new IllegalArgumentException();
		}
		if(ticketType == TicketType.REQUEST && (!code.equals(Command.RC_COMPLETED) && !code.equals(Command.RC_NOT_COMPLETED) && !code.equals(Command.RC_CALLER_CLOSED))) {
			throw new IllegalArgumentException();
		}
		
		if(code.equals(Command.RC_COMPLETED)) {
			resolutionCode = ResolutionCode.COMPLETED;
		}
		if(code.equals(Command.RC_NOT_COMPLETED)) {
			resolutionCode = ResolutionCode.NOT_COMPLETED;
		}
		if(code.equals(Command.RC_SOLVED)) {
			resolutionCode = ResolutionCode.SOLVED;
		}
		if(code.equals(Command.RC_WORKAROUND)) {
			resolutionCode = ResolutionCode.WORKAROUND;
		}
		if(code.equals(Command.RC_NOT_SOLVED)) {
			resolutionCode = ResolutionCode.NOT_SOLVED;
		}
		if(code.equals(Command.RC_CALLER_CLOSED)) {
			resolutionCode = ResolutionCode.CALLER_CLOSED;
		}
	}
	/**
	 * Sets the Tickets state.
	 * 
	 * @param state of the Ticket
	 */
	private void setState(String state) {
		if(!state.equals(NEW_NAME) && !state.equals(WORKING_NAME) && !state.equals(FEEDBACK_NAME) && !state.equals(RESOLVED_NAME) && !state.equals(CLOSED_NAME) && !state.equals(CANCELED_NAME)) {
			throw new IllegalArgumentException();
		}
		if(state.equals(NEW_NAME)) {
			this.state = newState;
		}
		if(state.equals(WORKING_NAME)) {
			this.state = workingState;
		}
		if(state.equals(FEEDBACK_NAME)) {
			this.state = feedbackState;
		}
		if(state.equals(RESOLVED_NAME)) {
			this.state = resolvedState;
		}
		if(state.equals(CLOSED_NAME)) {
			this.state = closedState;
		}
		if(state.equals(CANCELED_NAME)) {
			this.state = canceledState;
		}
	}
	/**
	 * Sets the Tickets subject.
	 * 
	 * @param subject of the Ticket
	 */
	private void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * Sets the Tickets type.
	 * 
	 * @param ticketType the Tickets type
	 */
	private void setTicketType(String ticketType) {
		if(ticketType == null) {
			throw new IllegalArgumentException();
		}
		if(!ticketType.equals(TT_REQUEST) && !ticketType.equals(TT_INCIDENT)) {
			throw new IllegalArgumentException();
		}
		if(ticketType.equals(TT_INCIDENT)) {
			this.ticketType = TicketType.INCIDENT;
		}
		if(ticketType.equals(TT_REQUEST)) {
			this.ticketType = TicketType.REQUEST;
		}
	}
	
	
	
	
	
	/**
	 * Creates a string representation of a Ticket.
	 * 
	 * @return a string representation of a Ticket
	 */
	public String toString() {
		String code = "";
		if(state instanceof ClosedState || state instanceof ResolvedState) {    //check if correct
			code = getResolutionCode();
		}
		if(state instanceof CanceledState) {
			code = getCancellationCode();
		}
		if(state instanceof FeedbackState) {
			code = getFeedbackCode();
		}
		String ticketString = "*" + getTicketId() + "#" + getState() + "#" + getTicketTypeString() + "#" + getSubject() + "#" + getCaller() + "#" + getCategory() + "#" + getPriority() + "#" + getOwner() + "#" + code + "\n";
		return ticketString + getNotes();
	}
	
	/**
	 * Updates a Ticket depending on the command.    
	 * 
	 * @param command the Ticket command
	 */
	public void update(Command command) throws UnsupportedOperationException {   // check if correct implem and if need to add more
		try {
			if(command == null || state == null) {
				throw new IllegalArgumentException();
			}
			state.updateState(command);
			if(command.getNote() != null) {
				notes.add(command.getNote());
			}
		}
		catch(IllegalArgumentException e) {
			throw new UnsupportedOperationException();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * The different Categories a Ticket can have.
	 * 
	 * @author Isabella Samuelsson
	 */
	public enum Category { INQUIRY, SOFTWARE, HARDWARE, NETWORK, DATABASE }
	/**
	 * The different Priorities a Ticket can have.
	 * 
	 * @author Isabella Samuelsson
	 */
	public enum Priority { URGENT, HIGH, MEDIUM, LOW }
	/**
	 * The different TicketTypes a Ticket can have.
	 * 
	 * @author Isabella Samuelsson
	 */
	public enum TicketType { REQUEST, INCIDENT }
	
	

		
		
	
	
	
		
	
	
	
	
	
	
	
	/**
	 * The Feedback Ticket state. 
	 * 
	 * @author Isabella Samuelsson
	 */
	private class FeedbackState implements TicketState {
		/**
		 * Obtains the Ticket state name.
		 * 
		 * @return the state name
		 */
		public String getStateName() {
			return FEEDBACK_NAME;
		}
		/**
		 * Updates the Tickets state.
		 * 
		 * @param update the command that specifies how to update the Ticket
		 */
		public void updateState(Command update) throws UnsupportedOperationException {
			if(update.getCommand().equals(CommandValue.REOPEN)) {
				state = workingState; 
				feedbackCode = null;
			}
			else if(update.getCommand().equals(CommandValue.CANCEL)) {
				state = canceledState;
				feedbackCode = null;
				resolutionCode = null;
				cancellationCode = update.getCancellationCode();
			}
			else if(update.getCommand().equals(CommandValue.RESOLVE)) {
				if(ticketType == TicketType.INCIDENT && (!update.getResolutionCode().equals(ResolutionCode.SOLVED) && !update.getResolutionCode().equals(ResolutionCode.WORKAROUND) && !update.getResolutionCode().equals(ResolutionCode.NOT_SOLVED) && !update.getResolutionCode().equals(ResolutionCode.CALLER_CLOSED))) {
					throw new UnsupportedOperationException();
				}
				if(ticketType == TicketType.REQUEST && (!update.getResolutionCode().equals(ResolutionCode.COMPLETED) && !update.getResolutionCode().equals(ResolutionCode.NOT_COMPLETED) && !update.getResolutionCode().equals(ResolutionCode.CALLER_CLOSED))) {
					throw new UnsupportedOperationException();
				}
				state = resolvedState;
				resolutionCode = update.getResolutionCode();
				feedbackCode = null;
			}
			else {
				throw new UnsupportedOperationException();
			}
		}
	}
	/**
	 * The Closed Ticket state. 
	 * 
	 * @author Isabella Samuelsson
	 */
	private class ClosedState implements TicketState {    //do need to remove other codes? also when do update owner id?
		/**
		 * Obtains the Ticket state name.
		 * 
		 * @return the state name
		 */
		public String getStateName() {
			return CLOSED_NAME;
		}
		/**
		 * Updates the Tickets state.
		 * 
		 * @param update the command that specifies how to update the Ticket
		 */
		public void updateState(Command update) throws UnsupportedOperationException {   
			if(update.getCommand().equals(CommandValue.REOPEN)) {
				state = workingState;
				resolutionCode = null;
			}
			else {
				throw new UnsupportedOperationException();
			}	
		}
	}
	/**
	 * The Resolved Ticket state. 
	 * 
	 * @author Isabella Samuelsson
	 */
	private class ResolvedState implements TicketState {
		/**
		 * Obtains the Ticket state name.
		 * 
		 * @return the state name
		 */
		public String getStateName() {
			return RESOLVED_NAME;
		}
		/**
		 * Updates the Tickets state.
		 * 
		 * @param update the command that specifies how to update the Ticket
		 */
		public void updateState(Command update) throws UnsupportedOperationException {
			if(ticketType == TicketType.INCIDENT && !(update.getResolutionCode() == null || update.getResolutionCode().equals(ResolutionCode.SOLVED) || update.getResolutionCode().equals(ResolutionCode.WORKAROUND) || update.getResolutionCode().equals(ResolutionCode.NOT_SOLVED) || update.getResolutionCode().equals(ResolutionCode.CALLER_CLOSED))) {
				throw new UnsupportedOperationException();
			}
			if(ticketType == TicketType.REQUEST && !(update.getResolutionCode() == null || update.getResolutionCode().equals(ResolutionCode.COMPLETED) || update.getResolutionCode().equals(ResolutionCode.NOT_COMPLETED) || update.getResolutionCode().equals(ResolutionCode.CALLER_CLOSED))) {
				throw new UnsupportedOperationException();
			}
			
			if(update.getCommand().equals(CommandValue.REOPEN)) {
				state = workingState;
				resolutionCode = null;
			}
			else if(update.getCommand().equals(CommandValue.CONFIRM)) {
				state = closedState;
			}
			else if(update.getCommand().equals(CommandValue.FEEDBACK)) {
				state = feedbackState;
				resolutionCode = null;
				feedbackCode = update.getFeedbackCode();
			}
			else {
				throw new UnsupportedOperationException();
			}
		}	
	}
	/**
	 * The New Ticket state. 
	 * 
	 * @author Isabella Samuelsson
	 */
	private class NewState implements TicketState {
		/**
		 * Obtains the Ticket state name.
		 * 
		 * @return the state name
		 */
		public String getStateName() {
			return NEW_NAME;
		}
		/**
		 * Updates the Tickets state.
		 * 
		 * @param update the command that specifies how to update the Ticket
		 */
		public void updateState(Command update) throws UnsupportedOperationException {
			if(update.getCommand().equals(CommandValue.PROCESS)) {
				state = workingState;
				owner = update.getOwnerId();
			}
			else if(update.getCommand().equals(CommandValue.CANCEL)) {
				state = canceledState;
				cancellationCode = update.getCancellationCode();
			}
			else {
				throw new UnsupportedOperationException();
			}
		}
	}
	/**
	 * The Working Ticket state. 
	 * 
	 * @author Isabella Samuelsson
	 */
	private class WorkingState implements TicketState {
		/**
		 * Obtains the Ticket state name.
		 * 
		 * @return the state name
		 */
		public String getStateName() {
			return WORKING_NAME;
		}
		/**
		 * Updates the Tickets state.
		 * 
		 * @param update the command that specifies how to update the Ticket
		 */
		public void updateState(Command update) throws UnsupportedOperationException {
			if(update.getCommand().equals(CommandValue.CANCEL)) {
				state = canceledState;
				cancellationCode = update.getCancellationCode();
			}
			else if(update.getCommand().equals(CommandValue.FEEDBACK)) {
				state = feedbackState;
				feedbackCode = update.getFeedbackCode();
			}
			else if(update.getCommand().equals(CommandValue.RESOLVE)) {
				if(ticketType == TicketType.INCIDENT && (!update.getResolutionCode().equals(ResolutionCode.SOLVED) && !update.getResolutionCode().equals(ResolutionCode.WORKAROUND) && !update.getResolutionCode().equals(ResolutionCode.NOT_SOLVED) && !update.getResolutionCode().equals(ResolutionCode.CALLER_CLOSED))) {
					throw new UnsupportedOperationException();
				}
				if(ticketType == TicketType.REQUEST && (!update.getResolutionCode().equals(ResolutionCode.COMPLETED) && !update.getResolutionCode().equals(ResolutionCode.NOT_COMPLETED) && !update.getResolutionCode().equals(ResolutionCode.CALLER_CLOSED))) {
					throw new UnsupportedOperationException();
				}
				state = resolvedState;
				resolutionCode = update.getResolutionCode();
			}
			else {
				throw new UnsupportedOperationException();
			}
		}
	}
	/**
	 * The Canceled Ticket state. 
	 * 
	 * @author Isabella Samuelsson
	 */
	private class CanceledState implements TicketState {
		/**
		 * Obtains the Ticket state name.
		 * 
		 * @return the state name
		 */
		public String getStateName() {
			return CANCELED_NAME;
		}
		/**
		 * Updates the Tickets state.
		 * 
		 * @param update the command that specifies how to update the Ticket
		 */
		public void updateState(Command update) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
	}
}
