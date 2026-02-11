/**
 * DECORATOR DESIGN PATTERN EXAMPLE
 *
 * The Decorator pattern is used to attach additional responsibilities to an object
 * dynamically. Decorators provide a flexible alternative to subclassing for extending
 * functionality.
 *
 * Use Case in EduLearn:
 * - Optional add-ons like Practice question set ($10) and Live mentor support ($20)
 * - Can be added to any Course or Module
 * - Multiple decorators can be stacked together
 * - Price calculation includes base price + decorator prices
 */

// Abstract Component Interface
interface Purchasable {
    double getPrice();
    String getDescription();
}

// Concrete Component - Base course offering
class BaseCourse implements Purchasable {
    private String name;
    private double basePrice;
    
    public BaseCourse(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }
    
    @Override
    public double getPrice() {
        return basePrice;
    }
    
    @Override
    public String getDescription() {
        return "Course: " + name;
    }
}

// Abstract Decorator - base class for all decorators
abstract class CourseDecorator implements Purchasable {
    protected Purchasable wrappedCourse;
    
    public CourseDecorator(Purchasable wrappedCourse) {
        this.wrappedCourse = wrappedCourse;
    }
    
    @Override
    public double getPrice() {
        return wrappedCourse.getPrice();
    }
    
    @Override
    public String getDescription() {
        return wrappedCourse.getDescription();
    }
}

// Concrete Decorator 1 - Practice Question Set add-on ($10)
class PracticeQuestionSetDecorator extends CourseDecorator {
    private static final double PRICE = 10.0;
    
    public PracticeQuestionSetDecorator(Purchasable wrappedCourse) {
        super(wrappedCourse);
    }
    
    @Override
    public double getPrice() {
        // Add the decorator's price to the wrapped component's price
        return wrappedCourse.getPrice() + PRICE;
    }
    
    @Override
    public String getDescription() {
        return wrappedCourse.getDescription() + " + Practice Question Set ($10)";
    }
}

// Concrete Decorator 2 - Live Mentor Support add-on ($20)
class LiveMentorSupportDecorator extends CourseDecorator {
    private static final double PRICE = 20.0;
    
    public LiveMentorSupportDecorator(Purchasable wrappedCourse) {
        super(wrappedCourse);
    }
    
    @Override
    public double getPrice() {
        // Add the decorator's price to the wrapped component's price
        return wrappedCourse.getPrice() + PRICE;
    }
    
    @Override
    public String getDescription() {
        return wrappedCourse.getDescription() + " + Live Mentor Support ($20)";
    }
}

// Concrete Decorator 3 - Premium Support add-on ($15)
class PremiumSupportDecorator extends CourseDecorator {
    private static final double PRICE = 15.0;
    
    public PremiumSupportDecorator(Purchasable wrappedCourse) {
        super(wrappedCourse);
    }
    
    @Override
    public double getPrice() {
        return wrappedCourse.getPrice() + PRICE;
    }
    
    @Override
    public String getDescription() {
        return wrappedCourse.getDescription() + " + Premium Support ($15)";
    }
}

// Main class demonstrating the Decorator pattern
public class App {
    public static void main(String[] args) {
        System.out.println("=== DECORATOR PATTERN EXAMPLE ===\n");
        
        // Example 1: Base course without any add-ons
        System.out.println("Example 1: Course without add-ons");
        Purchasable basicCourse = new BaseCourse("Java Fundamentals", 100);
        displayPurchase(basicCourse);
        
        // Example 2: Course with single decorator
        System.out.println("\nExample 2: Course with Practice Question Set");
        Purchasable courseWithPractice = new PracticeQuestionSetDecorator(basicCourse);
        displayPurchase(courseWithPractice);
        
        // Example 3: Course with two decorators (stacking)
        System.out.println("\nExample 3: Course with Practice Question Set + Live Mentor Support");
        Purchasable courseWithBothAddOns = new LiveMentorSupportDecorator(courseWithPractice);
        displayPurchase(courseWithBothAddOns);
        
        // Example 4: Course with all decorators
        System.out.println("\nExample 4: Course with all add-ons");
        Purchasable fullyCertifiedCourse = new PremiumSupportDecorator(courseWithBothAddOns);
        displayPurchase(fullyCertifiedCourse);
        
        // Example 5: Different combination (Premium Support without Practice)
        System.out.println("\nExample 5: Course with Premium Support + Live Mentor (no Practice)");
        Purchasable alternativeCourse = new LiveMentorSupportDecorator(
                                           new PremiumSupportDecorator(basicCourse)
                                        );
        displayPurchase(alternativeCourse);
        
        // Demonstration of flexibility
        System.out.println("\n--- Flexibility Demonstration ---");
        System.out.println("Without decorators, we would need separate classes like:");
        System.out.println("  - JavaBasicCourse");
        System.out.println("  - JavaWithPracticeCourse");
        System.out.println("  - JavaWithMentorCourse");
        System.out.println("  - JavaWithBothCourse");
        System.out.println("  - ... many more combinations!");
        System.out.println("\nWith Decorator pattern, we compose functionality dynamically!");
    }
    
    // Helper method to display purchase details
    private static void displayPurchase(Purchasable item) {
        System.out.println("Description: " + item.getDescription());
        System.out.println("Total Price: $" + item.getPrice());
    }
}

