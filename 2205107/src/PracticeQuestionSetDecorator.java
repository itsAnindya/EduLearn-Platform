public class PracticeQuestionSetDecorator extends AddOnDecorator {
  private static final double PRICE = 10.0;

  public PracticeQuestionSetDecorator(Purchasable purchasable) {
    super(purchasable);
  }

  @Override
  public double calculatePrice() {
    return super.calculatePrice() + PRICE;
  }

  @Override
  public void printDetails(String indent) {
    super.printDetails(indent);
    System.out.println(indent + String.format("Add-On: Practice Question Set"));
    System.out.println(indent + String.format("Price: $%.2f", PRICE));
  }

  @Override
  public int getModuleCount() {
    return super.getModuleCount(); // Return the module count from the decorated cart
  }
}
