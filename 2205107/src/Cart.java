import java.util.List;

/**
 * COMPOSITE PATTERN – Composite (shopping-cart root)
 *
 * <p>A {@code Cart} is a flat composite that can hold <em>any</em>
 * {@link Purchasable} item – a bare {@link Lesson}, a {@link Course}, a
 * {@link Module}, or even a decorator-wrapped variant of any of these. It sums
 * the results of {@link #calculatePrice()}, {@link #calculateDuration()}, and
 * {@link #getModuleCount()} across all children, remaining completely unaware of
 * the concrete type of each item. This is the key benefit of the Composite
 * pattern: the checkout logic works uniformly regardless of what was added to the
 * cart.
 */
public class Cart implements Purchasable {
  private List<Purchasable> items;

  //Constructor
  public Cart(List<Purchasable> items) {
    this.items = items;
  }

  public boolean addItem(Purchasable item) {
    if(!items.contains(item)) {
      items.add(item);
      return true;
    }
    return false;
  }

  public boolean removeItem(Purchasable item) {
    return items.remove(item);
  }

  @Override
  public double calculatePrice() {
    double totalPrice = 0;
    for (Purchasable item : items) {
      totalPrice += item.calculatePrice();
    }
    return totalPrice;
  }

  @Override
  public double calculateDuration() {
    double totalDuration = 0;
    for (Purchasable item : items) {
      totalDuration += item.calculateDuration();
    }
    return totalDuration;
  }

  //Getters and Setters
  public void setItems(List<Purchasable> items) {
    this.items = items;
  }

  public List<Purchasable> getItems() {
    return items;
  }

  @Override
  public void printDetails(String indent) {
    System.out.println(indent + "Cart Details:");
    System.out.printf(indent + "Total Price: $%.2f\n", calculatePrice());
    System.out.printf(indent + "Total Duration: %.2f hours\n", calculateDuration());
    
    for (int i = 1; i <= items.size(); i++) {
      Purchasable item = items.get(i-1);
      System.out.println(indent + "Item " + i + ":");
      item.printDetails("  " + indent);
      System.out.println(indent + "-------------------");
    }
  }

  @Override
  public int getModuleCount() {
    int moduleCount = 0;
    for (Purchasable item : items) {
      moduleCount += item.getModuleCount();
    }
    return moduleCount;
  }
}
