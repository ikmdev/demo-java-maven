package dev.ikm.examples;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NexusTestDataFileTest {

	// Sample method to test (doubles the number)
	public int doubleNumber(int number) {
		return number * 2;
	}

	@Test
	public void testDoubleNumber() {

		// String userHomeM2Repository = System.getProperty("user.home") + "\\.m2\\repository";

		String csvFilePath = System.getenv("CSV_FILE_PATH");
		System.out.println("CSV_FILE_PATH" + userHomeM2Repository);

		// Path to the CSV file in the target/resources directory
		//String csvFilePath = userHomeM2Repository + "\\dev\\ikm\\data\\test_data\\1.0\\test_data-1.0-dev.ikm.data.csv";
		// String csvFilePath = "home/runner+ "/test_data-1.0-dev.ikm.data.csv";

		try (CSVReader csvReader = new CSVReader(new FileReader(csvFilePath))) {

			String[] headers = csvReader.readNext(); // Read header row

			// Read each row of the CSV
			String[] row;

			while ((row = csvReader.readNext()) != null) {
				String input = row[0]; // First column: input
				String expected = row[1]; // Second column: expected value

				assertEquals(doubleNumber(Integer.parseInt(input)), Integer.parseInt(expected));

				// Print or use the values as needed
				System.out.println("Input: " + input + ", Expected: " + expected);
			}
		} catch (IOException | CsvValidationException e) {
			e.printStackTrace();
		}
	}
}
