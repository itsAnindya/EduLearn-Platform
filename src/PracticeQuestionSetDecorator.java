public class PracticeQuestionSetDecorator extends AddOnDecorator {
  private static final double PRICE = 10.0;

  public PracticeQuestionSetDecorator(Purchasable purchasable) {
    super(purchasable);
  }

  @Override
  public double calculatePrice() {
    return super.calculatePrice() + PRICE;
  }
}
