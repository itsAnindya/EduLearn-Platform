/**
 * ADAPTER DESIGN PATTERN EXAMPLE
 *
 * The Adapter pattern is used to convert the interface of a class into another interface
 * that clients expect. It allows classes with incompatible interfaces to work together.
 *
 * Use Case in EduLearn:
 * - Integrating different discount systems (legacy payment system, third-party discount APIs)
 * - Converting incompatible discount calculation methods into a unified interface
 * - Allowing the system to use different discount providers transparently
 */

// Target Interface - what the system expects
interface DiscountCalculator {
    double calculateDiscount(double basePrice, double duration);
    String getDiscountName();
}

// Adaptee 1 - Legacy discount system (incompatible interface)
class LegacyFlatDiscountSystem {
    // Old method signature - incompatible with our system
    public double applyFlatDiscount(double amount, int discountPercentage) {
        return amount * (100 - discountPercentage) / 100;
    }
}

// Adaptee 2 - Third-party discount service (incompatible interface)
class ThirdPartyDiscountService {
    // Different interface - takes different parameters
    public double processDiscount(double originalPrice, int thresholdHours, int discountAmount) {
        // Returns discounted price if duration >= threshold
        // This is incompatible with our DiscountCalculator interface
        return originalPrice;  // Simplified
    }
}

// Concrete Adapter 1 - Adapts LegacyFlatDiscountSystem to DiscountCalculator
class LegacyFlatDiscountAdapter implements DiscountCalculator {
    private LegacyFlatDiscountSystem legacySystem = new LegacyFlatDiscountSystem();
    private int discountPercentage;
    
    public LegacyFlatDiscountAdapter(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
    
    @Override
    public double calculateDiscount(double basePrice, double duration) {
        // Adapt the legacy method to our interface
        // The legacy system returns discounted price, we need to return discount amount
        double discountedPrice = legacySystem.applyFlatDiscount(basePrice, discountPercentage);
        return basePrice - discountedPrice;  // Return discount amount, not final price
    }
    
    @Override
    public String getDiscountName() {
        return "Legacy Flat Discount (" + discountPercentage + "% off)";
    }
}

// Concrete Adapter 2 - Adapts ThirdPartyDiscountService to DiscountCalculator
class ThirdPartyDiscountAdapter implements DiscountCalculator {
    private ThirdPartyDiscountService thirdPartyService = new ThirdPartyDiscountService();
    private int thresholdHours;
    private double fixedDiscountAmount;
    
    public ThirdPartyDiscountAdapter(int thresholdHours, double fixedDiscountAmount) {
        this.thresholdHours = thresholdHours;
        this.fixedDiscountAmount = fixedDiscountAmount;
    }
    
    @Override
    public double calculateDiscount(double basePrice, double duration) {
        // Adapt the third-party service to our interface
        if (duration >= thresholdHours) {
            return fixedDiscountAmount;  // Apply discount if duration meets threshold
        }
        return 0;  // No discount
    }
    
    @Override
    public String getDiscountName() {
        return "Third-Party Discount ($" + fixedDiscountAmount + " off for " + thresholdHours + "+ hours)";
    }
}

// Another built-in discount (no adaptation needed - already compatible)
class DevelopingCountryDiscountAdapter implements DiscountCalculator {
    private static final double DISCOUNT_AMOUNT = 10.0;
    
    @Override
    public double calculateDiscount(double basePrice, double duration) {
        return DISCOUNT_AMOUNT;
    }
    
    @Override
    public String getDiscountName() {
        return "Developing Country Student Discount ($10 off)";
    }
}

// Checkout system that uses the unified DiscountCalculator interface
class CheckoutSystem {
    private java.util.List<DiscountCalculator> discounts = new java.util.ArrayList<>();
    
    public void addDiscount(DiscountCalculator discount) {
        discounts.add(discount);
    }
    
    public void checkout(double basePrice, double duration) {
        System.out.println("\n--- Checkout Details ---");
        System.out.println("Base Price: $" + basePrice);
        System.out.println("Duration: " + duration + " hours");
        
        double totalDiscount = 0;
        System.out.println("\nApplicable Discounts:");
        
        for (DiscountCalculator discount : discounts) {
            double discountAmount = discount.calculateDiscount(basePrice, duration);
            if (discountAmount > 0) {
                System.out.println("  [OK] " + discount.getDiscountName() + ": -$" + discountAmount);
                totalDiscount += discountAmount;
            }
        }
        
        if (totalDiscount == 0) {
            System.out.println("  (No discounts applicable)");
        }
        
        double finalPrice = Math.max(0, basePrice - totalDiscount);  // Ensure non-negative
        System.out.println("\nTotal Discount: $" + totalDiscount);
        System.out.println("Final Price: $" + finalPrice);
    }
}

// Main class demonstrating the Adapter pattern
public class App {
    public static void main(String[] args) {
        System.out.println("=== ADAPTER PATTERN EXAMPLE ===\n");
        
        // Scenario 1: Using adapted legacy discount system
        System.out.println("Scenario 1: Legacy Flat Discount System");
        CheckoutSystem checkout1 = new CheckoutSystem();
        // Adapt the old system to work with our new interface
        checkout1.addDiscount(new LegacyFlatDiscountAdapter(15));  // 15% off
        checkout1.checkout(100, 6);
        
        // Scenario 2: Using adapted third-party discount service
        System.out.println("\n\nScenario 2: Third-Party Discount Service");
        CheckoutSystem checkout2 = new CheckoutSystem();
        // Adapt the third-party service to work with our interface
        checkout2.addDiscount(new ThirdPartyDiscountAdapter(5, 12));  // $12 off for 5+ hours
        checkout2.checkout(100, 6);
        
        // Scenario 3: Multiple adapted discounts together
        System.out.println("\n\nScenario 3: Multiple Discounts (Developed Country Student)");
        CheckoutSystem checkout3 = new CheckoutSystem();
        // Mix legacy system, third-party system, and built-in discount
        checkout3.addDiscount(new LegacyFlatDiscountAdapter(10));  // 10% off
        checkout3.addDiscount(new ThirdPartyDiscountAdapter(5, 12));  // $12 off for 5+ hours
        checkout3.addDiscount(new DevelopingCountryDiscountAdapter());  // $10 off
        checkout3.checkout(100, 6);
        
        // Scenario 4: Low price, duration doesn't qualify
        System.out.println("\n\nScenario 4: Low Price, Minimal Duration");
        CheckoutSystem checkout4 = new CheckoutSystem();
        checkout4.addDiscount(new ThirdPartyDiscountAdapter(5, 12));  // Only applies for 5+ hours
        checkout4.addDiscount(new DevelopingCountryDiscountAdapter());
        checkout4.checkout(50, 2);
        
        // Explanation of the pattern
        System.out.println("\n\n--- Pattern Explanation ---");
        System.out.println("Without Adapter Pattern:");
        System.out.println("  - Checkout would need to know about LegacyFlatDiscountSystem");
        System.out.println("  - Checkout would need to know about ThirdPartyDiscountService");
        System.out.println("  - Would need different code paths for different discount systems");
        System.out.println("\nWith Adapter Pattern:");
        System.out.println("  - All discount systems are adapted to DiscountCalculator interface");
        System.out.println("  - Checkout only knows about DiscountCalculator");
        System.out.println("  - New discount systems can be added by creating new adapters");
        System.out.println("  - Flexible and maintainable!");
    }
}


