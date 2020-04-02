package labs.replit.stackbonus;
/**
 * The StackBonus class is a generic class that extends
 * the functionality of a typical Stack with methods
 * to help reverse the stack, undo previous actions
 * and pop multiple items in one single method call.
 *
 * @author Gagan Bhat
 * Collaborators: None
 * Teacher Name: Mrs. Ishman
 * Period: 3
 * Due Date: 4/6/2020
 */

import java.util.List;
import java.util.Stack;

public class StackBonus<E> extends Stack <E>
{
    /*NOTICE THE INHERITANCE, The stack will be constructed in the parent */
    /** Constructs this StackBonus object **/
    public StackBonus()
    {
    }

    /** multiPop will repetively remove data for a given number of times.
     * @param numItems the number of items to remove from the stack.
     */
    public void multiPop(int numItems)
    {
        if(numItems < 0)
            return;

        for(int i = 0; i < numItems; i++)
            this.pop();
    }


    /** reverse will create a new Stack containing the inverse order of
     * the current stack.
     * @return the Stack in reverse order
     * */
    public StackBonus<E> reverse()
    {
        StackBonus<E> reversedStack = new StackBonus<>();
        while (!this.isEmpty())
            reversedStack.push(this.pop());

        StackBonus<E> replica = (StackBonus<E>) reversedStack.clone();
        while (replica.isEmpty())
            this.push(replica.pop());

        return reversedStack;
    }

    /** undo will remove one item from the Stack for a given index.
     * @param index the index of the data to be removed.
     * @return false if the index is beyond the bounds of the Stack.
     * otherwise, it will return true.
     */
    public boolean undo(int index)
    {
        StackBonus<E> tmpStack = new StackBonus<>();
        if(this.size() < index + 1)
            return false;

        int removalVal = (this.size()-1) - index;
        for (int i = 0; i < removalVal; i++)
            tmpStack.push(this.pop());

        this.pop();

        while (!tmpStack.isEmpty())
            this.push(tmpStack.pop());

        return true;
    }
}
