/**
 * COMPOSITE DESIGN PATTERN EXAMPLE
 *
 * The Composite pattern is used to compose objects into tree structures to represent
 * part-whole hierarchies. It allows clients to treat individual objects and compositions
 * of objects uniformly.
 *
 * Use Case in EduLearn:
 * - Lesson → Course → Module creates a tree structure
 * - Each level can calculate total duration and price
 * - Client doesn't need to know if it's a single Lesson or entire Module
 */

// Component Interface - defines operations for both leaf and composite objects
interface CourseComponent {
    double getDuration();  // in hours
    double calculatePrice();
    void displayDetails(String indent);
}

// Leaf Class - represents a Lesson (cannot have children)
class Lesson implements CourseComponent {
    private String name;
    private double duration;  // in hours
    private double price;
    
    public Lesson(String name, double duration, double price) {
        this.name = name;
        this.duration = duration;
        this.price = price;
    }
    
    @Override
    public double getDuration() {
        return duration;
    }
    
    @Override
    public double calculatePrice() {
        return price;
    }
    
    @Override
    public void displayDetails(String indent) {
        System.out.println(indent + "[Lesson] " + name +
                         " | Duration: " + duration + "h | Price: $" + price);
    }
}

// Composite Class - represents a Course (can contain lessons)
class Course implements CourseComponent {
    private String name;
    private java.util.List<CourseComponent> lessons = new java.util.ArrayList<>();
    
    public Course(String name) {
        this.name = name;
    }
    
    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }
    
    @Override
    public double getDuration() {
        // Composite object sums up duration of all children
        return lessons.stream().mapToDouble(CourseComponent::getDuration).sum();
    }
    
    @Override
    public double calculatePrice() {
        // Composite object sums up price of all children
        return lessons.stream().mapToDouble(CourseComponent::calculatePrice).sum();
    }
    
    @Override
    public void displayDetails(String indent) {
        System.out.println(indent + "[Course] " + name +
                         " | Total Duration: " + getDuration() + "h | Total Price: $" + calculatePrice());
        for (CourseComponent lesson : lessons) {
            lesson.displayDetails(indent + "  ");
        }
    }
}

// Composite Class - represents a Module (can contain courses)
class Module implements CourseComponent {
    private String name;
    private java.util.List<CourseComponent> courses = new java.util.ArrayList<>();
    
    public Module(String name) {
        this.name = name;
    }
    
    public void addCourse(Course course) {
        courses.add(course);
    }
    
    @Override
    public double getDuration() {
        // Composite object sums up duration of all children
        return courses.stream().mapToDouble(CourseComponent::getDuration).sum();
    }
    
    @Override
    public double calculatePrice() {
        // Composite object sums up price of all children
        return courses.stream().mapToDouble(CourseComponent::calculatePrice).sum();
    }
    
    @Override
    public void displayDetails(String indent) {
        System.out.println(indent + "[Module] " + name +
                         " | Total Duration: " + getDuration() + "h | Total Price: $" + calculatePrice());
        for (CourseComponent course : courses) {
            course.displayDetails(indent + "  ");
        }
    }
}

// Main class demonstrating the Composite pattern
public class App {
    public static void main(String[] args) {
        System.out.println("=== COMPOSITE PATTERN EXAMPLE ===\n");
        
        // Create lessons
        Lesson lesson1 = new Lesson("Introduction to Java", 2, 20);
        Lesson lesson2 = new Lesson("OOP Concepts", 3, 30);
        Lesson lesson3 = new Lesson("Advanced Java", 4, 40);
        
        // Create courses and add lessons
        Course course1 = new Course("Java Fundamentals");
        course1.addLesson(lesson1);
        course1.addLesson(lesson2);
        
        Course course2 = new Course("Advanced Java Programming");
        course2.addLesson(lesson3);
        
        // Create module and add courses
        Module module = new Module("Java Complete Mastery");
        module.addCourse(course1);
        module.addCourse(course2);
        
        // Display hierarchy
        System.out.println("Course Structure:");
        module.displayDetails("");
        
        // Demonstrate uniform treatment
        System.out.println("\n--- Unified Interface Demonstration ---");
        System.out.println("Getting duration of a Lesson: " + lesson1.getDuration() + " hours");
        System.out.println("Getting duration of a Course: " + course1.getDuration() + " hours");
        System.out.println("Getting duration of a Module: " + module.getDuration() + " hours");
        
        System.out.println("\nChecking out single lesson:");
        checkout(lesson1);
        
        System.out.println("\nChecking out entire course:");
        checkout(course1);
        
        System.out.println("\nChecking out entire module:");
        checkout(module);
    }
    
    // This method demonstrates the unified interface - it works with ANY CourseComponent
    private static void checkout(CourseComponent item) {
        System.out.println("Duration: " + item.getDuration() + " hours");
        System.out.println("Price: $" + item.calculatePrice());
    }
}




