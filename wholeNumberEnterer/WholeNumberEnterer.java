package wholeNumberEnterer;

import java.util.Scanner;

public class WholeNumberEnterer {
  public static void main(String[] args) {
    int[] erg = getMinMax();
    System.out.println("Min: " + erg[0]);
    System.out.println("Max: " + erg[1]);
  }

  public static int[] getMinMax() {
    int[] returnValue = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
    Scanner scanner = new Scanner(System.in);
    int input;
    do {
      System.out.println("Enter whole positive number: (0 to exit)");
      input = scanner.nextInt();
      if (input != 0) {
        if (returnValue[0] > input) {
          returnValue[0] = input;
        } else if (returnValue[1] < input) {
          returnValue[1] = input;
        }
      }
    } while (input != 0);
    return returnValue;
  }
}
