/**
 * BRIDGE DESIGN PATTERN EXAMPLE
 *
 * The Bridge pattern is used to decouple an abstraction from its implementation so that
 * the two can vary independently. It involves creating a bridge interface that the high-level
 * classes use to access the low-level classes.
 *
 * Use Case in EduLearn:
 * - Separate the course content structure from pricing algorithms
 * - Different pricing strategies (standard pricing, dynamic pricing, bulk pricing)
 * - Extend pricing without modifying course classes and vice versa
 * - Allows independent evolution of course hierarchies and pricing logic
 */

// Abstraction - represents the course structure
abstract class CourseOffering {
    // Bridge: reference to the pricing implementation
    protected PricingStrategy pricingStrategy;
    
    protected String name;
    protected double basePrice;
    protected double duration;  // in hours
    
    public CourseOffering(String name, double basePrice, double duration) {
        this.name = name;
        this.basePrice = basePrice;
        this.duration = duration;
    }
    
    // Bridge: set the pricing strategy dynamically
    public void setPricingStrategy(PricingStrategy strategy) {
        this.pricingStrategy = strategy;
    }
    
    // Bridge: delegate pricing calculation to the implementation
    public abstract double calculatePrice();
    
    public String getDescription() {
        return name + " (Duration: " + duration + "h, Base Price: $" + basePrice + ")";
    }
}

// Refined Abstraction 1 - Single Course
class SingleCourse extends CourseOffering {
    public SingleCourse(String name, double basePrice, double duration) {
        super(name, basePrice, duration);
    }
    
    @Override
    public double calculatePrice() {
        // Use the bridge to calculate final price
        return pricingStrategy.calculatePrice(basePrice, duration, 1);
    }
}

// Refined Abstraction 2 - Course Bundle (multiple courses)
class CourseBundleOffering extends CourseOffering {
    private int numberOfCourses;
    
    public CourseBundleOffering(String name, double basePrice, double duration, int numberOfCourses) {
        super(name, basePrice, duration);
        this.numberOfCourses = numberOfCourses;
    }
    
    @Override
    public double calculatePrice() {
        // Use the bridge to calculate final price for bundle
        return pricingStrategy.calculatePrice(basePrice, duration, numberOfCourses);
    }
}

// Refined Abstraction 3 - Annual Subscription
class AnnualSubscription extends CourseOffering {
    private int courseCount;
    
    public AnnualSubscription(String name, double basePrice, double duration, int courseCount) {
        super(name, basePrice, duration);
        this.courseCount = courseCount;
    }
    
    @Override
    public double calculatePrice() {
        // Use the bridge to calculate final price for subscription
        return pricingStrategy.calculatePrice(basePrice, duration, courseCount);
    }
}

// Implementor Interface - defines the pricing implementation
interface PricingStrategy {
    double calculatePrice(double basePrice, double duration, int quantity);
    String getStrategyName();
}

// Concrete Implementor 1 - Standard Pricing
class StandardPricing implements PricingStrategy {
    @Override
    public double calculatePrice(double basePrice, double duration, int quantity) {
        // Standard: just return base price
        return basePrice;
    }
    
    @Override
    public String getStrategyName() {
        return "Standard Pricing";
    }
}

// Concrete Implementor 2 - Duration-Based Pricing
class DurationBasedPricing implements PricingStrategy {
    private static final double PRICE_PER_HOUR = 5.0;
    
    @Override
    public double calculatePrice(double basePrice, double duration, int quantity) {
        // Calculate based on duration: base + (duration * price per hour)
        return basePrice + (duration * PRICE_PER_HOUR);
    }
    
    @Override
    public String getStrategyName() {
        return "Duration-Based Pricing ($" + PRICE_PER_HOUR + "/hour)";
    }
}

// Concrete Implementor 3 - Bulk Discount Pricing
class BulkDiscountPricing implements PricingStrategy {
    @Override
    public double calculatePrice(double basePrice, double duration, int quantity) {
        // Apply bulk discount for multiple courses
        double price = basePrice;
        if (quantity >= 3) {
            price = price * 0.85;  // 15% discount for 3+ courses
        } else if (quantity >= 2) {
            price = price * 0.90;  // 10% discount for 2 courses
        }
        return price;
    }
    
    @Override
    public String getStrategyName() {
        return "Bulk Discount Pricing";
    }
}

