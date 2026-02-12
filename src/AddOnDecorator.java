public class AddOnDecorator implements Purchasable {
  private Purchasable purchasable;

  //Constructor
  public AddOnDecorator(Purchasable purchasable) {
    this.purchasable = purchasable;
  }

  @Override
  public double calculatePrice() {
    return purchasable.calculatePrice();
  }

  @Override
  public double calculateDuration() {
    return purchasable.calculateDuration();
  }

  //Getters and Setters
  public Purchasable getPurchasable() {
    return purchasable;
  }

  public void setPurchasable(Purchasable purchasable) {
    this.purchasable = purchasable;
  }
}
