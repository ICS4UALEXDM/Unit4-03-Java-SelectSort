import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/**
* This program calculates amount of mean median and mode.
*
* @author  Alex De Meo
* @version 1.0
* @since   2023/02/08
*/

public final class SelectionSort {
    /**
    * This is a private constructor used to satisfy the
    * style checker.
    *
    * @exception IllegalStateException Utility Class
     */
    private SelectionSort() {
        throw new IllegalStateException("Utility Class");
    }
    /**
    * This is the main method.
    *
    * @param args Unused
    * @throws Exception if something goes wrong
    */

    public static void main(String[] args) throws Exception {
        // Initializing variables
        String line;
        final String err = "Error";
        final String splitBy = " ";
        try {
            // new file object
            final File input = new File("input.txt");

            // Creating the writer
            final FileWriter writer = new FileWriter("ouput.txt");

            try {
                // Creating the scanner.
                final Scanner scanner = new Scanner(input);

                // ArrayList to hold the lines
                final ArrayList<String> lines = new ArrayList<>();
                // Getting the input from the first file
                while (scanner.hasNextLine()) {
                    // getting next line and putting it in the interim list
                    line = scanner.nextLine();
                    lines.add(line);
                }
                for (String aLine : lines) {
                    if (aLine.length() != 0) {
                        try {
                            // Reference, Keiden showed me how to code it like
                            // this.
                            final int[] numbers = Arrays.stream(
                                    aLine.split(splitBy)
                                ).mapToInt(Integer::parseInt).toArray();
                            final int[] sorted = selectionSort(numbers);
                            for (int num : sorted) {
                                writer.write(num + splitBy);
                            }
                            writer.write("\n");
                        } catch (NumberFormatException error) {
                            writer.write("ERROR: Invalid number\n");
                        }
                    } else {
                        writer.write("ERROR: Empty line\n");
                    }
                }
            } catch (IOException error) {
                System.out.println(err + error.getMessage());
            }
            // closes the writer
            writer.close();
        } catch (IOException error) {
            System.out.println(err + error.getMessage());
        }
    }
    /**
    * This is the method reverseRecs the string.
    *
    * @param numbers This is the array to be sorted
    * @return the sorted array
    */

    public static int[] selectionSort(int[] numbers) {
        // This loop determines how many passes to go through the array
        for (int counter1 = 0; counter1 < numbers.length - 1; counter1++) {
            // Setting the index of the current minimum
            int currentMinimum = counter1;
            // This for loop iterates through the array
            for (int counter2 = counter1 + 1;
                    counter2 < numbers.length; counter2++) {
                // Choosing whether or not to reset the minimum's index'
                if (numbers[counter2] < numbers[currentMinimum]) {
                    currentMinimum = counter2;
                }
            }
            if (numbers[counter1] != numbers[currentMinimum]) {
                // This chunk of code swaps the minimum with the selected index.
                final int temp = numbers[counter1];
                numbers[counter1] = numbers[currentMinimum];
                numbers[currentMinimum] = temp;
            }
        }
        return numbers;
    }
}
