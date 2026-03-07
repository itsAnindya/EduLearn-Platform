import java.util.ArrayList;

/**
 * COMPOSITE PATTERN – Composite (first level)
 *
 * <p>A {@code Course} is an intermediate composite node that owns an ordered
 * collection of {@link Lesson} objects (the leaves). It implements the
 * {@link Purchasable} interface by iterating over its children and summing their
 * results, so {@link #calculatePrice()} and {@link #calculateDuration()} return
 * aggregate values automatically. Client code that holds a {@code Purchasable}
 * reference treats a {@code Course} identically to a single {@code Lesson}.
 */
public class Course implements Purchasable {
  private String title;
  private final ArrayList<Lesson> lessons;

  // Constructors
  public Course(String title) {
    this.title = title;
    this.lessons = new ArrayList<>();
  }

  public Course(String title, ArrayList<Lesson> lessons) {
    this.title = title;
    this.lessons = lessons;
  }

  // Getters and Setters
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public ArrayList<Lesson> getLessons() {
    return lessons;
  }

  @Override
  public double calculatePrice() {
    double totalPrice = 0;
    for (Lesson lesson : lessons) {
      totalPrice += lesson.calculatePrice();
    }
    return totalPrice;
  }

  @Override
  public double calculateDuration() {
    double totalDuration = 0;
    for (Lesson lesson : lessons) {
      totalDuration += lesson.calculateDuration();
    }
    return totalDuration;
  }

  public boolean addLesson(Lesson lesson) {
    if (!lessons.contains(lesson)) {
      lessons.add(lesson);
      return true;
    }
    return false;
  }

  public boolean removeLesson(Lesson lesson) {
    return lessons.remove(lesson);
  }

  @Override
  public void printDetails(String indent) {
    System.out.println(indent + "Course: " + title);
    System.out.println(indent + "Course Price: $" + String.format("%.2f", calculatePrice()));
    System.out.println(indent + "Total Duration: " + String.format("%.2f", calculateDuration()) + " hours");
    System.out.println(indent + "Lessons:");
    for (int i = 1; i <= lessons.size(); i++) {
      System.out.println(indent + "Lesson " + i + ":");
      lessons.get(i-1).printDetails("  " + indent);
    }
  }

  @Override
  public int getModuleCount() {
    return 0; // Courses do not contain modules, so return 0
  }
}
