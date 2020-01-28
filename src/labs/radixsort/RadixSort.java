package labs.radixsort;

/**
 * RadixSort.java lab has static helper methods to complete independent
 * passes of the radix sort and returns queues and number arrays
 * upon completion of said static methods
 *
 * @author Gagan Bhat
 * Collaborators: None
 * Teacher Name: Mrs. Ishman
 * Period: 3
 * Due Date: 1/28/2020
 */

import java.util.*;

public class RadixSort
{
    private static final int QUEUE_SIZE = 10; //Size of queue

    /** Sorts the given array using the Radix Sort
     *  @param nums the array of integers to be sorted
     *      Precondition: nums.length > 0; all values in nums are non-negative
     */
    public static void sort(int[] nums)
    {
        int numDigits = getMaxDigits(nums);

        for (int k = 0; k < numDigits; k++)
        {
            List<Queue<Integer>> qs = itemsToQueues(nums, k);
            queuesToArray(qs, nums);
        }
    }

    /**
     * Given an array of integers, returns the maximum number of digits of any value in the array.
     * @param numbers array of numnbers
     * @return maximum number of digits of any value in the array
     */
    private static int getMaxDigits(int[] numbers)
    {
        int maxDigitLength = 0;
        for(int num: numbers)
        {
            int size = Integer.toString(num).length();
            if(size > maxDigitLength)
                maxDigitLength = size;
        }
        return maxDigitLength;
    }

    /**
     * Returns the digit specified at k
     * @param number array of numbers
     * @param k location from which digit should be returned
     * @return digit based on k location
     */
    private static int getDigit(int number, int k)
    {
        return (number % (int) Math.pow(QUEUE_SIZE, k + 1)) /
                (int) Math.pow(QUEUE_SIZE, k);
    }

    /**
     * Converts numbers to queues correspondingly
     * @param nums number array of unsorted
     * @param k location to search at
     * @return one pass completed radix sort queue
     */
    private static List<Queue<Integer>> itemsToQueues(int[] nums, int k)
    {
        List<Queue<Integer>> listQueue= new ArrayList<>();

        for(int i = 0; i < QUEUE_SIZE; i++)
            listQueue.add(new LinkedList<>());

        for (int i = 0; i < QUEUE_SIZE; i++)
            listQueue.set(i, new LinkedList<>());

        for(int num: nums)
            listQueue.get(getDigit(num, k)).offer(num);

        return listQueue;
    }

    /**
     * Fills array from queue data
     * @param queues queue given by method call
     * @param nums array of numbers to fill
     */
    private static void queuesToArray(List<Queue<Integer>> queues, int[] nums)
    {
        int veryCuteInteger = 0;

        for (Queue<Integer> queue : queues)
            while (queue.size() > 0) {
                nums[veryCuteInteger] = queue.remove();
                veryCuteInteger++;
            }
    }


}



