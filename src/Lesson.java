public class Lesson implements Purchasable {
  private String title;
  private double pricePerHour;
  private double duration; // in hours

  //Constructor
  public Lesson(String title, double pricePerHour, double duration) {
    this.title = title;
    this.pricePerHour = pricePerHour;
    this.duration = duration;
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
}
