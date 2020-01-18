package Zahlensysteme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Zahlensysteme {
  private static final Scanner SC = new Scanner(System.in);
  private static Type type = null;

  public static void main(String[] args) {
    do {
      enterType();
      getSolution(enterNumber());
      System.out.println("Enter new number? (j/n)");
    } while (SC.nextLine().toLowerCase().charAt(0) == 'j');
  }

  public static String enterNumber() {
    System.out.println("Number to parse: ");
    return SC.nextLine().trim();
  }

  public static void enterType() {
    do {
      System.out.println("Type of the number: ");
      System.out.println("(binary | decimal | octal | hexadecimal)");
      type = Type.getTypeForString(SC.nextLine().trim());
      if (type == null) {
        System.out.println("Type is not valid, please try again.");
      }
    } while (type == null);
  }

  public static void getSolution(String number) {
    Arrays.stream(Type.values()).filter(type1 -> type1 != type).forEach(t -> printSolution(t.getTypeString(), t.getConverterFunction().apply(number)));
  }

  public static void printSolution(String type, String solution) {
    System.out.printf("The solution in %s is: %s\n", type, solution);
  }

  public static List<Integer> getNumberAsIntegerList(String numberString) {
    return IntStream.range(0, numberString.length()).map(numberString::charAt).map(Character::getNumericValue).boxed().collect(Collectors.toCollection(ArrayList::new));
  }

  public static String convertToDecimal(String numberString) {
    return convertBinaryToDecimal(convertToBinary(numberString));
  }

  public static String convertBinaryToDecimal(String numberString) {
    List<Integer> arrList = getNumberAsIntegerList(numberString);
    IntStream.range(0, arrList.size()).forEach(i -> arrList.set(i, arrList.get(i) * (int) Math.pow(2, arrList.size() - 1 - i)));
    return Integer.toString(arrList.stream().mapToInt(Integer::intValue).sum());
  }

  public static String convertToOctal(String numberString) {
    StringBuilder builder = new StringBuilder();
    int number = Integer.parseInt(type == Type.DECIMAL ? numberString : convertBinaryToDecimal(convertToBinary(numberString)));
    while (number != 0) {
      builder.append(number % 8);
      number /= 8;
    }
    return builder.reverse().toString();
  }

  public static String convertToHexadecimal(String numberString) {
    String digits = "0123456789ABCDEF";
    StringBuilder builder = new StringBuilder();
    int number = Integer.parseInt(type == Type.DECIMAL ? numberString : convertBinaryToDecimal(convertToBinary(numberString)));
    while (number != 0) {
      builder.append(digits.charAt(number % 16));
      number /= 16;
    }
    return builder.reverse().toString();
  }

  public static String convertToBinary(String number) {
    switch (type) {
      case BINARY:
        return number;
      case DECIMAL:
        return convertDecimalToBinary(number);
      case OCTAL:
        List<Integer> octalNumber = getNumberAsIntegerList(number);
        return octalNumber.stream().map(i -> String.format("%1$3s", convertDecimalToBinary(i.toString())).replace(' ', '0')).collect(Collectors.joining());
      case HEXADECIMAL:
        String digits = "0123456789ABCDEF";
        int dec = 0;
        for (int i = 0; i < number.length(); i++) {
          char c = number.charAt(i);
          int d = digits.indexOf(c);
          dec = 16 * dec + d;
        }
        return convertDecimalToBinary(Integer.toString(dec));
      default:
        return "";
    }
  }

  public static String convertDecimalToBinary(String numberString) {
    StringBuilder builder = new StringBuilder();
    int number = Integer.parseInt(numberString);
    while (number != 0) {
      builder.append(number % 2);
      number /= 2;
    }
    return builder.reverse().toString();
  }

  private enum Type {
    BINARY("binary", Zahlensysteme::convertToBinary),
    DECIMAL("decimal", Zahlensysteme::convertToDecimal),
    OCTAL("octal", Zahlensysteme::convertToOctal),
    HEXADECIMAL("hexadecimal", Zahlensysteme::convertToHexadecimal),
    ;


    private String typeString;
    private Function<String, String> converterFunction;

    Type(String type, Function<String, String> converterFunction) {
      this.typeString = type;
      this.converterFunction = converterFunction;
    }

    public String getTypeString() {
      return typeString;
    }

    public static Type getTypeForString(String typeString) {
      return Arrays.stream(Type.values()).filter(type -> type.getTypeString().equals(typeString)).findFirst().orElse(null);
    }

    public Function<String, String> getConverterFunction() {
      return converterFunction;
    }
  }

}