// Concrete Implementor 4 - Premium Dynamic Pricing
class PremiumDynamicPricing implements PricingStrategy {
    @Override
    public double calculatePrice(double basePrice, double duration, int quantity) {
        // Premium pricing: surge pricing based on duration and quantity
        double price = basePrice;
        
        // Premium multiplier for longer courses
        if (duration >= 10) {
            price = price * 1.25;  // 25% premium for 10+ hour courses
        } else if (duration >= 5) {
            price = price * 1.15;  // 15% premium for 5+ hour courses
        }
        
        // Quantity multiplier (cheaper when buying in bulk)
        if (quantity >= 5) {
            price = price * 0.80;  // 20% off for 5+ items
        } else if (quantity >= 3) {
            price = price * 0.90;  // 10% off for 3+ items
        }
        
        return price;
    }
    
    @Override
    public String getStrategyName() {
        return "Premium Dynamic Pricing";
    }
}

// Main class demonstrating the Bridge pattern
public class App {
    public static void main(String[] args) {
        System.out.println("=== BRIDGE DESIGN PATTERN EXAMPLE ===\n");
        
        // Create course offerings
        SingleCourse javaCourse = new SingleCourse("Java Fundamentals", 100, 8);
        CourseBundleOffering bundleOffering = new CourseBundleOffering("Java & Python Bundle", 200, 15, 2);
        AnnualSubscription annualSub = new AnnualSubscription("Annual All-Access", 500, 40, 12);
        
        // Scenario 1: Using Standard Pricing with different course types
        System.out.println("Scenario 1: STANDARD PRICING");
        System.out.println("===============================");
        PricingStrategy standardPricing = new StandardPricing();
        
        javaCourse.setPricingStrategy(standardPricing);
        bundleOffering.setPricingStrategy(standardPricing);
        annualSub.setPricingStrategy(standardPricing);
        
        displayOffering(javaCourse, standardPricing);
        displayOffering(bundleOffering, standardPricing);
        displayOffering(annualSub, standardPricing);
        
        // Scenario 2: Switch to Duration-Based Pricing
        System.out.println("\n\nScenario 2: DURATION-BASED PRICING");
        System.out.println("===================================");
        PricingStrategy durationPricing = new DurationBasedPricing();
        
        javaCourse.setPricingStrategy(durationPricing);
        bundleOffering.setPricingStrategy(durationPricing);
        annualSub.setPricingStrategy(durationPricing);
        
        displayOffering(javaCourse, durationPricing);
        displayOffering(bundleOffering, durationPricing);
        displayOffering(annualSub, durationPricing);
        
        // Scenario 3: Switch to Bulk Discount Pricing
        System.out.println("\n\nScenario 3: BULK DISCOUNT PRICING");
        System.out.println("==================================");
        PricingStrategy bulkPricing = new BulkDiscountPricing();
        
        javaCourse.setPricingStrategy(bulkPricing);
        bundleOffering.setPricingStrategy(bulkPricing);
        annualSub.setPricingStrategy(bulkPricing);
        
        displayOffering(javaCourse, bulkPricing);
        displayOffering(bundleOffering, bulkPricing);
        displayOffering(annualSub, bulkPricing);
        
        // Scenario 4: Switch to Premium Dynamic Pricing
        System.out.println("\n\nScenario 4: PREMIUM DYNAMIC PRICING");
        System.out.println("===================================");
        PricingStrategy premiumPricing = new PremiumDynamicPricing();
        
        javaCourse.setPricingStrategy(premiumPricing);
        bundleOffering.setPricingStrategy(premiumPricing);
        annualSub.setPricingStrategy(premiumPricing);
        
        displayOffering(javaCourse, premiumPricing);
        displayOffering(bundleOffering, premiumPricing);
        displayOffering(annualSub, premiumPricing);
        
        // Pattern Explanation
        System.out.println("\n\n--- Bridge Pattern Benefits ---");
        System.out.println("1. Abstraction and Implementation are independent:");
        System.out.println("   - Course types can be modified without touching pricing");
        System.out.println("   - Pricing strategies can be added without touching courses");
        System.out.println("\n2. Easy to add new combinations:");
        System.out.println("   - New course types work with all pricing strategies");
        System.out.println("   - New pricing strategies work with all course types");
        System.out.println("\n3. Avoids class explosion:");
        System.out.println("   - Without Bridge: 3 course types × 4 pricing = 12 classes!");
        System.out.println("   - With Bridge: 3 course types + 4 pricing = 7 classes");
    }
    
    private static void displayOffering(CourseOffering offering, PricingStrategy strategy) {
        System.out.println(offering.getDescription());
        System.out.println("Strategy: " + strategy.getStrategyName());
        System.out.println("Final Price: $" + offering.calculatePrice());
        System.out.println();
    }
}

