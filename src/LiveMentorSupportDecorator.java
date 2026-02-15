public class LiveMentorSupportDecorator extends AddOnDecorator {
  private static final double PRICE = 20.0;

  public LiveMentorSupportDecorator(Purchasable purchasable) {
    super(purchasable);
  }

  @Override
  public double calculatePrice() {
    return super.calculatePrice() + PRICE;
  }

  @Override
  public void printDetails(String indent) {
    super.printDetails(indent);
    System.out.println(indent + String.format("Add-On: Live Mentor Support"));
    System.out.println(indent + String.format("Price: $%.2f", PRICE));
  }

  @Override
  public int getModuleCount() {
    return super.getModuleCount(); // Return the module count from the decorated cart
  }
}
