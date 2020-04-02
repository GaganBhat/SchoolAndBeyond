package labs.replit.stackbonus;

/**
 *  Your heading goes here
 */

public class Main
{
    public static void main(String[] args)
    {
        // Create the bonus stack
        StackBonus<Integer> extraStack = new StackBonus<Integer>();

        // Write a loop to fill it with the lowest 15 positive odd numbers




        // Display the stack
        System.out.println("filled:             " + extraStack);


        // Multipop 3 items




        // Display the stack
        System.out.println("after multipop:     " + extraStack);


        // Undo the 3rd item (remove it) - remember, it's not at index 3.



        // Display the stack
        System.out.println("after undo index 2: " + extraStack);


        // Add two more numbers


        // Use the undo method to remove the last number added

        // Display the stack
        System.out.println("after undo last:    " + extraStack);


        // Reverse the stack and store the resulting stack in extraStack



        // Display the stack
        System.out.println("after reverse:      " + extraStack);

    }
}

