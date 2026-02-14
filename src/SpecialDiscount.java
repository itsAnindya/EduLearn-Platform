public class SpecialDiscount extends CartDecorator {
  private static final double DISCOUNT_AMOUNT = 12.0;
  private static final double MIN_DURATION_FOR_DISCOUNT = 5.0;

  public SpecialDiscount(Purchasable cart) {
    super(cart);
  }

  private boolean isEligibleForDiscount() {
    return calculateDuration() >= MIN_DURATION_FOR_DISCOUNT;
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
      System.out.println(indent + String.format("Special Discount Applied: -$%.2f\n", DISCOUNT_AMOUNT));
    }
  }

  @Override
  public int getModuleCount() {
    return super.getModuleCount(); // Return the module count from the decorated cart
  }

  @Override
  public double calculateDuration() {
    return super.calculateDuration(); // Return the total duration from the decorated cart
  }
}
