import java.util.ArrayList;

/**
 * COMPOSITE PATTERN – Composite (second level)
 *
 * <p>A {@code Module} is the top-level composite node in the content hierarchy. It
 * owns an ordered collection of {@link Course} objects, each of which may itself
 * contain multiple {@link Lesson} leaves. {@link #calculatePrice()} and
 * {@link #calculateDuration()} delegate recursively to every contained
 * {@code Course}, which in turn delegate to their {@code Lesson} children. The
 * result is that querying price or duration on a {@code Module} transparently
 * traverses the entire sub-tree without any special-case logic in the caller.
 */
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

  @Override
  public void printDetails(String indent) {
    System.out.println(indent + "Module: " + title);
    System.out.println(indent + "Module Price: $" + String.format("%.2f", calculatePrice()));
    System.out.println(indent + "Total Duration: " + String.format("%.2f", calculateDuration()) + " hours");
    
    for (int i = 1; i <= courses.size(); i++) {
      System.out.println(indent + "Course " + i + ":");
      courses.get(i-1).printDetails("  " + indent);
      System.out.println(indent + "-------------------");
    }
  }

  @Override
  public int getModuleCount() {
    return 1; // Each Module counts as 1 module
  }
}
