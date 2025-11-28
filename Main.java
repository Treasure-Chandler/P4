/**
 * Main provides a simple console interface for interacting with MaxHeap. 
 * 
 * It provides an interactive choice system:
 * 1 - Build heap from file
 * 2 - Insert value
 * 3 - Delete max
 * 4 - Heap sort
 * 5 - Print heap
 * 
 * There is an example ".txt" file in this project that is included to work with this class.
 * 
 * @author Treasure Chandler
 */

import java.util.Scanner;

public class Main {
    /**
     * Method that is the catalyst for interaction
     * 
     * @param args      Entered values
     */
    public static void main(String[] args) {
        // Declaring the input and initial capacity
        Scanner input = new Scanner(System.in);
        MaxHeap heap = new MaxHeap(20);

        int choice;

        do {
            System.out.println("\n===== MAX HEAP MENU =====");
            System.out.println("1 - Build heap from file");
            System.out.println("2 - Insert number");
            System.out.println("3 - Delete max");
            System.out.println("4 - Heapsort");
            System.out.println("5 - Print heap");
            System.out.println("6 - Exit");
            System.out.print("\nEnter your choice: ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter file name: ");
                    String fileName = input.next();
                    heap.buildHeap(fileName);
                    break;

                case 2:
                    System.out.print("Enter a number to insert: ");
                    int value = input.nextInt();
                    heap.insert(value);

                    System.out.println("Value " + value + " has been inserted.");
                    break;

                case 3:
                    int max = heap.deleteMax();

                    if (max != -1) {
                        System.out.println("Deleted max: " + max);
                    }
                    break;

                case 4:
                    heap.heapSort();;
                    break;

                case 5:
                    heap.print();
                    break;

                case 6:
                    System.out.println("Exiting program...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("That was an invalid choice. Please try again.");
            }
        } while (choice != 6);

        input.close();
    }
}