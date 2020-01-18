package temperaturwerte;

public class TemperaturWerte {
  public static void main(String[] args) {
    int[] temperatures = new int[]{2, 4, 9, 2, 5, 6, 5, 5, 1, 8, 3, 3, 5, 2};
    System.out.println("Average temperature: " + averageTemperature(temperatures));
    System.out.println("Min temperature: " + minTemperature(temperatures));
    System.out.println("Max temperature: " + maxTemperature(temperatures));
    System.out.println("Biggest difference in temperature between two days: " + biggestDifferenceInTemperature(temperatures));
    System.out.println("Biggest difference in temperature between two successive days: " + biggestDifferenceInTemperatureSuccessiveDays(temperatures));
  }

  static int averageTemperature(int[] temperatures) {
    int sum = 0;
    for (int temperature : temperatures) {
      sum += temperature;
    }
    return sum / temperatures.length;
  }

  static int minTemperature(int[] temperatures) {
    int min = Integer.MAX_VALUE;
    for (int temperature : temperatures) {
      if (min > temperature) {
        min = temperature;
      }
    }
    return min;
  }

  static int maxTemperature(int[] temperatures) {
    int max = Integer.MIN_VALUE;
    for (int temperature : temperatures) {
      if (max < temperature) {
        max = temperature;
      }
    }
    return max;
  }

  static int biggestDifferenceInTemperature(int[] temperatures) {
    return maxTemperature(temperatures) - minTemperature(temperatures);
  }

  static int biggestDifferenceInTemperatureSuccessiveDays(int[] temperatures) {
    int biggestDiff = 0;
    for (int i = 0; i < temperatures.length - 1; i++) {
      if (Math.abs(temperatures[i] - temperatures[i + 1]) > biggestDiff) {
        biggestDiff = Math.abs(temperatures[i] - temperatures[i + 1]);
      }
    }
    return biggestDiff;
  }
}


