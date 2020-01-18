package ostersonntag;

import java.time.LocalDate;
import java.util.Scanner;

public class OsterSonntag {
  private static final Scanner SC = new Scanner(System.in);

  public static void main(String[] args) {
    int year = SC.nextInt();

    printEasterSundayDate(getEasterSundayDayAndMonth(year), year);
  }

  private static int[] getEasterSundayDayAndMonth(int year) {
    int a, b, c, d, e, f, g, h, i, k, l, m, x;

    a = year % 19;
    b = year / 100;
    c = year % 100;
    d = b / 4;
    e = b % 4;
    f = (b + 8) / 25;
    g = (b - f + 1) / 3;
    h = (19 * a + b - d - g + 15) % 30;
    i = c / 4;
    k = c % 4;
    l = (32 + 2 * e + 2 * i - h - k) % 7;
    m = (a + 11 * h + 22 * l) / 451;
    x = h + l - 7 * m + 114;

    return new int[]{x / 31, (x % 31) + 1};
  }

  private static void printEasterSundayDate(int[] dayAndMonth, int year) {
    LocalDate datum = LocalDate.of(year, dayAndMonth[0], dayAndMonth[1]);
    System.out.printf("Aschermittwoch: %s\n", datum.minusDays(46));
    System.out.printf("Palmsonntag: %s\n", datum.minusWeeks(1));
    System.out.printf("Gründonnerstag: %s\n", datum.minusDays(3));
    System.out.printf("Karfreitag: %s\n", datum.minusDays(2));
    System.out.printf("Karsamstag: %s\n", datum.minusDays(1));
    System.out.printf("Ostersonntag: %s\n", datum);
    System.out.printf("Ostermontag: %s\n", datum.plusDays(1));
    System.out.printf("Christi Himmelfahrt: %s\n", datum.plusDays(39));
    System.out.printf("Pfingstsonntag: %s\n", datum.plusDays(49));
    System.out.printf("Pfingstmontag: %s\n", datum.plusDays(50));
    System.out.printf("Fronleichnam: %s\n", datum.plusDays(60));
  }

}
