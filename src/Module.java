import java.util.ArrayList;

public class Module implements Purchasable {
  private String title;
  private final ArrayList<Course> courses;

  // Constructors
  public Module(String title) {
    this.title = title;
    this.courses = new ArrayList<>();
  }

  public Module(String title, ArrayList<Course> courses) {
    this.title = title;
    this.courses = courses;
  }

  // Getters and Setters
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public ArrayList<Course> getCourses() {
    return courses;
  }

  @Override
  public double calculatePrice() {
    double totalPrice = 0;
    for (Course course : courses) {
      totalPrice += course.calculatePrice();
    }
    return totalPrice;
  }

  @Override
  public double calculateDuration() {
    double totalDuration = 0;
    for (Course course : courses) {
      totalDuration += course.calculateDuration();
    }
    return totalDuration;
  }

  public boolean addCourse(Course course) {
    if (!courses.contains(course)) {
      courses.add(course);
      return true;
    }
    return false;
  }

  public boolean removeCourse(Course course) {
    return courses.remove(course);
  }
}
