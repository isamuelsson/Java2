package edu.ncsu.csc216.ticket_manager.model.io;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket;

/**
 * Handles the input files for the ticket manager system. Creates ticket objects from a file of tickets.
 * 
 * @author Isabella Samuelsson
 */
public class TicketReader {	
	/**
	 * Reads Ticket records from a file and generates a list of valid Tickets. If the file to read cannot be found or the permissions are incorrect,
     * a File NotFoundException is thrown.
	 * 
	 * @param fileName the file of tickets that is read
	 * @return ticketList list of ticket objects created
	 */
	public static ArrayList<Ticket> readTicketFile(String fileName){
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		
		try {
			Scanner fileReader = new Scanner(new FileInputStream(fileName));
		    
		    ArrayList<String> notes = null;
		    String currentTicket = null;
		    
		    while (fileReader.hasNextLine()) {
		    	String token = fileReader.nextLine();
		        if(token.charAt(0) == '*') {
		        	if(notes != null && readTicket(currentTicket, notes) != null) {
		        		tickets.add(readTicket(currentTicket, notes));  
		        		notes = new ArrayList<String>(); 
		            }
		           	currentTicket = token;
		           	notes = new ArrayList<String>();
		           	
		        }
		        else if(token.charAt(0) == '-') {
		        	notes.add(token.substring(1));
		           	
		        }
		        else {
		        	String beginingNote = notes.get(notes.size() - 1);
		        	notes.remove(notes.size() - 1);
		        	notes.add(beginingNote + "\n" + token);
		        }
		    }
		    tickets.add(readTicket(currentTicket, notes));
		    fileReader.close();
		}
		catch(Exception e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
		   
	    return tickets;
	}
	
	
	/**
     * Reads the ticket as a line and return a Ticket object.
     * 
     * @param line input ticket string
     * @param notes list of ticket notes
     * @return the Ticket object constructed from the input string
     */
    private static Ticket readTicket(String line, ArrayList<String> notes) {
    	Scanner lineScanner = new Scanner(line);
    	lineScanner.useDelimiter("#");
    	Ticket ticket = null;
    	
    	int id;
    	String state;
		String ticketType;
		String subject;
		String caller;
		String category;
		String priority;
		String owner;
		String code = "";
		
		
		
    	try {
    		id = Integer.parseInt(lineScanner.next().substring(1));
    		state = lineScanner.next();
    		ticketType = lineScanner.next();
    		subject = lineScanner.next();
    		caller = lineScanner.next();
    		category = lineScanner.next();
    		priority = lineScanner.next();
    		owner = lineScanner.next();
    		if(lineScanner.hasNext()) {
    			code = lineScanner.next();
    		}
    		lineScanner.close();
    		
    		ticket = new Ticket(id, state, ticketType, subject, caller, category, priority, owner, code, notes);
    	} catch(IllegalArgumentException e) {
    		return null;
    	} 
    	
    	return ticket;
    }

}