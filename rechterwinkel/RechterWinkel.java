package rechterwinkel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class RechterWinkel {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    double a, b, c;
    System.out.println("-- Dreiecksüberprüfung --");
    System.out.println("Länge der Kathete a eingeben: ");
    a = sc.nextDouble();
    System.out.println("Länge der Kathete b eingeben: ");
    b = sc.nextDouble();
    System.out.println("Länge der Hypothenuse c eingeben: ");
    c = sc.nextDouble();

    System.out.println(rechtWinkligesDreieckPrüfung(a, b, c) == 1 ? "Das Dreieck ist rechtwinklig!" : "Das Dreieck ist nicht rechtwinklig!");
  }

  static int rechtWinkligesDreieckPrüfung(double a, double b, double c) {
    return c == round(Math.sqrt(round(Math.pow(a, 2), 2) + round(Math.pow(b, 2), 2)), 2) ? 1 : 0;
  }

  private static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    BigDecimal bd = new BigDecimal(Double.toString(value));
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }
}
