public abstract class CartDecorator implements Purchasable {
  protected Purchasable cart; //Wrappee

  //Constructor
  public CartDecorator(Purchasable cart) {
    this.cart = cart;
  }

  @Override
  public double calculatePrice() {
    return cart.calculatePrice();
  }

  @Override
  public double calculateDuration() {
    return cart.calculateDuration();
  }

  //Getters and Setters
  public Purchasable getCart() {
    return cart;
  }

  public void setCart(Purchasable cart) {
    this.cart = cart;
  }
}
