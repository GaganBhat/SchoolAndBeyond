package labs.replit.stackbonus;
/**
 * The Main method tests the unique StackBonus methods
 * reverse, multipop and undo by creating an instance of a
 * StackBonus and providing it with sample data and printing
 * out the effect on the stack after each method.
 *
 * @author Gagan Bhat
 * Collaborators: None
 * Teacher Name: Mrs. Ishman
 * Period: 3
 * Due Date: 4/6/2020
 */

public class Main
{
    public static void main(String[] args)
    {
        // Create the bonus stack
        StackBonus<Integer> extraStack = new StackBonus<Integer>();

        // Write a loop to fill it with the lowest 15 positive odd numbers
        for (int i = 1; i < 30; i += 2)
            extraStack.push(i);
        // Display the stack
        System.out.println("filled:             " + extraStack);


        // Multipop 3 items
        extraStack.multiPop(3);
        // Display the stack
        System.out.println("after multipop:     " + extraStack);


        // Undo the 3rd item (remove it) - remember, it's not at index 3.
        extraStack.undo(2);
        // Display the stack
        System.out.println("after undo index 2: " + extraStack);


        // Add two more numbers
        extraStack.push(100);
        extraStack.push(200);
        // Use the undo method to remove the last number added
        extraStack.undo(extraStack.size() - 1);
        // Display the stack
        System.out.println("after undo last:    " + extraStack);


        // Reverse the stack and store the resulting stack in extraStack
        extraStack = extraStack.reverse();
        // Display the stack
        System.out.println("after reverse:      " + extraStack);

    }
}

