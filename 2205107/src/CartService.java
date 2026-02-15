import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class CartService {
  private final List<Purchasable> items = new ArrayList<>();

  void addItem(Purchasable item) {
    items.add(item);
  }

  boolean isEmpty() {
    return items.isEmpty();
  }

  void clear() {
    items.clear();
  }

  int size() {
    return items.size();
  }

  void removeAt(int index) {
    items.remove(index);
  }

  List<Purchasable> getItems() {
    return Collections.unmodifiableList(items);
  }

  double calculateSubtotal() {
    double total = 0;
    for (Purchasable item : items) {
      total += item.calculatePrice();
    }
    return total;
  }

  Cart toCart() {
    return new Cart(items);
  }
}
