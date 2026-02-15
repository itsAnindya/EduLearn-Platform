public class DevelopingCountryStudentDiscount extends CartDecorator {
  private static final double DISCOUNT_AMOUNT = 10.0;
  private final Customer customer;

  //Constructor
  public DevelopingCountryStudentDiscount(Purchasable cart, Customer customer) {
    super(cart);
    this.customer = customer;
  }

  private boolean isEligibleForDiscount() {
    return customer.isStudent() && customer.isFromDevelopingCountry();
  }

  @Override
  public double calculatePrice() {
    double basePrice = super.calculatePrice();

    if (isEligibleForDiscount()) {
      return Math.max(0, basePrice - DISCOUNT_AMOUNT);
    }
    
    return basePrice;
  }

  @Override
  public void printDetails(String indent) {
    super.printDetails(indent);
    if (isEligibleForDiscount()) {
      System.out.println(indent + String.format("Developing Country Student Discount Applied: -$%.2f\n", DISCOUNT_AMOUNT));
    }
  }
}
