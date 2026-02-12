import java.util.List;

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

  public int getModuleCount() {
    int moduleCount = 0;
    for (Purchasable item : items) {
      if (item instanceof Module) {
        moduleCount++;
      }
    }
    return moduleCount;
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
}
