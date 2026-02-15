public interface Purchasable {
  double calculatePrice();

  double calculateDuration(); // in hours

  void printDetails(String indent);

  int getModuleCount(); // Default method to return 0 for non-cart items
}
