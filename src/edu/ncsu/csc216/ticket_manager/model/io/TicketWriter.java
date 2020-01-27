package edu.ncsu.csc216.ticket_manager.model.io;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket;

/**
 * Handles the output files for the ticket manager system. Allows ticket objects to be written to a file.
 * 
 * @author Isabella Samuelsson
 */
public class TicketWriter {
	/**
	 * Takes a list of tickets as an input and writes them to a specified file. 
	 * If the file can not be written to, an IOException is thrown. 
	 * PTF assisted with printwriter/filewriter.
	 * 
	 * @param filename the file that is written to
	 * @param ticketList the list of tickets that is being recorded
	 */
	public static void writeTicketFile(String filename, List<Ticket> ticketList) {
		try {
			FileWriter fileWriter = new FileWriter(filename);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			
			for(int i = 0; i < ticketList.size(); i++) {
				
				printWriter.print(ticketList.get(i).toString());
			}
			fileWriter.close();
			printWriter.close();
		}
		catch(Exception e) {
			throw new IllegalArgumentException("Unable to save file");
		}
	}
}