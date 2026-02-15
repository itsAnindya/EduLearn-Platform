import java.util.List;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
      EduLearnApp app = new EduLearnApp(scanner, CatalogSeeder.createDefaultCatalog());
      app.run();
    }
  }
}

class EduLearnApp {
  private static final int REPEAT_COUNT = 60;

  private final InputHelper input;
  private final List<Module> availableModules;
  private final CartService cartService;
  private Customer currentCustomer;

  EduLearnApp(Scanner scanner, List<Module> availableModules) {
    this.input = new InputHelper(scanner);
    this.availableModules = availableModules;
    this.cartService = new CartService();
  }

  void run() {
    mainMenu();
  }

  private void mainMenu() {
    while (true) {
      System.out.println("\n" + "=".repeat(REPEAT_COUNT));
      System.out.println("EDULEARN - E-LEARNING PLATFORM");
      System.out.println("=".repeat(REPEAT_COUNT));
      System.out.println("1. Browse Courses");
      System.out.println("2. View Cart");
      System.out.println("3. Checkout");
      System.out.println("4. Exit");
      System.out.println("=".repeat(REPEAT_COUNT));

      int choice = input.readIntInRange("Enter your choice (1-4): ", 1, 4);
      switch (choice) {
        case 1 -> browseModules();
        case 2 -> viewCart();
        case 3 -> checkout();
        case 4 -> {
          System.out.println("\nThank you for using EduLearn!");
          return;
        }
        default -> System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  private void browseModules() {
    while (true) {
      System.out.println("\n" + "-".repeat(REPEAT_COUNT));
      System.out.println("AVAILABLE MODULES");
      System.out.println("-".repeat(REPEAT_COUNT));

      for (int i = 0; i < availableModules.size(); i++) {
        System.out.println((i + 1) + ". " + availableModules.get(i).getTitle());
      }
      System.out.println((availableModules.size() + 1) + ". Back to Main Menu");

      int choice = input.readIntInRange("Enter module number: ", 1, availableModules.size() + 1);
      if (choice == availableModules.size() + 1) {
        return;
      }
      viewModule(availableModules.get(choice - 1));
    }
  }

  private void viewModule(Module module) {
    while (true) {
      System.out.println("\n" + "-".repeat(REPEAT_COUNT));
      System.out.println("MODULE: " + module.getTitle());
      System.out.println("-".repeat(REPEAT_COUNT));
      System.out.println("Price: $" + String.format("%.2f", module.calculatePrice()));
      System.out.println("Duration: " + module.calculateDuration() + " hours");
      System.out.println("\nModule Details:");
      module.printDetails("  ");
      System.out.println("\n" + "-".repeat(REPEAT_COUNT));
      System.out.println("1. Add Module to Cart (with add-ons)");
      System.out.println("2. Browse Courses in this Module");
      System.out.println("3. Back to Module List");

      int choice = input.readIntInRange("Enter your choice (1-3): ", 1, 3);
      switch (choice) {
        case 1 -> {
          addModuleToCart(module);
          return;
        }
        case 2 -> browseCourses(module);
        case 3 -> {
          return;
        }
        default -> System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  private void browseCourses(Module module) {
    while (true) {
      System.out.println("\n" + "-".repeat(REPEAT_COUNT));
      System.out.println("COURSES IN MODULE: " + module.getTitle());
      System.out.println("-".repeat(REPEAT_COUNT));

      List<Course> courses = module.getCourses();
      for (int i = 0; i < courses.size(); i++) {
        Course course = courses.get(i);
        System.out.println((i + 1) + ". " + course.getTitle());
      }
      System.out.println((courses.size() + 1) + ". Back to Module");

      int choice = input.readIntInRange("Enter course number: ", 1, courses.size() + 1);
      if (choice == courses.size() + 1) {
        return;
      }
      viewCourse(courses.get(choice - 1));
    }
  }

  private void viewCourse(Course course) {
    while (true) {
      System.out.println("\n" + "-".repeat(REPEAT_COUNT));
      System.out.println("COURSE: " + course.getTitle());
      System.out.println("-".repeat(REPEAT_COUNT));
      System.out.println("Price: $" + String.format("%.2f", course.calculatePrice()));
      System.out.println("Duration: " + course.calculateDuration() + " hours");
      System.out.println("\nCourse Details:");
      course.printDetails("  ");
      System.out.println("\n" + "-".repeat(REPEAT_COUNT));
      System.out.println("1. Add Course to Cart");
      System.out.println("2. Browse Lessons in this Course");
      System.out.println("3. Back to Course List");

      int choice = input.readIntInRange("Enter your choice (1-3): ", 1, 3);
      if (choice == 1) {
        addNonModuleToCart(course, "Course");
        return;
      }
      if (choice == 2) {
        browseLessons(course);
      }
      if (choice == 3) {
        return;
      }
    }
  }

  private void browseLessons(Course course) {
    while (true) {
      System.out.println("\n" + "-".repeat(REPEAT_COUNT));
      System.out.println("LESSONS IN COURSE: " + course.getTitle());
      System.out.println("-".repeat(REPEAT_COUNT));

      List<Lesson> lessons = course.getLessons();
      for (int i = 0; i < lessons.size(); i++) {
        Lesson lesson = lessons.get(i);
        System.out.println((i + 1) + ". " + lesson.getTitle());
      }
      System.out.println((lessons.size() + 1) + ". Back to Course");

      int choice = input.readIntInRange("Enter lesson number: ", 1, lessons.size() + 1);
      if (choice == lessons.size() + 1) {
        return;
      }
      viewLesson(lessons.get(choice - 1));
    }
  }

  private void viewLesson(Lesson lesson) {
    while (true) {
      System.out.println("\n" + "-".repeat(REPEAT_COUNT));
      System.out.println("LESSON: " + lesson.getTitle());
      System.out.println("-".repeat(REPEAT_COUNT));
      System.out.println("Price: $" + String.format("%.2f", lesson.calculatePrice()));
      System.out.println("Duration: " + lesson.calculateDuration() + " hours");
      System.out.println("\nLesson Details:");
      lesson.printDetails("  ");
      System.out.println("\n" + "-".repeat(REPEAT_COUNT));
      System.out.println("1. Add Lesson to Cart");
      System.out.println("2. Back to Lesson List");

      int choice = input.readIntInRange("Enter your choice (1-2): ", 1, 2);
      if (choice == 1) {
        addNonModuleToCart(lesson, "Lesson");
        return;
      }
      if (choice == 2) {
        return;
      }
    }
  }

  private void addModuleToCart(Module module) {
    System.out.println("\n" + "-".repeat(REPEAT_COUNT));
    System.out.println("ADD-ONS SELECTION");
    System.out.println("-".repeat(REPEAT_COUNT));
    System.out.println("Base Price: $" + String.format("%.2f", module.calculatePrice()));
    System.out.println("\nAvailable Add-ons:");
    System.out.println("1. Practice Question Set (+$10.00)");
    System.out.println("2. Live Mentor Support (+$20.00)");
    System.out.println("3. Both Add-ons (+$30.00)");
    System.out.println("4. None (base module only)");

    int choice = input.readIntInRange("Enter your choice (1-4): ", 1, 4);
    Purchasable finalItem = switch (choice) {
      case 1 -> new PracticeQuestionSetDecorator(module);
      case 2 -> new LiveMentorSupportDecorator(module);
      case 3 -> new LiveMentorSupportDecorator(
          new PracticeQuestionSetDecorator(module));
      default -> module;
    };

    switch (choice) {
      case 1 -> System.out.println("Added Practice Question Set");
      case 2 -> System.out.println("Added Live Mentor Support");
      case 3 -> System.out.println("Added Both Add-ons");
      default -> System.out.println("No add-ons selected");
    }

    cartService.addItem(finalItem);
    System.out.println("Module added to cart. New price: $" + String.format("%.2f", finalItem.calculatePrice()));
  }

  private void addNonModuleToCart(Purchasable item, String itemType) {
    cartService.addItem(item);
    System.out.println(itemType + " added to cart. Price: $" + String.format("%.2f", item.calculatePrice()));
  }

  private void viewCart() {
    if (cartService.isEmpty()) {
      System.out.println("\nYour cart is empty.");
      return;
    }

    while (true) {
      System.out.println("\n" + "-".repeat(REPEAT_COUNT));
      System.out.println("SHOPPING CART");
      System.out.println("-".repeat(REPEAT_COUNT));

      double total = 0;
      int count = 0;
      for (Purchasable item : cartService.getItems()) {
        count++;
        System.out.println("\nItem " + count + ":");
        item.printDetails("  ");
        System.out.println("  Price: $" + String.format("%.2f", item.calculatePrice()));
        total += item.calculatePrice();
      }

      System.out.println("\n" + "-".repeat(REPEAT_COUNT));
      System.out.println("Subtotal: $" + String.format("%.2f", total));
      System.out.println("-".repeat(REPEAT_COUNT));
      System.out.println("1. Remove Item from Cart");
      System.out.println("2. Clear Cart");
      System.out.println("3. Back to Main Menu");

      int choice = input.readIntInRange("Enter your choice (1-3): ", 1, 3);
      switch (choice) {
        case 1 -> removeFromCart();
        case 2 -> {
          cartService.clear();
          System.out.println("Cart cleared.");
          return;
        }
        case 3 -> {
          return;
        }
        default -> System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  private void removeFromCart() {
    int itemNum = input.readIntInRange("Enter item number to remove: ", 1, cartService.size());
    cartService.removeAt(itemNum - 1);
    System.out.println("Item removed from cart.");
  }

  private void checkout() {
    if (cartService.isEmpty()) {
      System.out.println("\nYour cart is empty. Please add items before checkout.");
      return;
    }

    System.out.println("\n" + "=".repeat(REPEAT_COUNT));
    System.out.println("CHECKOUT");
    System.out.println("=".repeat(REPEAT_COUNT));

    String name = input.readLine("Enter your name: ");
    boolean isStudent = input.readYesNo("Are you a student? (yes/no): ");
    boolean isFromDevCountry = input.readYesNo("Are you from a developing country? (yes/no): ");
    currentCustomer = new Customer(name, isFromDevCountry, isStudent);

    System.out.println("\n" + "-".repeat(REPEAT_COUNT));
    System.out.println("ORDER SUMMARY");
    System.out.println("-".repeat(REPEAT_COUNT));
    System.out.println("Customer: " + currentCustomer.getName());
    System.out.println("Student: " + (currentCustomer.isStudent() ? "Yes" : "No"));
    System.out.println("From Developing Country: " + (currentCustomer.isFromDevelopingCountry() ? "Yes" : "No"));
    System.out.println("\nItems in Cart:");

    Cart finalCart = cartService.toCart();
    double subtotal = finalCart.calculatePrice();
    double duration = finalCart.calculateDuration();
    int moduleCount = finalCart.getModuleCount();

    int itemNum = 0;
    for (Purchasable item : cartService.getItems()) {
      itemNum++;
      System.out.println("\n  Item " + itemNum + ":");
      item.printDetails("    ");
      System.out.println("    Price: $" + String.format("%.2f", item.calculatePrice()));
    }

    System.out.println("\n" + "-".repeat(REPEAT_COUNT));
    System.out.println("Subtotal: $" + String.format("%.2f", subtotal));
    System.out.println("Total Duration: " + duration + " hours");
    System.out.println("Module Count: " + moduleCount);
    System.out.println("-".repeat(REPEAT_COUNT));

    Purchasable checkout = new MultiModuleDiscount(
        new DevelopingCountryStudentDiscount(
            new SpecialDiscount(finalCart),
            currentCustomer));

    double finalPrice = checkout.calculatePrice();
    double savings = subtotal - finalPrice;

    System.out.println("\nDISCOUNTS APPLIED:");
    System.out.println("-".repeat(REPEAT_COUNT));

    if (duration >= 5) {
      System.out.println("Special Discount (duration >= 5 hours): -$12.00");
    }
    if (moduleCount >= 2) {
      System.out.println("Multi-Module Discount (2+ modules): -$15.00");
    }
    if (isStudent && isFromDevCountry) {
      System.out.println("Developing Country Student Discount: -$10.00");
    }

    System.out.println("-".repeat(REPEAT_COUNT));
    System.out.println("FINAL PRICE: $" + String.format("%.2f", finalPrice));
    if (savings > 0) {
      System.out.println("Total Savings: $" + String.format("%.2f", savings));
    }
    System.out.println("-".repeat(REPEAT_COUNT));

    boolean confirm = input.readYesNo("\nConfirm purchase? (yes/no): ");
    if (confirm) {
      System.out.println("\n" + "*".repeat(REPEAT_COUNT));
      System.out.println("THANK YOU FOR YOUR PURCHASE!");
      System.out.println("Order confirmed. Your courses are now available.");
      System.out.println("*".repeat(REPEAT_COUNT));
      cartService.clear();
    } else {
      System.out.println("Purchase cancelled. Returning to main menu.");
    }
  }
}