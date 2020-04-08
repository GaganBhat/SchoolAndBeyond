package labs.replit.runtimelab;
/**
 *  The SimpleRuntimeMachine class contains the methods
 *  required to sequentially process the custom low level programming
 *  language that was created by teachers in Plano. It contains methods
 *  for printing, adding, copying and can be used in conjunction of one
 *  another to create one line commands that can perform multiple
 *  additions.
 *
 * @author Gagan Bhat
 * Collaborators: None
 * Teacher Name: Mrs. Ishman
 * Period: 3
 * Due Date: 4/9/2020
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
		while (fileReader.hasNextLine()) {
			Scanner commandReader = new Scanner(fileReader.nextLine());

			switch (commandReader.next()) {
				case "begin":
					programStack.add("begin");
					System.out.println("Starting the program...");
					break;
				case "end":
					programStack.pop();
					System.out.println("Stopping the program...");
					System.out.println("Runtime Machine DONE!");
					break;
				case "print":
					print(commandReader);
					break;
				case "copy":
					copyOp(commandReader);
					break;
			}

		}
	}

	/**
	 * print method handles the print command in this language.
	 * print displays information to the output window(System.out).
	 *
	 * @param input the scanner object going through your current command
	 */
	private void print(Scanner input) {
		programStack.add("print");

		while (input.hasNext()){
			String token = input.next();
			if(token.equalsIgnoreCase("add"))
				addOp(input);
			else
				programStack.push(token);
		}

		StringBuilder toPrint = new StringBuilder();
		while (!programStack.peek().equalsIgnoreCase("print")) {
			String popped = programStack.pop();
			if (isVariable(popped))
				toPrint.insert(0, getRAMValue(popped) + " ");
			else
				toPrint.insert(0, popped + " ");
		}
		toPrint = new StringBuilder(toPrint.toString().trim());

		System.out.println(toPrint);

		programStack.pop();
	}

	/**
	 * copyOp method handles the copy command in this language.
	 * copyOp copies an int value, constant or value stored in RAM,
	 * into RAM at a certain index.
	 *
	 * @param input the scanner object going through your current command
	 */
	private void copyOp(Scanner input) {
		programStack.add("copy");

		while (input.hasNext()){
			String token = input.next();
			if(token.equalsIgnoreCase("add"))
				addOp(input);
			else
				programStack.push(token);
		}

		while (!programStack.peek().equalsIgnoreCase("copy")){
			String tokenToAdd = programStack.pop();
			int valueToAddRam;
			if(isVariable(tokenToAdd))
				valueToAddRam = getRAMValue(tokenToAdd);
			else
				valueToAddRam = Integer.parseInt(tokenToAdd);

			String memLocal = programStack.pop();
			putVariable(memLocal, valueToAddRam);
		}

		programStack.pop();
	}

	/**
	 * addOp method handles the add command in this language.
	 *
	 * @param input the scanner object going through your current command
	 */
	private void addOp(Scanner input) {
		programStack.add("add");
		String firstToken = input.next();
		if(firstToken.equalsIgnoreCase("add"))
			addOp(input);
		else
			programStack.push(firstToken);

		String secondToken = input.next();
		if(secondToken.equalsIgnoreCase("add"))
			addOp(input);
		else
			programStack.push(secondToken);

		int val2;
		if (isVariable(programStack.peek()))
			val2 = getRAMValue(programStack.pop());
		else
			val2 = Integer.parseInt(programStack.pop());

		int val1;
		if (isVariable(programStack.peek()))
			val1 = getRAMValue(programStack.pop());
		else
			val1 = Integer.parseInt(programStack.pop());

		int sum = val1 + val2;

		programStack.pop();
		programStack.push(Integer.toString(sum));

	}

	/**
	 * Checks if the given reference token is a reference to variable in RAM.
	 * @param refWord token to check for reference
	 * @return true if it is a RAM variable, false if it is not.
	 */
	private boolean isVariable(String refWord){
		if(refWord.charAt(0) == 'i' && Character.isDigit(refWord.charAt(1)))
			return true;
		return false;
	}

	/**
	 * Retrieves the given RAM reference from ram and returns the value
	 * @param memVar reference to a RAM slot in ram array
	 * @return the value of reference in RAM
	 */
	private int getRAMValue(String memVar){
		return ram[Integer.parseInt(
				Character.toString(memVar.charAt(1)))];
	}

	/**
	 * Puts the given value into the correct reference RAM slot
	 * @param memVar location in RAM to put value in
	 * @param value value to put in given location in RAM
	 */
	private void putVariable(String memVar, int value){
		ram[Integer.parseInt(
				Character.toString(memVar.charAt(1)))]
				= value;
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