public class LiveMentorSupportDecorator extends AddOnDecorator {
  private static final double PRICE = 20.0;

  public LiveMentorSupportDecorator(Purchasable purchasable) {
    super(purchasable);
  }

  @Override
  public double calculatePrice() {
    return super.calculatePrice() + PRICE;
  }

}
