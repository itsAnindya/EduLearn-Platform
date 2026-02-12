
public class MultiModuleDiscount extends CartDecorator {
  private static final double DISCOUNT_AMOUNT = 15.0;
  private static final int MIN_MODULES_FOR_DISCOUNT = 2;

  public MultiModuleDiscount(Purchasable cart) {
    super(cart);
  }

  private boolean isEligibleForDiscount() {
    if(super.cart instanceof Cart c) {
      return c.getModuleCount() >= MIN_MODULES_FOR_DISCOUNT;
    }
    return false;
  }

  @Override
  public double calculatePrice() {
    double basePrice = super.calculatePrice();

    if (isEligibleForDiscount()) {
      return Math.max(0, basePrice - DISCOUNT_AMOUNT);
    }
    
    return basePrice;
  }
}
