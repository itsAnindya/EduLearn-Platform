
public class MultiModuleDiscount extends CartDecorator {
  private static final double DISCOUNT_AMOUNT = 15.0;
  private static final int MIN_MODULES_FOR_DISCOUNT = 2;

  public MultiModuleDiscount(Purchasable cart) {
    super(cart);
  }

  private boolean isEligibleForDiscount() {
    return getModuleCount() >= MIN_MODULES_FOR_DISCOUNT;
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
      System.out.println(indent + String.format("Multi-Module Discount Applied: -$%.2f\n", DISCOUNT_AMOUNT));
    }
  }

  @Override
  public int getModuleCount() {
    return super.getModuleCount(); // Return the module count from the decorated cart
  }
}
