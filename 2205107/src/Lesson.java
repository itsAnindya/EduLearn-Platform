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
