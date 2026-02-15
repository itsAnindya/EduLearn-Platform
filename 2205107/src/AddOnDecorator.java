public abstract class AddOnDecorator implements Purchasable {
  private Purchasable purchasableItem; //Wrappee

  //Constructor
  public AddOnDecorator(Purchasable purchasable) {
    this.purchasableItem = purchasable;
  }

  @Override
  public double calculatePrice() {
    return purchasableItem.calculatePrice();
  }

  @Override
  public double calculateDuration() {
    return purchasableItem.calculateDuration();
  }

  //Getters and Setters
  public Purchasable getPurchasableItem() {
    return purchasableItem;
  }

  public void setPurchasableItem(Purchasable purchasable) {
    this.purchasableItem = purchasable;
  }

  @Override
  public void printDetails(String indent) {
    purchasableItem.printDetails(indent);
  }

  @Override
  public int getModuleCount() {
    return purchasableItem.getModuleCount();
  }
}
