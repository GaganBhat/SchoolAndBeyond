package labs.replit.runtimelab;/*
Your heading goes here...
*/

import java.util.*;
import java.io.*;

/**
 * SimpleRuntimeMachine excutes programs in a custom programming language.
 * This object will have an array of length 10 to represent the state of RAM.
 * It will also have a Stack of strings acting as the program stack.
 * Commands will be pushed and popped off the stack as the program executes.
 */
public class SimpleRuntimeMachine {
	// These are your ONLY instance variables!
	// DO NOT USE ANY OTHER DATA STRUCTURES IN THIS LAB!
	private final int RAM_CAPACITY = 10;
	private int[] ram;
	private Stack<String> programStack;

	/**
	 * This Construtor takes in a file name representing a program.
	 * The construtor will then create a connection to the file using Scanner
	 * and process the commands one line at a time.
	 *
	 * @param fileName the name of a file, representing the program to execute
	 */
	public SimpleRuntimeMachine(String fileName) {
		ram = new int[RAM_CAPACITY];
		programStack = new Stack<String>();
		processCommands(fileName);
	}

	/**
	 * processCommands method will process commands of the program
	 * one line at a time, calling other methods as needed.
	 *
	 * @param fileName the name of a file, representing the program to execute
	 */
	public void processCommands(String fileName) {
		Scanner fileReader = null;
		try {
			fileReader = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
          e.printStackTrace();
        }

		assert fileReader != null;
		while (fileReader.hasNextLine()){
			Scanner commandReader = new Scanner(fileReader.nextLine());

			switch (commandReader.next()){
				case "begin":
					programStack.add("begin");
					System.out.println("Starting the program...");
					programStack.pop();
					break;
				case "end":
					programStack.add("end");
					System.out.println("Stopping the program...");
					System.out.println("Runtime Machine DONE!");
					programStack.pop();
					break;
				case "print":
					programStack.add("print");
					print(commandReader);
					programStack.pop();
					break;
				case "copy":
					programStack.add("copy");
					copyOp(commandReader);
					programStack.pop();
					break;
			}

		}

		// complete the processCommands method...
	}

	/**
	 * print method handles the print command in this language.
	 * print displays information to the output window(System.out).
	 *
	 * @param input the scanner object going through your current command
	 */
	private void print(Scanner input) {
		String referenceWord = input.next();
		if(referenceWord.equalsIgnoreCase("add"))
			addOp(input);
		else if(isVariable(referenceWord))
			System.out.println(ram[Integer.parseInt(
					Character.toString(referenceWord.charAt(1)))]);
		else
			if(input.hasNext())
				System.out.println(referenceWord + input.nextLine());
			else
				System.out.println(referenceWord);

	}

	private boolean isVariable(String refWord){
		if(refWord.charAt(0) == 'i' && Character.isDigit(refWord.charAt(1)))
			return true;
		return false;
	}

	/**
	 * copyOp method handles the copy command in this language.
	 * copyOp copies an int value, constant or value stored in RAM,
	 * into RAM at a certain index.
	 *
	 * @param input the scanner object going through your current command
	 */
	private void copyOp(Scanner input) {
		// complete the copyOp method...
	}

	/**
	 * addOp method handles the add command in this language.
	 *
	 * @param input the scanner object going through your current command
	 */
	private void addOp(Scanner input) {
		// complete the addOp method...
	}

	/**
	 * The toString method returns the state of RAM for testing purposes.
	 *
	 * @return the current state of RAM
	 */
	public String toString() {
		return Arrays.toString(ram);
	}
}