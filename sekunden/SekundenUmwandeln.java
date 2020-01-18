package sekunden;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SekundenUmwandeln {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Sekunden oder Zeit eingeben? (s/z)");
    String check = sc.next().toLowerCase();
    if (check.equals("s")) {
      System.out.println("Sekunden eingenben: ");
      int inputSeconds = sc.nextInt();
      showFormatedInput(calcHoursMinutesSeconds(inputSeconds));
    } else if (check.equals("z")) {
      System.out.println("Zeit eingeben: (hh:mm:ss)");
      String inputTime = sc.next();
      showFormatedInput(Integer.toString(calcSecondsFromHoursMinutesSeconds(inputTime)));
    }
  }

  static String calcHoursMinutesSeconds(int input) {
    int hours = input / 3600;
    int minutes = (input - hours * 3600) / 60;
    int seconds = input - hours * 3600 - minutes * 60;


    return (hours < 10 ? "0" + hours : hours) + ":" + (minutes < 10 ? "0" + minutes : minutes) + ":" + (seconds < 10 ? "0" + seconds : seconds);
  }

  static int calcSecondsFromHoursMinutesSeconds(String input) {
    List<String> inputList = Arrays.asList(input.split(":"));
    int seconds = 0;
    if (inputList.size() == 3) {
      int hours = Integer.parseInt(inputList.get(0));
      int minutes = Integer.parseInt(inputList.get(1));
      seconds = Integer.parseInt(inputList.get(2));

      seconds += minutes * 60 + hours * 3600;
    }
    return seconds;
  }

  static void showFormatedInput(String time) {
    System.out.println(time);
  }
}
