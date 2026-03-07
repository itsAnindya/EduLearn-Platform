/**
 * COMPOSITE PATTERN – Component Interface
 *
 * <p>Defines the common contract for every node in the content tree: leaf nodes
 * (Lesson) as well as composite nodes (Course, Module, Cart). Because all four
 * classes implement this interface, client code can call {@code calculatePrice()},
 * {@code calculateDuration()}, and {@code printDetails()} on any node without
 * knowing whether it is a single lesson or an entire module.
 */
public interface Purchasable {
  double calculatePrice();

  double calculateDuration(); // in hours

  void printDetails(String indent);

  int getModuleCount(); // Default method to return 0 for non-cart items
}
