import java.util.ArrayList;
import java.util.List;

class CatalogSeeder {
  static List<Module> createDefaultCatalog() {
    List<Module> modules = new ArrayList<>();

    Module webDev = new Module("Web Development Fundamentals");
    Course htmlCss = new Course("HTML & CSS Essentials");
    htmlCss.addLesson(new Lesson("HTML Basics", 10.0, 2.0));
    htmlCss.addLesson(new Lesson("CSS Styling", 15.0, 3.0));
    htmlCss.addLesson(new Lesson("Responsive Design", 12.0, 2.5));
    Course javascript = new Course("JavaScript Mastery");
    javascript.addLesson(new Lesson("JS Fundamentals", 20.0, 3.0));
    javascript.addLesson(new Lesson("DOM Manipulation", 18.0, 2.5));
    javascript.addLesson(new Lesson("Async Programming", 25.0, 4.0));
    webDev.addCourse(htmlCss);
    webDev.addCourse(javascript);
    modules.add(webDev);

    Module dataSci = new Module("Data Science Essentials");
    Course python = new Course("Python for Data Science");
    python.addLesson(new Lesson("Python Basics", 15.0, 2.0));
    python.addLesson(new Lesson("NumPy & Pandas", 20.0, 3.0));
    python.addLesson(new Lesson("Data Visualization", 18.0, 2.5));
    Course statistics = new Course("Statistics & Probability");
    statistics.addLesson(new Lesson("Descriptive Statistics", 15.0, 2.0));
    statistics.addLesson(new Lesson("Hypothesis Testing", 20.0, 3.0));
    dataSci.addCourse(python);
    dataSci.addCourse(statistics);
    modules.add(dataSci);

    Module advJava = new Module("Advanced Java Programming");
    Course concurrency = new Course("Concurrency and Multithreading");
    concurrency.addLesson(new Lesson("Thread Basics", 25.0, 3.0));
    concurrency.addLesson(new Lesson("Synchronization", 30.0, 4.0));
    Course design = new Course("Design Patterns in Java");
    design.addLesson(new Lesson("Creational Patterns", 20.0, 2.5));
    design.addLesson(new Lesson("Structural Patterns", 22.0, 3.0));
    design.addLesson(new Lesson("Behavioral Patterns", 24.0, 3.5));
    advJava.addCourse(concurrency);
    advJava.addCourse(design);
    modules.add(advJava);

    return modules;
  }
}
