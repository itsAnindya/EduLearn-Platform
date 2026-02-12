public class SpecialDiscount extends CartDecorator {
  private static final double DISCOUNT_AMOUNT = 12.0;
  private static final double MIN_DURATION_FOR_DISCOUNT = 5.0;

  public SpecialDiscount(Purchasable cart) {
    super(cart);
  }

  private boolean isEligibleForDiscount() {
    if(super.cart instanceof Cart c) {
      return c.calculateDuration() >= MIN_DURATION_FOR_DISCOUNT;
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
