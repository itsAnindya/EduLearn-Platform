import java.util.Scanner;

class InputHelper {
  private final Scanner scanner;

  InputHelper(Scanner scanner) {
    this.scanner = scanner;
  }

  String readLine(String prompt) {
    System.out.print(prompt);
    return scanner.nextLine().trim();
  }

  int readIntInRange(String prompt, int min, int max) {
    while (true) {
      String input = readLine(prompt);
      try {
        int value = Integer.parseInt(input);
        if (value >= min && value <= max) {
          return value;
        }
        System.out.println("Invalid choice. Please try again.");
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a number.");
      }
    }
  }

  boolean readYesNo(String prompt) {
    while (true) {
      String input = readLine(prompt).toLowerCase();
      if (input.equals("yes") || input.equals("y")) {
        return true;
      }
      if (input.equals("no") || input.equals("n")) {
        return false;
      }
      System.out.println("Invalid choice. Please enter yes or no.");
    }
  }
}
