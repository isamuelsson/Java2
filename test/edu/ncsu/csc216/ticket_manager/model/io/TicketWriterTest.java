package edu.ncsu.csc216.ticket_manager.model.io;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket;

/**
 * Tests the TicketWriterTest class in Project1.  Source of checkFiles method is the PackScheduler labs.
 * 
 * @author Isabella Samuelsson
 */
public class TicketWriterTest {

	/**
	 * Tests writeCourseRecords()
	 */
	@Test
	public void testWriteCourseRecords() {
		ArrayList<Ticket> tickets = TicketReader.readTicketFile("test-files/ticket1.txt");
		String filename = "test-files/actual_ticket_records.txt";
		
		try {
			TicketWriter.writeTicketFile(filename, tickets);
		} catch (Exception e) {
			fail("Cannot write to course records file");
		}
		
		checkFiles("test-files/ticket1.txt", "test-files/actual_ticket_records.txt");
		
		try {
			TicketWriter.writeTicketFile("", null);
		}
		catch(Exception e) {
			//caught invalid parameters
		}
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new File (expFile));
			Scanner actScanner = new Scanner(new File(actFile));
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
