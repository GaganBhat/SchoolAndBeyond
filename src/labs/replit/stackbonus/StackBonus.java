package labs.replit.stackbonus;

/**
 *  Your heading goes here
 */

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

    }


    /** reverse will create a new Stack containing the inverse order of
     * the current stack.
     * @return the Stack in reverse order
     * */
    public StackBonus<E> reverse()
    {
        return null;
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

        for (int i = 0; i < index; i++)
            tmpStack.push(this.pop());

        while (!tmpStack.isEmpty())
            this.push(tmpStack.pop());

        return true;
    }
}
