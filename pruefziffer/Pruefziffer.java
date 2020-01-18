package pruefziffer;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Pruefziffer {
  private static final Scanner SC = new Scanner(System.in);

  public static void main(String[] args) {
    String barcode = readBarcode();
    String barcodeType = checkBarcode(barcode);

    printValidBarcode(barcode, barcodeType);
  }

  static String getBarcodeByType(String barcode, String type) {
    switch (type) {
      case "ISBN":
        return barcode + calcPruefzifferISBN(barcode);
      case "EAN":
        return barcode + calcPruefzifferEAN(barcode);
    }
    return null;
  }

  static void printValidBarcode(String barcode, String type) {
    if (type != null) {
      barcode = getBarcodeByType(barcode, type);
      if (barcode != null) {
        System.out.println(type + ": " + barcode);
      }
    }
  }

  static char calcPruefzifferISBN(String barcode) {
    int[] barcodeArr = getBarcodeAsIntArray(barcode);
    IntStream.range(0, barcodeArr.length).forEach(i -> barcodeArr[i] *= (i + 1));
    int pruefziffer = Arrays.stream(barcodeArr).sum() % 11;
    return pruefziffer < 10 ? (char) (pruefziffer + '0') : 'X';
  }

  static char calcPruefzifferEAN(String barcode) {
    int[] barcodeArr = getBarcodeAsIntArray(barcode);
    IntStream.range(0, barcodeArr.length).filter(i -> i % 2 == 1).forEach(i -> barcodeArr[i] *= 3);
    int pruefziffer = Arrays.stream(barcodeArr).sum() % 10;
    return pruefziffer != 0 ? (char) (10 - pruefziffer + '0') : '0';
  }

  static int[] getBarcodeAsIntArray(String barcode) {
    return IntStream.range(0, barcode.length()).map(barcode::charAt).map(Character::getNumericValue).toArray();
  }

  static String readBarcode() {
    System.out.println("ISBN(9-Stellen)/EAN(12-Stellen) Nr. einlesen: ");
    return SC.next();
  }

  static String checkBarcode(String barCode) {
    if (barCode.matches("\\d+")) {
      if (barCode.length() == 9) return "ISBN";
      if (barCode.length() == 12) return "EAN";
      System.out.println("Barcode with incorrect length entered!");
      return null;
    }
    System.out.println("Barcode has to contain only numbers!");
    return null;
  }

}
