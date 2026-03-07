/**
 * COMPOSITE PATTERN – Leaf
 *
 * <p>A {@code Lesson} is the smallest, indivisible unit of the content tree. It
 * has no children and directly stores its own {@code duration} and
 * {@code pricePerHour}. Both {@link #calculatePrice()} and
 * {@link #calculateDuration()} simply return the values it owns rather than
 * delegating to child components.
 */
public class Lesson implements Purchasable {
  private String title;
  private double pricePerHour;
  private double duration; // in hours

  // Constructor
  public Lesson(String title, double pricePerHour, double duration) {
    this.title = title;
    this.pricePerHour = pricePerHour;
    this.duration = duration;
  }

  @Override
  public void printDetails(String indent) {
    System.out.println(indent + String.format("Lesson: %s", title));
    System.out.println(indent + String.format("Lesson Price: $%.2f", calculatePrice()));
    System.out.println(indent + String.format("Duration: %.2f hours", duration));
  }

  // Getters and Setters
  @Override
  public double calculatePrice() {
    return pricePerHour * duration;
  }

  @Override
  public double calculateDuration() {
    return duration;
  }

  public String getTitle() {
    return title;
  }

  public double getPricePerHour() {
    return pricePerHour;
  }

  public void setPricePerHour(double pricePerHour) {
    this.pricePerHour = pricePerHour;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDuration(double duration) {
    this.duration = duration;
  }

  @Override
  public int getModuleCount() {
    return 0; // Lessons do not contain modules
  }
}
