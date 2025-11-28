/**
 * MaxHeap is a full implementation of a max heap using an array. It stores integers and grows
 * dynamically as needed.
 * 
 * Its supporting features are:
 * - Build heap (using Floyd's method)
 * - Insert
 * - Delete max
 * - Heap sort (destroys the heap after finishing)
 * 
 * @author Treasure Chandler
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MaxHeap {
    // Variables declaration
    private int[] heap;
    private int size, capacity;

    /**
     * Constructs an empty heap with a given initial capacity.
     * 
     * @param capacity      Given initial capacity
     */
    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.heap = new int[capacity];
        this.size = 0;
    }

    /**
     * Ensures the heap has enough capacity
     * 
     * If not, it doubles the size of the array
     */
    private void ensureCapacity() {
        if (size >= capacity) {
            capacity *= 2;

            int[] newHeap = new int[capacity];

            System.arraycopy(heap, 0, newHeap, 0, size);

            heap = newHeap;
        }
    }

    /**
     * Builds the heap using Floyd's build-heap algorithm by reading integers from a supplied
     * filename.
     * 
     * @param fileName      Name of the integer containing file
     */
    public void buildHeap(String fileName) {
        // Reset the heap before building
        size = 0;

        try (Scanner scan = new Scanner(new File(fileName))) {
            while (scan.hasNextInt()) {
                ensureCapacity();

                // Add directly; this will be fixed with Floyd's method
                heap[size++] = scan.nextInt();

                // Apply Floyd's method
                for (int i = size / 2 - 1; i >= 0; i--) {
                    heapifyDown(i);
                }
            }

            System.out.println("\nThe heap has been successfully built from the file.");
        } catch (FileNotFoundException e) {
            System.out.println("\nError: File not found. If there are no \".txt\" files, you " +
                                "will need to manually create a file with your own values " +
                                "listed in it.");
        }
    }

    /**
     * Inserts a value into the heap by placing it at the end, then bubbling upward.
     * 
     * @param value     Integer to support
     */
    public void insert(int value) {
        ensureCapacity();

        // Place at the next free index
        heap[size] = value;

        // Restore the heap order
        heapifyUp(size);

        size++;
    }

    /**
     * Removes and returns the maximum value (root) from the heap
     * 
     * @return      Max value or -1 if empty
     */
    public int deleteMax() {
        if (size == 0) {
            System.out.println("\nHeap is empty.");
            return -1;
        }

        int maxValue = heap[0];

        // Move the last element to the root
        heap[0] = heap[size - 1];
        size--;

        // Restore the heap order
        heapifyDown(0);

        return maxValue;
    }

    /**
     * Performs the heap sort in-place by repeatedly removing the max and placing it at the
     * end of the array. Then, the sorted result is printed
     * 
     * After finishing, the size is set to 0 because the heap is considered destroyed
     */
    public void heapSort() {
        if (size == 0) {
            System.out.println("\nThe heap is empty. There is nothing to sort.");
            return;
        }

        int originalSize = size;

        // Perform the sort
        for (int i = size - 1; i >0; i--) {
            // Swap the root (max) into final position
            int temp = heap[0];
            heap[0] = heap[i];
            heap[i] = temp;

            // Reduce the temp size
            size--;

            heapifyDown(0);
        }

        // Print the sorted result
        System.out.println("\nSorted Output:");

        for (int i = 0; i < originalSize; i++) {
            System.out.println(heap[i] + " ");
        }

        System.out.println();

        // Destroy heap
        size = 0;

        System.out.println("\nHeap destroyed after heap sort (size reset to 0).");
    }

    /**
     * Prints the current heap contents (that are not sorted).
     */
    public void print() {
        if (size == 0) {
            System.out.println("\nHeap is empty.");
            return;
        }

        System.out.println("\nHeap elements: ");
        for (int i = 0; i < size; i++) {
            System.out.println(heap[i] + " ");
        }

        System.out.println();
    }

    /**
     * Moves a value upward to restore heap property
     * 
     * @param index     Current index
     */
    private void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;

            if (heap[index] > heap[parent]) {
                int temp = heap[index];
                heap[index] = heap[parent];
                heap[parent] = temp;

                // Continue going up
                index = parent;
            } else {
                // Already in the correct position
                break;
            }
        }
    }

    /**
     * Moves a value downward from the given index to restore the heap property
     * 
     * @param index     Current index
     */
    private void heapifyDown(int index) {
        while (true) {
            int largest = index;
            int left = 2 * index + 1;
            int right = 2 * index + 2;

            // Check left child
            if (left < size && heap[left] > heap[largest]) {
                largest = left;
            }

            // Check the right child
            if (right < size && heap[right] > heap[largest]) {
                largest = right;
            }

            // If the parent is already larger, stop
            if (largest == index) {
                break;
            }

            // Swap
            int temp = heap[index];

            heap[index] = heap[largest];
            heap[largest] = temp;

            // Continue going down
            index = largest;
        }
    }
}