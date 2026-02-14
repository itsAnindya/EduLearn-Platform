import java.util.ArrayList;
import java.util.List;

public class Test {
  private static int testCount = 0;
  private static int passCount = 0;
  private static int failCount = 0;

  public static void main(String[] args) {
    System.out.println("=".repeat(80));
    System.out.println("EDULEARN COMPREHENSIVE TEST SUITE");
    System.out.println("=".repeat(80));

    // Run all test categories
    testLessons();
    testCourses();
    testModules();
    testCart();
    testAddOnDecorators();
    testCartDecorators();
    testEdgeCases();
    testIntegrationScenarios();
    testAdvancedEdgeCases();
    testDecoratorChaining();
    testBoundaryConditions();
    testStressTests();

    // Print summary
    System.out.println("\n" + "=".repeat(80));
    System.out.println("TEST SUMMARY");
    System.out.println("=".repeat(80));
    System.out.println("Total Tests: " + testCount);
    System.out.println("Passed: " + passCount + " ✓");
    System.out.println("Failed: " + failCount + " ✗");
    System.out.println("Success Rate: " + String.format("%.2f", (passCount * 100.0 / testCount)) + "%");
    System.out.println("=".repeat(80));

    if (failCount == 0) {
      System.out.println("\n🎉 ALL TESTS PASSED! 🎉\n");
    } else {
      System.out.println("\n⚠️  SOME TESTS FAILED ⚠️\n");
    }
  }

  // ========================================================================
  // LESSON TESTS
  // ========================================================================

  private static void testLessons() {
    printHeader("LESSON TESTS");

    // Test 1: Basic lesson creation and price calculation
    Lesson lesson1 = new Lesson("Java Basics", 10.0, 2.0);
    assertEquals(20.0, lesson1.calculatePrice(), "Lesson basic price calculation");

    // Test 2: Lesson with zero price per hour
    Lesson lesson2 = new Lesson("Free Intro", 0.0, 3.0);
    assertEquals(0.0, lesson2.calculatePrice(), "Lesson with zero price per hour");

    // Test 3: Lesson with zero duration
    Lesson lesson3 = new Lesson("Quick Tip", 50.0, 0.0);
    assertEquals(0.0, lesson3.calculatePrice(), "Lesson with zero duration");

    // Test 4: Lesson duration calculation
    assertEquals(2.0, lesson1.calculateDuration(), "Lesson duration");

    // Test 5: Lesson with fractional values
    Lesson lesson4 = new Lesson("Advanced Topic", 15.5, 2.5);
    assertEquals(38.75, lesson4.calculatePrice(), "Lesson with fractional values");

    // Test 6: Lesson with very large numbers
    Lesson lesson5 = new Lesson("Marathon", 100.0, 100.0);
    assertEquals(10000.0, lesson5.calculatePrice(), "Lesson with large values");

    // Test 7: Lesson setters
    lesson1.setPricePerHour(20.0);
    lesson1.setDuration(3.0);
    assertEquals(60.0, lesson1.calculatePrice(), "Lesson after setters");

    System.out.println();
  }

  // ========================================================================
  // COURSE TESTS
  // ========================================================================

  private static void testCourses() {
    printHeader("COURSE TESTS");

    // Test 1: Empty course
    Course course1 = new Course("Empty Course");
    assertEquals(0.0, course1.calculatePrice(), "Empty course price");
    assertEquals(0.0, course1.calculateDuration(), "Empty course duration");

    // Test 2: Course with single lesson
    Course course2 = new Course("Single Lesson Course");
    Lesson lesson1 = new Lesson("Intro", 10.0, 2.0);
    course2.addLesson(lesson1);
    assertEquals(20.0, course2.calculatePrice(), "Course with single lesson price");
    assertEquals(2.0, course2.calculateDuration(), "Course with single lesson duration");

    // Test 3: Course with multiple lessons
    Course course3 = new Course("Multi Lesson Course");
    course3.addLesson(new Lesson("Lesson 1", 10.0, 2.0));
    course3.addLesson(new Lesson("Lesson 2", 15.0, 3.0));
    course3.addLesson(new Lesson("Lesson 3", 20.0, 1.5));
    assertEquals(95.0, course3.calculatePrice(), "Course with multiple lessons price");
    assertEquals(6.5, course3.calculateDuration(), "Course with multiple lessons duration");

    // Test 4: Adding duplicate lesson (should not add)
    Lesson duplicateLesson = new Lesson("Duplicate", 10.0, 1.0);
    Course course4 = new Course("Test Course");
    assertTrue(course4.addLesson(duplicateLesson), "First add should succeed");
    assertFalse(course4.addLesson(duplicateLesson), "Duplicate add should fail");
    assertEquals(1, course4.getLessons().size(), "Course should have only 1 lesson");

    // Test 5: Removing lesson
    Course course5 = new Course("Remove Test");
    Lesson lessonToRemove = new Lesson("Remove Me", 10.0, 1.0);
    course5.addLesson(lessonToRemove);
    course5.addLesson(new Lesson("Keep Me", 10.0, 1.0));
    assertTrue(course5.removeLesson(lessonToRemove), "Remove existing lesson");
    assertEquals(1, course5.getLessons().size(), "Course should have 1 lesson after removal");

    // Test 6: Removing non-existent lesson
    Lesson nonExistent = new Lesson("Not Added", 10.0, 1.0);
    assertFalse(course5.removeLesson(nonExistent), "Remove non-existent lesson should fail");

    // Test 7: Course title setter
    course1.setTitle("New Title");
    assertEquals("New Title", course1.getTitle(), "Course title setter");

    System.out.println();
  }

  // ========================================================================
  // MODULE TESTS
  // ========================================================================

  private static void testModules() {
    printHeader("MODULE TESTS");

    // Test 1: Empty module
    Module module1 = new Module("Empty Module");
    assertEquals(0.0, module1.calculatePrice(), "Empty module price");
    assertEquals(0.0, module1.calculateDuration(), "Empty module duration");

    // Test 2: Module with single course
    Module module2 = new Module("Single Course Module");
    Course course1 = new Course("Course 1");
    course1.addLesson(new Lesson("Lesson 1", 10.0, 2.0));
    module2.addCourse(course1);
    assertEquals(20.0, module2.calculatePrice(), "Module with single course price");
    assertEquals(2.0, module2.calculateDuration(), "Module with single course duration");

    // Test 3: Module with multiple courses
    Module module3 = new Module("Multi Course Module");
    Course course2 = new Course("Course 1");
    course2.addLesson(new Lesson("L1", 10.0, 2.0));
    course2.addLesson(new Lesson("L2", 15.0, 3.0));
    Course course3 = new Course("Course 2");
    course3.addLesson(new Lesson("L3", 20.0, 1.5));
    module3.addCourse(course2);
    module3.addCourse(course3);
    assertEquals(95.0, module3.calculatePrice(), "Module with multiple courses price");
    assertEquals(6.5, module3.calculateDuration(), "Module with multiple courses duration");

    // Test 4: Adding duplicate course (should not add)
    Course duplicateCourse = new Course("Duplicate");
    Module module4 = new Module("Test Module");
    assertTrue(module4.addCourse(duplicateCourse), "First add should succeed");
    assertFalse(module4.addCourse(duplicateCourse), "Duplicate add should fail");
    assertEquals(1, module4.getCourses().size(), "Module should have only 1 course");

    // Test 5: Removing course
    Module module5 = new Module("Remove Test");
    Course courseToRemove = new Course("Remove Me");
    module5.addCourse(courseToRemove);
    module5.addCourse(new Course("Keep Me"));
    assertTrue(module5.removeCourse(courseToRemove), "Remove existing course");
    assertEquals(1, module5.getCourses().size(), "Module should have 1 course after removal");

    // Test 6: Removing non-existent course
    Course nonExistent = new Course("Not Added");
    assertFalse(module5.removeCourse(nonExistent), "Remove non-existent course should fail");

    // Test 7: Module title setter
    module1.setTitle("New Module Title");
    assertEquals("New Module Title", module1.getTitle(), "Module title setter");

    System.out.println();
  }

  // ========================================================================
  // CART TESTS
  // ========================================================================

  private static void testCart() {
    printHeader("CART TESTS");

    // Test 1: Empty cart
    Cart cart1 = new Cart(new ArrayList<>());
    assertEquals(0.0, cart1.calculatePrice(), "Empty cart price");
    assertEquals(0.0, cart1.calculateDuration(), "Empty cart duration");
    assertEquals(0, cart1.getModuleCount(), "Empty cart module count");

    // Test 2: Cart with single lesson
    List<Purchasable> items1 = new ArrayList<>();
    items1.add(new Lesson("Lesson 1", 10.0, 2.0));
    Cart cart2 = new Cart(items1);
    assertEquals(20.0, cart2.calculatePrice(), "Cart with single lesson price");
    assertEquals(2.0, cart2.calculateDuration(), "Cart with single lesson duration");

    // Test 3: Cart with multiple items (mixed types)
    List<Purchasable> items2 = new ArrayList<>();
    Lesson lesson = new Lesson("Lesson", 10.0, 1.0);
    Course course = new Course("Course");
    course.addLesson(new Lesson("CL1", 20.0, 2.0));
    Module module = new Module("Module");
    Course c = new Course("MC1");
    c.addLesson(new Lesson("MCL1", 30.0, 3.0));
    module.addCourse(c);
    items2.add(lesson);
    items2.add(course);
    items2.add(module);
    Cart cart3 = new Cart(items2);
    assertEquals(140.0, cart3.calculatePrice(), "Cart with mixed items price");
    assertEquals(6.0, cart3.calculateDuration(), "Cart with mixed items duration");

    // Test 4: Module count in cart
    assertEquals(1, cart3.getModuleCount(), "Cart module count");

    // Test 5: Cart with multiple modules
    List<Purchasable> items3 = new ArrayList<>();
    items3.add(new Module("Module 1"));
    items3.add(new Module("Module 2"));
    items3.add(new Module("Module 3"));
    Cart cart4 = new Cart(items3);
    assertEquals(3, cart4.getModuleCount(), "Cart with 3 modules count");

    // Test 6: Adding item to cart
    Cart cart5 = new Cart(new ArrayList<>());
    Lesson newLesson = new Lesson("New", 10.0, 1.0);
    assertTrue(cart5.addItem(newLesson), "Add new item to cart");
    assertEquals(1, cart5.getItems().size(), "Cart size after add");

    // Test 7: Adding duplicate item (should not add)
    assertFalse(cart5.addItem(newLesson), "Add duplicate item should fail");
    assertEquals(1, cart5.getItems().size(), "Cart size unchanged after duplicate");

    // Test 8: Removing item from cart
    assertTrue(cart5.removeItem(newLesson), "Remove existing item");
    assertEquals(0, cart5.getItems().size(), "Cart empty after removal");

    // Test 9: Removing non-existent item
    assertFalse(cart5.removeItem(newLesson), "Remove non-existent item should fail");

    // Test 10: Set items
    List<Purchasable> newItems = new ArrayList<>();
    newItems.add(new Lesson("L1", 10.0, 1.0));
    cart5.setItems(newItems);
    assertEquals(1, cart5.getItems().size(), "Cart after setItems");

    System.out.println();
  }

  // ========================================================================
  // ADD-ON DECORATOR TESTS
  // ========================================================================

  private static void testAddOnDecorators() {
    printHeader("ADD-ON DECORATOR TESTS");

    // Test 1: PracticeQuestionSet on Lesson
    Lesson lesson1 = new Lesson("Java", 10.0, 2.0);
    Purchasable withPractice = new PracticeQuestionSetDecorator(lesson1);
    assertEquals(30.0, withPractice.calculatePrice(), "Practice decorator on lesson price");
    assertEquals(2.0, withPractice.calculateDuration(), "Practice decorator keeps duration");

    // Test 2: LiveMentorSupport on Lesson
    Lesson lesson2 = new Lesson("Python", 15.0, 3.0);
    Purchasable withMentor = new LiveMentorSupportDecorator(lesson2);
    assertEquals(65.0, withMentor.calculatePrice(), "Mentor decorator on lesson price");
    assertEquals(3.0, withMentor.calculateDuration(), "Mentor decorator keeps duration");

    // Test 3: Both decorators stacked on Lesson
    Lesson lesson3 = new Lesson("C++", 20.0, 2.0);
    Purchasable stacked = new PracticeQuestionSetDecorator(
        new LiveMentorSupportDecorator(lesson3));
    assertEquals(70.0, stacked.calculatePrice(), "Stacked decorators on lesson price");
    assertEquals(2.0, stacked.calculateDuration(), "Stacked decorators keep duration");

    // Test 4: PracticeQuestionSet on Course
    Course course1 = new Course("Course");
    course1.addLesson(new Lesson("L1", 10.0, 1.0));
    course1.addLesson(new Lesson("L2", 20.0, 2.0));
    Purchasable courseWithPractice = new PracticeQuestionSetDecorator(course1);
    assertEquals(60.0, courseWithPractice.calculatePrice(), "Practice decorator on course price");
    assertEquals(3.0, courseWithPractice.calculateDuration(), "Practice decorator on course duration");

    // Test 5: LiveMentorSupport on Course
    Course course2 = new Course("Course");
    course2.addLesson(new Lesson("L1", 10.0, 1.0));
    Purchasable courseWithMentor = new LiveMentorSupportDecorator(course2);
    assertEquals(30.0, courseWithMentor.calculatePrice(), "Mentor decorator on course price");

    // Test 6: Both decorators on Course
    Course course3 = new Course("Course");
    course3.addLesson(new Lesson("L1", 15.0, 2.0));
    Purchasable courseStacked = new LiveMentorSupportDecorator(
        new PracticeQuestionSetDecorator(course3));
    assertEquals(60.0, courseStacked.calculatePrice(), "Stacked decorators on course price");

    // Test 7: PracticeQuestionSet on Module
    Module module1 = new Module("Module");
    Course c1 = new Course("C1");
    c1.addLesson(new Lesson("L1", 20.0, 2.0));
    module1.addCourse(c1);
    Purchasable moduleWithPractice = new PracticeQuestionSetDecorator(module1);
    assertEquals(50.0, moduleWithPractice.calculatePrice(), "Practice decorator on module price");

    // Test 8: LiveMentorSupport on Module
    Module module2 = new Module("Module");
    Course c2 = new Course("C2");
    c2.addLesson(new Lesson("L1", 30.0, 3.0));
    module2.addCourse(c2);
    Purchasable moduleWithMentor = new LiveMentorSupportDecorator(module2);
    assertEquals(110.0, moduleWithMentor.calculatePrice(), "Mentor decorator on module price");

    // Test 9: Both decorators on Module
    Module module3 = new Module("Module");
    Course c3 = new Course("C3");
    c3.addLesson(new Lesson("L1", 25.0, 2.0));
    module3.addCourse(c3);
    Purchasable moduleStacked = new PracticeQuestionSetDecorator(
        new LiveMentorSupportDecorator(module3));
    assertEquals(80.0, moduleStacked.calculatePrice(), "Stacked decorators on module price");

    // Test 10: Decorator on zero-price item
    Lesson freeLesson = new Lesson("Free", 0.0, 1.0);
    Purchasable decoratedFree = new PracticeQuestionSetDecorator(
        new LiveMentorSupportDecorator(freeLesson));
    assertEquals(30.0, decoratedFree.calculatePrice(), "Decorators on zero-price item");

    // Test 11: Reverse stacking order
    Lesson lesson4 = new Lesson("Test", 10.0, 1.0);
    Purchasable stack1 = new PracticeQuestionSetDecorator(
        new LiveMentorSupportDecorator(lesson4));
    Lesson lesson5 = new Lesson("Test", 10.0, 1.0);
    Purchasable stack2 = new LiveMentorSupportDecorator(
        new PracticeQuestionSetDecorator(lesson5));
    assertEquals(stack1.calculatePrice(), stack2.calculatePrice(),
        "Decorator stacking order doesn't affect price");

    System.out.println();
  }

  // ========================================================================
  // CART DECORATOR TESTS
  // ========================================================================

  private static void testCartDecorators() {
    printHeader("CART DECORATOR TESTS");

    // Test 1: MultiModuleDiscount - No modules (no discount)
    List<Purchasable> items1 = new ArrayList<>();
    items1.add(new Lesson("L1", 50.0, 2.0));
    Cart cart1 = new Cart(items1);
    Purchasable discounted1 = new MultiModuleDiscount(cart1);
    assertEquals(100.0, discounted1.calculatePrice(), "MultiModule discount with 0 modules");

    // Test 2: MultiModuleDiscount - 1 module (no discount)
    List<Purchasable> items2 = new ArrayList<>();
    Module m1 = new Module("M1");
    Course c1 = new Course("C1");
    c1.addLesson(new Lesson("L1", 50.0, 2.0));
    m1.addCourse(c1);
    items2.add(m1);
    Cart cart2 = new Cart(items2);
    Purchasable discounted2 = new MultiModuleDiscount(cart2);
    assertEquals(100.0, discounted2.calculatePrice(), "MultiModule discount with 1 module");

    // Test 3: MultiModuleDiscount - 2 modules (discount applied)
    List<Purchasable> items3 = new ArrayList<>();
    Module m2 = new Module("M1");
    Module m3 = new Module("M2");
    Course c2 = new Course("C1");
    c2.addLesson(new Lesson("L1", 50.0, 2.0));
    m2.addCourse(c2);
    items3.add(m2);
    items3.add(m3);
    Cart cart3 = new Cart(items3);
    Purchasable discounted3 = new MultiModuleDiscount(cart3);
    assertEquals(85.0, discounted3.calculatePrice(), "MultiModule discount with 2 modules");

    // Test 4: MultiModuleDiscount - 3 modules (discount applied)
    List<Purchasable> items4 = new ArrayList<>();
    items4.add(new Module("M1"));
    items4.add(new Module("M2"));
    items4.add(new Module("M3"));
    Cart cart4 = new Cart(items4);
    cart4.getItems().forEach(m -> {
      if (m instanceof Module mod) {
        Course c = new Course("C");
        c.addLesson(new Lesson("L", 20.0, 1.0));
        mod.addCourse(c);
      }
    });
    Purchasable discounted4 = new MultiModuleDiscount(cart4);
    assertEquals(45.0, discounted4.calculatePrice(), "MultiModule discount with 3 modules");

    // Test 5: SpecialDiscount - Duration < 5 hours (no discount)
    List<Purchasable> items5 = new ArrayList<>();
    items5.add(new Lesson("L1", 50.0, 2.0));
    Cart cart5 = new Cart(items5);
    Purchasable specialDiscount1 = new SpecialDiscount(cart5);
    assertEquals(100.0, specialDiscount1.calculatePrice(), "Special discount with < 5 hours");

    // Test 6: SpecialDiscount - Duration = 5 hours (discount applied)
    List<Purchasable> items6 = new ArrayList<>();
    items6.add(new Lesson("L1", 50.0, 5.0));
    Cart cart6 = new Cart(items6);
    Purchasable specialDiscount2 = new SpecialDiscount(cart6);
    assertEquals(238.0, specialDiscount2.calculatePrice(), "Special discount with 5 hours");

    // Test 7: SpecialDiscount - Duration > 5 hours (discount applied)
    List<Purchasable> items7 = new ArrayList<>();
    items7.add(new Lesson("L1", 10.0, 6.0));
    Cart cart7 = new Cart(items7);
    Purchasable specialDiscount3 = new SpecialDiscount(cart7);
    assertEquals(48.0, specialDiscount3.calculatePrice(), "Special discount with > 5 hours");

    // Test 8: DevelopingCountryStudentDiscount - Not student (no discount)
    Customer notStudent = new Customer("John", true, false);
    List<Purchasable> items8 = new ArrayList<>();
    items8.add(new Lesson("L1", 50.0, 2.0));
    Cart cart8 = new Cart(items8);
    Purchasable dev1 = new DevelopingCountryStudentDiscount(cart8, notStudent);
    assertEquals(100.0, dev1.calculatePrice(), "Dev country discount - not student");

    // Test 9: DevelopingCountryStudentDiscount - Not from developing country (no
    // discount)
    Customer notDevCountry = new Customer("Jane", false, true);
    List<Purchasable> items9 = new ArrayList<>();
    items9.add(new Lesson("L1", 50.0, 2.0));
    Cart cart9 = new Cart(items9);
    Purchasable dev2 = new DevelopingCountryStudentDiscount(cart9, notDevCountry);
    assertEquals(100.0, dev2.calculatePrice(), "Dev country discount - not from dev country");

    // Test 10: DevelopingCountryStudentDiscount - Eligible (discount applied)
    Customer eligible = new Customer("Ahmed", true, true);
    List<Purchasable> items10 = new ArrayList<>();
    items10.add(new Lesson("L1", 50.0, 2.0));
    Cart cart10 = new Cart(items10);
    Purchasable dev3 = new DevelopingCountryStudentDiscount(cart10, eligible);
    assertEquals(90.0, dev3.calculatePrice(), "Dev country discount - eligible");

    // Test 11: Not eligible - neither student nor from dev country
    Customer notEligible = new Customer("Bob", false, false);
    List<Purchasable> items11 = new ArrayList<>();
    items11.add(new Lesson("L1", 50.0, 2.0));
    Cart cart11 = new Cart(items11);
    Purchasable dev4 = new DevelopingCountryStudentDiscount(cart11, notEligible);
    assertEquals(100.0, dev4.calculatePrice(), "Dev country discount - not eligible");

    // Test 12: Stacking cart decorators
    List<Purchasable> items12 = new ArrayList<>();
    Module m4 = new Module("M1");
    Module m5 = new Module("M2");
    Course c3 = new Course("C1");
    c3.addLesson(new Lesson("L1", 50.0, 3.0));
    m4.addCourse(c3);
    items12.add(m4);
    items12.add(m5);
    Cart cart12 = new Cart(items12);
    Customer eligibleStudent = new Customer("Student", true, true);
    Purchasable stacked = new MultiModuleDiscount(
        new DevelopingCountryStudentDiscount(
            new SpecialDiscount(cart12),
            eligibleStudent));
    // Base: 150, Duration 3h (no Special), Dev country -10 = 140, MultiModule -15 =
    // 125
    assertEquals(125.0, stacked.calculatePrice(), "All cart discounts stacked");

    // Test 13: Price cannot go below zero
    List<Purchasable> items13 = new ArrayList<>();
    items13.add(new Lesson("Cheap", 1.0, 1.0));
    Cart cart13 = new Cart(items13);
    Customer student = new Customer("Student", true, true);
    Purchasable heavyDiscount = new MultiModuleDiscount(
        new DevelopingCountryStudentDiscount(
            new SpecialDiscount(cart13),
            student));
    assertTrue(heavyDiscount.calculatePrice() >= 0, "Price should not be negative");

    System.out.println();
  }

  // ========================================================================
  // EDGE CASE TESTS
  // ========================================================================

  private static void testEdgeCases() {
    printHeader("EDGE CASE TESTS");

    // Test 1: Decorating a decorated item in cart
    Lesson lesson = new Lesson("Base", 10.0, 1.0);
    Purchasable decorated = new PracticeQuestionSetDecorator(
        new LiveMentorSupportDecorator(lesson));
    List<Purchasable> items = new ArrayList<>();
    items.add(decorated);
    Cart cart = new Cart(items);
    assertEquals(40.0, cart.calculatePrice(), "Cart with decorated item");

    // Test 2: Cart with mix of decorated and non-decorated items
    List<Purchasable> mixedItems = new ArrayList<>();
    mixedItems.add(new Lesson("Plain", 10.0, 1.0));
    mixedItems.add(new PracticeQuestionSetDecorator(new Lesson("Decorated", 10.0, 1.0)));
    Cart mixedCart = new Cart(mixedItems);
    assertEquals(30.0, mixedCart.calculatePrice(), "Cart with mixed items");

    // Test 3: Empty course in module
    Module module = new Module("Module");
    Course emptyCourse = new Course("Empty");
    module.addCourse(emptyCourse);
    assertEquals(0.0, module.calculatePrice(), "Module with empty course");

    // Test 4: Very large discount scenario
    List<Purchasable> largeItems = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      Module m = new Module("M" + i);
      Course c = new Course("C" + i);
      c.addLesson(new Lesson("L" + i, 100.0, 10.0));
      m.addCourse(c);
      largeItems.add(m);
    }
    Cart largeCart = new Cart(largeItems);
    Customer student = new Customer("Student", true, true);
    Purchasable manyDiscounts = new MultiModuleDiscount(
        new DevelopingCountryStudentDiscount(
            new SpecialDiscount(largeCart),
            student));
    assertTrue(manyDiscounts.calculatePrice() > 0, "Large cart with all discounts");

    // Test 5: Decorator on item with zero price
    Lesson freeLesson = new Lesson("Free", 0.0, 5.0);
    Purchasable decoratedFree = new LiveMentorSupportDecorator(freeLesson);
    assertEquals(20.0, decoratedFree.calculatePrice(), "Decorator on free item");
    assertEquals(5.0, decoratedFree.calculateDuration(), "Duration preserved on free item");

    // Test 6: Cart discount on cart with only lessons (no modules)
    List<Purchasable> onlyLessons = new ArrayList<>();
    onlyLessons.add(new Lesson("L1", 50.0, 6.0));
    onlyLessons.add(new Lesson("L2", 50.0, 1.0));
    Cart lessonCart = new Cart(onlyLessons);
    Purchasable discounted = new MultiModuleDiscount(lessonCart);
    assertEquals(350.0, discounted.calculatePrice(), "MultiModule discount with no modules");

    // Test 7: Multiple decorators in reverse order
    Lesson base = new Lesson("Test", 20.0, 2.0);
    Purchasable order1 = new PracticeQuestionSetDecorator(
        new LiveMentorSupportDecorator(base));
    Lesson base2 = new Lesson("Test", 20.0, 2.0);
    Purchasable order2 = new LiveMentorSupportDecorator(
        new PracticeQuestionSetDecorator(base2));
    assertEquals(order1.calculatePrice(), order2.calculatePrice(),
        "Decorator order shouldn't matter for price");

    // Test 8: Cart with single module at boundary (exactly 2 for discount)
    List<Purchasable> boundaryItems = new ArrayList<>();
    Module m1 = new Module("M1");
    Module m2 = new Module("M2");
    Course c1 = new Course("C1");
    c1.addLesson(new Lesson("L1", 50.0, 1.0));
    m1.addCourse(c1);
    boundaryItems.add(m1);
    boundaryItems.add(m2);
    Cart boundaryCart = new Cart(boundaryItems);
    Purchasable boundaryDiscount = new MultiModuleDiscount(boundaryCart);
    assertEquals(35.0, boundaryDiscount.calculatePrice(), "Boundary case - exactly 2 modules");

    // Test 9: Duration boundary for special discount (exactly 5.0)
    List<Purchasable> durationBoundary = new ArrayList<>();
    durationBoundary.add(new Lesson("L1", 20.0, 5.0));
    Cart durationCart = new Cart(durationBoundary);
    Purchasable durationDiscount = new SpecialDiscount(durationCart);
    assertEquals(88.0, durationDiscount.calculatePrice(), "Boundary case - exactly 5 hours");

    // Test 10: Customer attribute changes
    Customer customer = new Customer("Test", false, false);
    List<Purchasable> custItems = new ArrayList<>();
    custItems.add(new Lesson("L1", 50.0, 1.0));
    Cart custCart = new Cart(custItems);
    Purchasable before = new DevelopingCountryStudentDiscount(custCart, customer);
    assertEquals(50.0, before.calculatePrice(), "Before customer becomes eligible");

    customer.setFromDevelopingCountry(true);
    customer.setStudentStatus(true);
    Purchasable after = new DevelopingCountryStudentDiscount(custCart, customer);
    assertEquals(40.0, after.calculatePrice(), "After customer becomes eligible");

    System.out.println();
  }

  // ========================================================================
  // INTEGRATION TESTS
  // ========================================================================

  private static void testIntegrationScenarios() {
    printHeader("INTEGRATION SCENARIO TESTS");

    // Scenario 1: Complete checkout - Developing country student with module bundle
    System.out.println("Scenario 1: Developing country student buying module bundle");
    Customer student1 = new Customer("Ahmed", true, true);

    Module webDev = new Module("Web Development");
    Course html = new Course("HTML & CSS");
    html.addLesson(new Lesson("HTML Basics", 10.0, 2.0));
    html.addLesson(new Lesson("CSS Styling", 15.0, 3.0));
    Course js = new Course("JavaScript");
    js.addLesson(new Lesson("JS Fundamentals", 20.0, 2.5));
    webDev.addCourse(html);
    webDev.addCourse(js);

    Module dataSci = new Module("Data Science");
    Course python = new Course("Python");
    python.addLesson(new Lesson("Python Basics", 15.0, 3.0));
    dataSci.addCourse(python);

    List<Purchasable> cart1Items = new ArrayList<>();
    cart1Items.add(new PracticeQuestionSetDecorator(webDev));
    cart1Items.add(new LiveMentorSupportDecorator(dataSci));

    Cart cart1 = new Cart(cart1Items);
    Purchasable checkout1 = new MultiModuleDiscount(
        new DevelopingCountryStudentDiscount(
            new SpecialDiscount(cart1),
            student1));

    double expected1 = 120.0 + 10.0 + 45.0 + 20.0 - 12.0 - 10.0 - 15.0; // 153.0
    assertEquals(153.0, checkout1.calculatePrice(), "Scenario 1 total price");
    System.out.println("  Base: $120 (WebDev) + $45 (DataSci)");
    System.out.println("  Add-ons: +$10 (Practice) +$20 (Mentor)");
    System.out.println("  Discounts: -$12 (Special) -$10 (Dev) -$15 (Multi)");
    System.out.println("  Final: $" + checkout1.calculatePrice());

    // Scenario 2: Non-student buying single decorated course
    System.out.println("\nScenario 2: Regular customer buying decorated course");
    Customer regular = new Customer("John", false, false);

    Course javaCourse = new Course("Java Mastery");
    javaCourse.addLesson(new Lesson("Intro to Java", 20.0, 2.0));
    javaCourse.addLesson(new Lesson("OOP Concepts", 25.0, 3.0));
    javaCourse.addLesson(new Lesson("Advanced Java", 30.0, 2.5));

    Purchasable decoratedCourse = new LiveMentorSupportDecorator(
        new PracticeQuestionSetDecorator(javaCourse));

    List<Purchasable> cart2Items = new ArrayList<>();
    cart2Items.add(decoratedCourse);
    Cart cart2 = new Cart(cart2Items);

    Purchasable checkout2 = new SpecialDiscount(
        new DevelopingCountryStudentDiscount(cart2, regular));

    double expected2 = 40.0 + 75.0 + 75.0 + 10.0 + 20.0 - 12.0; // 208.0
    assertEquals(208.0, checkout2.calculatePrice(), "Scenario 2 total price");
    System.out.println("  Base: $" + javaCourse.calculatePrice());
    System.out.println("  Add-ons: +$30");
    System.out.println("  Discounts: -$12 (Special only)");
    System.out.println("  Final: $" + checkout2.calculatePrice());

    // Scenario 3: Mixed cart with decorated and non-decorated items
    System.out.println("\nScenario 3: Mixed cart with various items");
    Customer student2 = new Customer("Maria", true, true);

    Lesson quickLesson = new Lesson("Quick Tip", 5.0, 0.5);
    Course fullCourse = new Course("Full Course");
    fullCourse.addLesson(new Lesson("L1", 20.0, 1.5));
    fullCourse.addLesson(new Lesson("L2", 20.0, 1.5));
    Module fullModule = new Module("Full Module");
    Course mc = new Course("MC");
    mc.addLesson(new Lesson("MC1", 30.0, 2.0));
    fullModule.addCourse(mc);

    List<Purchasable> cart3Items = new ArrayList<>();
    cart3Items.add(quickLesson);
    cart3Items.add(new PracticeQuestionSetDecorator(fullCourse));
    cart3Items.add(new LiveMentorSupportDecorator(
        new PracticeQuestionSetDecorator(fullModule)));

    Cart cart3 = new Cart(cart3Items);
    Purchasable checkout3 = new MultiModuleDiscount(
        new DevelopingCountryStudentDiscount(
            new SpecialDiscount(cart3),
            student2));

    assertTrue(checkout3.calculatePrice() > 0, "Scenario 3 positive price");
    System.out.println("  Quick Lesson: $" + quickLesson.calculatePrice());
    System.out.println("  Decorated Course: $" + (fullCourse.calculatePrice() + 10.0));
    System.out.println("  Decorated Module: $" + (fullModule.calculatePrice() + 30.0));
    System.out.println("  After discounts: $" + checkout3.calculatePrice());

    // Scenario 4: Maximum decorators and discounts
    System.out.println("\nScenario 4: Maximum decorators and discounts");
    Customer maxStudent = new Customer("Max", true, true);

    List<Purchasable> maxItems = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      Module m = new Module("Module " + (i + 1));
      Course c = new Course("Course");
      c.addLesson(new Lesson("Lesson", 20.0, 2.5));
      m.addCourse(c);
      maxItems.add(new LiveMentorSupportDecorator(
          new PracticeQuestionSetDecorator(m)));
    }

    Cart maxCart = new Cart(maxItems);
    Purchasable maxCheckout = new MultiModuleDiscount(
        new DevelopingCountryStudentDiscount(
            new SpecialDiscount(maxCart),
            maxStudent));

    double maxExpected = (50.0 + 30.0) * 3 - 12.0 - 10.0 - 15.0; // 203.0
    assertEquals(203.0, maxCheckout.calculatePrice(), "Scenario 4 maximum stack");
    System.out.println("  3 modules with both decorators each");
    System.out.println("  All discounts applied");
    System.out.println("  Final: $" + maxCheckout.calculatePrice());

    // Scenario 5: Empty and minimal cases
    System.out.println("\nScenario 5: Edge cases - empty and minimal");

    Cart emptyCart = new Cart(new ArrayList<>());
    Customer anyCustomer = new Customer("Any", true, true);
    Purchasable emptyCheckout = new MultiModuleDiscount(
        new DevelopingCountryStudentDiscount(
            new SpecialDiscount(emptyCart),
            anyCustomer));
    assertEquals(0.0, emptyCheckout.calculatePrice(), "Empty cart with all decorators");

    // Scenario 6: Price floor test (cannot go negative)
    System.out.println("\nScenario 6: Price floor - heavy discounts on cheap item");
    List<Purchasable> cheapItems = new ArrayList<>();
    Module m1 = new Module("Cheap1");
    Module m2 = new Module("Cheap2");
    Course c = new Course("C");
    c.addLesson(new Lesson("L", 1.0, 6.0));
    m1.addCourse(c);
    cheapItems.add(m1);
    cheapItems.add(m2);

    Cart cheapCart = new Cart(cheapItems);
    Customer cheapStudent = new Customer("Student", true, true);
    Purchasable cheapCheckout = new MultiModuleDiscount(
        new DevelopingCountryStudentDiscount(
            new SpecialDiscount(cheapCart),
            cheapStudent));

    assertTrue(cheapCheckout.calculatePrice() >= 0.0, "Price should not be negative");
    System.out.println("  Base price: $" + cheapCart.calculatePrice());
    System.out.println("  After all discounts: $" + cheapCheckout.calculatePrice());
    System.out.println("  (Capped at $0 minimum)");

    System.out.println();
  }

  // ========================================================================
  // ADVANCED EDGE CASE TESTS
  // ========================================================================

  private static void testAdvancedEdgeCases() {
    printHeader("ADVANCED EDGE CASE TESTS");

    // Test 1: Deep nesting - Module with many courses with many lessons
    Module deepModule = new Module("Deep Module");
    for (int i = 0; i < 5; i++) {
      Course course = new Course("Course " + i);
      for (int j = 0; j < 5; j++) {
        course.addLesson(new Lesson("Lesson " + j, 2.0, 0.5));
      }
      deepModule.addCourse(course);
    }
    assertEquals(25.0, deepModule.calculatePrice(), "Deep nesting price");
    assertEquals(12.5, deepModule.calculateDuration(), "Deep nesting duration");
    assertEquals(1, deepModule.getModuleCount(), "Deep module count");

    // Test 2: Cart with only decorated items (no raw items)
    List<Purchasable> decoratedOnly = new ArrayList<>();
    decoratedOnly.add(new PracticeQuestionSetDecorator(new Lesson("L1", 10.0, 1.0))); // 10+10=20
    decoratedOnly.add(new LiveMentorSupportDecorator(new Lesson("L2", 15.0, 1.5))); // 22.5+20=42.5
    Cart decoratedCart = new Cart(decoratedOnly);
    assertEquals(62.5, decoratedCart.calculatePrice(), "Cart with only decorated items");

    // Test 3: Multiple levels of same decorator on different items
    Lesson base1 = new Lesson("Base1", 20.0, 1.0);
    Lesson base2 = new Lesson("Base2", 30.0, 2.0);
    Purchasable decorated1 = new PracticeQuestionSetDecorator(
        new PracticeQuestionSetDecorator(base1)); // 20+10+10=40
    Purchasable decorated2 = new PracticeQuestionSetDecorator(base2); // 60+10=70
    List<Purchasable> multiDecor = new ArrayList<>();
    multiDecor.add(decorated1);
    multiDecor.add(decorated2);
    Cart multiDecorCart = new Cart(multiDecor);
    assertEquals(110.0, multiDecorCart.calculatePrice(), "Multiple same decorators");

    // Test 4: Cart discount on cart with single item
    List<Purchasable> singleItem = new ArrayList<>();
    singleItem.add(new Lesson("Solo", 50.0, 6.0)); // 50*6=300
    Cart soloCart = new Cart(singleItem);
    Purchasable soloDiscount = new SpecialDiscount(soloCart); // 300-12=288
    assertEquals(288.0, soloDiscount.calculatePrice(), "Discount on single item cart");

    // Test 5: Empty course in module
    Module moduleWithEmpty = new Module("Module");
    moduleWithEmpty.addCourse(new Course("Empty"));
    moduleWithEmpty.addCourse(new Course("Also Empty"));
    assertEquals(0.0, moduleWithEmpty.calculatePrice(), "Module with empty courses");
    assertEquals(1, moduleWithEmpty.getModuleCount(), "Empty courses still count as module");

    // Test 6: Customer eligibility combinations
    Customer student = new Customer("A", true, false);
    Customer devCountry = new Customer("B", false, true);
    Customer neither = new Customer("C", false, false);
    Customer both = new Customer("D", true, true);

    List<Purchasable> testItems = new ArrayList<>();
    testItems.add(new Lesson("L", 50.0, 1.0));
    Cart testCart = new Cart(testItems);

    assertEquals(50.0, new DevelopingCountryStudentDiscount(testCart, student).calculatePrice(),
        "Student only - no discount");
    assertEquals(50.0, new DevelopingCountryStudentDiscount(testCart, devCountry).calculatePrice(),
        "Dev country only - no discount");
    assertEquals(50.0, new DevelopingCountryStudentDiscount(testCart, neither).calculatePrice(),
        "Neither - no discount");
    assertEquals(40.0, new DevelopingCountryStudentDiscount(testCart, both).calculatePrice(),
        "Both - discount applies");

    // Test 7: Decorator on decorated cart
    List<Purchasable> cartItems = new ArrayList<>();
    Module m1 = new Module("M1");
    m1.addCourse(new Course("C1"));
    cartItems.add(m1);
    Cart innerCart = new Cart(cartItems);
    Purchasable discounted = new MultiModuleDiscount(innerCart);

    List<Purchasable> outerItems = new ArrayList<>();
    outerItems.add(discounted); // Adding a decorated cart to another cart
    Cart outerCart = new Cart(outerItems);
    assertEquals(0.0, outerCart.calculatePrice(), "Cart containing decorated cart");

    // Test 8: Very small fractional prices
    Lesson microLesson = new Lesson("Micro", 0.01, 0.01);
    assertEquals(0.0001, microLesson.calculatePrice(), "Micro price calculation");

    // Test 9: Duration exactly at boundary after decorators
    List<Purchasable> boundaryItems = new ArrayList<>();
    boundaryItems.add(new Lesson("L1", 10.0, 4.0)); // 40
    boundaryItems.add(new PracticeQuestionSetDecorator(new Lesson("L2", 10.0, 1.0))); // 10+10=20, Total 5.0h
    Cart boundaryCart = new Cart(boundaryItems);
    assertEquals(5.0, boundaryCart.calculateDuration(), "Duration boundary with decorators");
    Purchasable boundarySpecial = new SpecialDiscount(boundaryCart); // 60-12=48
    assertEquals(48.0, boundarySpecial.calculatePrice(), "Special discount at exact boundary");

    // Test 10: Module count with nested decorated modules
    Module mod1 = new Module("Mod1");
    mod1.addCourse(new Course("C"));
    Module mod2 = new Module("Mod2");
    mod2.addCourse(new Course("C"));

    List<Purchasable> nestedMods = new ArrayList<>();
    nestedMods.add(new PracticeQuestionSetDecorator(mod1));
    nestedMods.add(new LiveMentorSupportDecorator(
        new PracticeQuestionSetDecorator(mod2)));
    Cart nestedCart = new Cart(nestedMods);
    assertEquals(2, nestedCart.getModuleCount(), "Module count through nested decorators");

    System.out.println();
  }

  // ========================================================================
  // DECORATOR CHAINING TESTS
  // ========================================================================

  private static void testDecoratorChaining() {
    printHeader("DECORATOR CHAINING TESTS");

    // Test 1: Triple decorator chaining on lesson
    Lesson base = new Lesson("Base", 10.0, 1.0);
    Purchasable triple = new LiveMentorSupportDecorator(
        new PracticeQuestionSetDecorator(
            new LiveMentorSupportDecorator(base)));
    assertEquals(60.0, triple.calculatePrice(), "Triple decorator chain");
    assertEquals(1.0, triple.calculateDuration(), "Duration preserved through triple chain");

    // Test 2: Alternating decorators
    Purchasable alternating = new PracticeQuestionSetDecorator(
        new LiveMentorSupportDecorator(
            new PracticeQuestionSetDecorator(
                new LiveMentorSupportDecorator(base))));
    assertEquals(70.0, alternating.calculatePrice(), "Alternating decorator pattern");

    // Test 3: All cart discounts in different orders
    List<Purchasable> items = new ArrayList<>();
    Module m1 = new Module("M1");
    m1.addCourse(new Course("C1"));
    Module m2 = new Module("M2");
    Course c = new Course("C");
    c.addLesson(new Lesson("L", 10.0, 1.0));
    m2.addCourse(c);
    items.add(m1);
    items.add(m2);
    Cart baseCart = new Cart(items);

    Customer eligible = new Customer("Student", true, true);

    // Order 1: Multi -> Dev -> Special
    Purchasable order1 = new SpecialDiscount(
        new DevelopingCountryStudentDiscount(
            new MultiModuleDiscount(baseCart), eligible));

    // Order 2: Special -> Multi -> Dev
    Purchasable order2 = new DevelopingCountryStudentDiscount(
        new MultiModuleDiscount(
            new SpecialDiscount(baseCart)),
        eligible);

    // Order 3: Dev -> Special -> Multi
    Purchasable order3 = new MultiModuleDiscount(
        new SpecialDiscount(
            new DevelopingCountryStudentDiscount(baseCart, eligible)));

    // All orders should give same result (commutativity of discounts)
    double price1 = order1.calculatePrice();
    double price2 = order2.calculatePrice();
    double price3 = order3.calculatePrice();

    assertTrue(Math.abs(price1 - price2) < 0.01, "Order 1 vs Order 2 same price");
    assertTrue(Math.abs(price2 - price3) < 0.01, "Order 2 vs Order 3 same price");
    assertTrue(Math.abs(price1 - price3) < 0.01, "Order 1 vs Order 3 same price");

    // Test 4: Cart discount on heavily decorated items
    List<Purchasable> heavyItems = new ArrayList<>();
    Module hm1 = new Module("HM1");
    Course hc1 = new Course("HC1");
    hc1.addLesson(new Lesson("HL1", 25.0, 3.0)); // 75
    hm1.addCourse(hc1);

    Purchasable decorated = new LiveMentorSupportDecorator(
        new PracticeQuestionSetDecorator(hm1)); // 75 + 10 + 20 = 105

    heavyItems.add(decorated);
    Cart heavyCart = new Cart(heavyItems);
    Purchasable heavyDiscount = new SpecialDiscount(heavyCart); // 105 (no discount, only 3h)
    assertEquals(105.0, heavyDiscount.calculatePrice(), "Discount on heavily decorated items");

    // Test 5: Multiple carts with different discount combinations
    List<Purchasable> items1 = new ArrayList<>();
    items1.add(new Lesson("L1", 100.0, 6.0)); // 600
    Cart cart1 = new Cart(items1);

    List<Purchasable> items2 = new ArrayList<>();
    Module m = new Module("M");
    Course co = new Course("CO");
    co.addLesson(new Lesson("L2", 50.0, 3.0)); // 150
    m.addCourse(co);
    items2.add(m);
    items2.add(m); // Duplicate module - 300 total
    Cart cart2 = new Cart(items2);

    Purchasable d1 = new SpecialDiscount(cart1); // 600 - 12 = 588
    Purchasable d2 = new MultiModuleDiscount(cart2); // 300 - 15 = 285

    assertEquals(588.0, d1.calculatePrice(), "Special discount only");
    assertEquals(285.0, d2.calculatePrice(), "Multi module discount only");

    // Test 6: Decorator preservation through cart operations
    Lesson lesson = new Lesson("Test", 30.0, 2.0); // 60
    Purchasable decorated1 = new PracticeQuestionSetDecorator(lesson); // 60+10=70

    List<Purchasable> cartItems = new ArrayList<>();
    cartItems.add(decorated1);
    Cart cart = new Cart(cartItems);

    assertEquals(70.0, cart.calculatePrice(), "Decorator preserved in cart");
    assertEquals(2.0, cart.calculateDuration(), "Duration preserved in cart");

    System.out.println();
  }

  // ========================================================================
  // BOUNDARY CONDITION TESTS
  // ========================================================================

  private static void testBoundaryConditions() {
    printHeader("BOUNDARY CONDITION TESTS");

    // Test 1: Special discount at 4.99 hours (just below boundary)
    List<Purchasable> items1 = new ArrayList<>();
    items1.add(new Lesson("L1", 100.0, 4.99)); // 499
    Cart cart1 = new Cart(items1);
    Purchasable special1 = new SpecialDiscount(cart1); // No discount: 499
    assertEquals(499.0, special1.calculatePrice(), "4.99 hours - no special discount");

    // Test 2: Special discount at 5.0 hours (exactly at boundary)
    List<Purchasable> items2 = new ArrayList<>();
    items2.add(new Lesson("L2", 100.0, 5.0)); // 500
    Cart cart2 = new Cart(items2);
    Purchasable special2 = new SpecialDiscount(cart2); // 500 - 12 = 488
    assertEquals(488.0, special2.calculatePrice(), "5.0 hours - special discount applies");

    // Test 3: Special discount at 5.01 hours (just above boundary)
    List<Purchasable> items3 = new ArrayList<>();
    items3.add(new Lesson("L3", 100.0, 5.01)); // 501
    Cart cart3 = new Cart(items3);
    Purchasable special3 = new SpecialDiscount(cart3); // 501 - 12 = 489
    assertEquals(489.0, special3.calculatePrice(), "5.01 hours - special discount applies");

    // Test 4: Multi-module at 1 module (below boundary)
    List<Purchasable> items4 = new ArrayList<>();
    Module m1 = new Module("M1");
    m1.addCourse(new Course("C"));
    items4.add(m1);
    Cart cart4 = new Cart(items4);
    Purchasable multi1 = new MultiModuleDiscount(cart4);
    assertEquals(0.0, multi1.calculatePrice(), "1 module - no multi discount");

    // Test 5: Multi-module at exactly 2 modules (at boundary)
    List<Purchasable> items5 = new ArrayList<>();
    Module m2 = new Module("M2");
    Course c1 = new Course("C1");
    c1.addLesson(new Lesson("L", 25.0, 1.0));
    m2.addCourse(c1);
    Module m3 = new Module("M3");
    Course c2 = new Course("C2");
    c2.addLesson(new Lesson("L", 25.0, 1.0));
    m3.addCourse(c2);
    items5.add(m2);
    items5.add(m3);
    Cart cart5 = new Cart(items5);
    Purchasable multi2 = new MultiModuleDiscount(cart5);
    assertEquals(35.0, multi2.calculatePrice(), "2 modules - multi discount applies");

    // Test 6: Multi-module at 3 modules (above boundary)
    List<Purchasable> items6 = new ArrayList<>();
    Module m4 = new Module("M4");
    Course c3 = new Course("C3");
    c3.addLesson(new Lesson("L3", 5.0, 1.0)); // 5
    m4.addCourse(c3);
    items6.add(m1); // 0 (empty)
    items6.add(m2); // 25
    items6.add(m4); // 5
    Cart cart6 = new Cart(items6); // Total: 30
    Purchasable multi3 = new MultiModuleDiscount(cart6); // 30 - 15 = 15
    assertEquals(15.0, multi3.calculatePrice(), "3 modules - multi discount applies");

    // Test 7: Price exactly at $0 boundary
    List<Purchasable> zeroItems = new ArrayList<>();
    zeroItems.add(new Lesson("Free", 0.0, 10.0));
    Cart zeroCart = new Cart(zeroItems);
    Customer student = new Customer("S", true, true);
    Purchasable allDiscounts = new MultiModuleDiscount(
        new DevelopingCountryStudentDiscount(
            new SpecialDiscount(zeroCart), student));
    assertEquals(0.0, allDiscounts.calculatePrice(), "Zero price with all discounts");

    // Test 8: Very large price values
    List<Purchasable> largeItems = new ArrayList<>();
    largeItems.add(new Lesson("Expensive", 10000.0, 10.0));
    Cart largeCart = new Cart(largeItems);
    assertEquals(100000.0, largeCart.calculatePrice(), "Very large price");
    Purchasable largeDiscount = new SpecialDiscount(largeCart);
    assertEquals(99988.0, largeDiscount.calculatePrice(), "Discount on large price");

    // Test 9: Duration accumulation across decorators
    List<Purchasable> durationItems = new ArrayList<>();
    durationItems.add(new Lesson("L1", 10.0, 1.0));
    durationItems.add(new Lesson("L2", 10.0, 1.0));
    durationItems.add(new Lesson("L3", 10.0, 1.0));
    durationItems.add(new Lesson("L4", 10.0, 1.0));
    durationItems.add(new Lesson("L5", 10.0, 1.0)); // Total 5.0 hours
    Cart durationCart = new Cart(durationItems);
    assertEquals(5.0, durationCart.calculateDuration(), "Accumulated duration at boundary");
    Purchasable specialAtBoundary = new SpecialDiscount(durationCart);
    assertEquals(38.0, specialAtBoundary.calculatePrice(), "Special discount at accumulated boundary");

    // Test 10: Module count with courses vs lessons
    List<Purchasable> mixedItems = new ArrayList<>();
    mixedItems.add(new Lesson("Standalone", 10.0, 1.0)); // 0 modules
    Course course = new Course("Course");
    course.addLesson(new Lesson("CL", 10.0, 1.0));
    mixedItems.add(course); // 0 modules
    Module module = new Module("Module");
    module.addCourse(new Course("MC"));
    mixedItems.add(module); // 1 module
    Cart mixedCart = new Cart(mixedItems);
    assertEquals(1, mixedCart.getModuleCount(), "Module count ignores lessons and courses");
    Purchasable noMultiDiscount = new MultiModuleDiscount(mixedCart);
    assertEquals(20.0, noMultiDiscount.calculatePrice(), "No multi discount with 1 module in mixed cart");

    System.out.println();
  }

  // ========================================================================
  // STRESS TESTS
  // ========================================================================

  private static void testStressTests() {
    printHeader("STRESS TESTS");

    // Test 1: Large number of lessons in course
    Course megaCourse = new Course("Mega Course");
    for (int i = 0; i < 100; i++) {
      megaCourse.addLesson(new Lesson("Lesson " + i, 1.0, 0.1));
    }
    assertEquals(10.0, megaCourse.calculatePrice(), "Course with 100 lessons price");
    assertEquals(10.0, megaCourse.calculateDuration(), "Course with 100 lessons duration");

    // Test 2: Large number of courses in module
    Module megaModule = new Module("Mega Module");
    for (int i = 0; i < 50; i++) {
      Course c = new Course("Course " + i);
      c.addLesson(new Lesson("L", 2.0, 0.2));
      megaModule.addCourse(c);
    }
    assertEquals(20.0, megaModule.calculatePrice(), "Module with 50 courses");
    assertEquals(10.0, megaModule.calculateDuration(), "Module with 50 courses duration");

    // Test 3: Large cart with many items
    List<Purchasable> manyItems = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      manyItems.add(new Lesson("Item " + i, 1.0, 0.05)); // 1*0.05 = 0.05 each
    }
    Cart megaCart = new Cart(manyItems); // 100 * 0.05 = 5.0
    assertEquals(5.0, megaCart.calculatePrice(), "Cart with 100 items");
    assertEquals(5.0, megaCart.calculateDuration(), "Cart with 100 items duration");

    // Test 4: Large cart with many modules
    List<Purchasable> manyModules = new ArrayList<>();
    for (int i = 0; i < 20; i++) {
      Module m = new Module("Module " + i);
      Course c = new Course("C");
      c.addLesson(new Lesson("L", 5.0, 0.25)); // 1.25 per module
      m.addCourse(c);
      manyModules.add(m);
    }
    Cart moduleCart = new Cart(manyModules); // 20 * 1.25 = 25
    assertEquals(20, moduleCart.getModuleCount(), "Cart with 20 modules count");
    Purchasable multiDiscount = new MultiModuleDiscount(moduleCart); // 25 - 15 = 10
    assertEquals(10.0, multiDiscount.calculatePrice(), "Multi discount on 20 modules");

    // Test 5: Deep decorator nesting
    Purchasable deeplyDecorated = new Lesson("Base", 10.0, 1.0);
    for (int i = 0; i < 10; i++) {
      if (i % 2 == 0) {
        deeplyDecorated = new PracticeQuestionSetDecorator(deeplyDecorated);
      } else {
        deeplyDecorated = new LiveMentorSupportDecorator(deeplyDecorated);
      }
    }
    assertEquals(160.0, deeplyDecorated.calculatePrice(), "10 levels of decorator nesting");
    assertEquals(1.0, deeplyDecorated.calculateDuration(), "Duration preserved through 10 decorators");

    // Test 6: Deep discount nesting
    List<Purchasable> discountItems = new ArrayList<>();
    Module dm1 = new Module("DM1");
    Course dc1 = new Course("DC1");
    dc1.addLesson(new Lesson("DL1", 50.0, 2.0)); // 100
    dm1.addCourse(dc1);
    Module dm2 = new Module("DM2");
    Course dc2 = new Course("DC2");
    dc2.addLesson(new Lesson("DL2", 50.0, 2.0)); // 100
    dm2.addCourse(dc2);
    discountItems.add(dm1);
    discountItems.add(dm2);
    Cart discountCart = new Cart(discountItems); // 200, 4h (no special), 2 modules

    Customer student = new Customer("S", true, true);
    Purchasable nested = new MultiModuleDiscount(
        new DevelopingCountryStudentDiscount(
            new SpecialDiscount(discountCart), student)); // 200 - 10 - 15 = 175
    assertEquals(175.0, nested.calculatePrice(), "Triple nested cart discounts");

    // Test 7: Mixed heavy scenario - large cart with decorators and discounts
    List<Purchasable> mixedHeavy = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Module m = new Module("HM" + i);
      Course c = new Course("HC" + i);
      c.addLesson(new Lesson("HL" + i, 10.0, 0.6));
      m.addCourse(c);

      if (i % 2 == 0) {
        mixedHeavy.add(new PracticeQuestionSetDecorator(m));
      } else {
        mixedHeavy.add(new LiveMentorSupportDecorator(m));
      }
    }
    Cart heavyCart = new Cart(mixedHeavy);
    assertEquals(10, heavyCart.getModuleCount(), "Heavy cart module count");
    assertEquals(6.0, heavyCart.calculateDuration(), "Heavy cart duration");

    Customer heavyStudent = new Customer("HS", true, true);
    Purchasable heavyCheckout = new MultiModuleDiscount(
        new DevelopingCountryStudentDiscount(
            new SpecialDiscount(heavyCart), heavyStudent));

    double expectedHeavy = (10 * 6.0 + 5 * 10.0 + 5 * 20.0) - 12.0 - 10.0 - 15.0;
    assertEquals(expectedHeavy, heavyCheckout.calculatePrice(), "Heavy mixed scenario");

    // Test 8: Cart operations stress test
    List<Purchasable> opsTest = new ArrayList<>();
    Cart opsCart = new Cart(opsTest);

    for (int i = 0; i < 50; i++) {
      Lesson lesson = new Lesson("OL" + i, 2.0, 0.1); // 0.2 each
      assertTrue(opsCart.addItem(lesson), "Add operation " + i);
    }
    assertEquals(10.0, opsCart.calculatePrice(), "After 50 additions"); // 50 * 0.2 = 10

    // Test 9: Decorator and discount combination stress
    List<Purchasable> comboItems = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      Module m = new Module("CM" + i);
      Course c = new Course("CC" + i);
      c.addLesson(new Lesson("CL" + i, 20.0, 1.0));
      m.addCourse(c);

      Purchasable decorated = new LiveMentorSupportDecorator(
          new PracticeQuestionSetDecorator(m));
      comboItems.add(decorated);
    }
    Cart comboCart = new Cart(comboItems);
    assertEquals(5, comboCart.getModuleCount(), "Combo cart module count");

    Customer comboStudent = new Customer("CS", true, true);
    Purchasable comboCheckout = new MultiModuleDiscount(
        new DevelopingCountryStudentDiscount(
            new SpecialDiscount(comboCart), comboStudent));

    double expectedCombo = (5 * 20.0 + 5 * 10.0 + 5 * 20.0) - 12.0 - 10.0 - 15.0;
    assertEquals(expectedCombo, comboCheckout.calculatePrice(), "Combo stress test");

    // Test 10: Boundary stress - many items at exact boundaries
    List<Purchasable> boundaryStress = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Module m = new Module("BSM" + i);
      Course c = new Course("BSC" + i);
      c.addLesson(new Lesson("BSL" + i, 10.0, 0.5)); // 5 each
      m.addCourse(c);
      boundaryStress.add(m);
    }
    Cart boundaryCart = new Cart(boundaryStress); // 10 * 5 = 50
    assertEquals(10, boundaryCart.getModuleCount(), "10 modules exactly");
    assertEquals(5.0, boundaryCart.calculateDuration(), "5 hours exactly");

    Purchasable boundaryDiscounts = new MultiModuleDiscount(
        new SpecialDiscount(boundaryCart)); // 50 - 12 - 15 = 23
    assertEquals(23.0, boundaryDiscounts.calculatePrice(), "Both discounts at boundaries");

    System.out.println();
  }

  // ========================================================================
  // UTILITY METHODS
  // ========================================================================

  private static void printHeader(String title) {
    System.out.println("\n" + "=".repeat(80));
    System.out.println(title);
    System.out.println("=".repeat(80));
  }

  private static void assertEquals(double expected, double actual, String testName) {
    testCount++;
    if (Math.abs(expected - actual) < 0.01) {
      passCount++;
      System.out.println("✓ PASS: " + testName);
    } else {
      failCount++;
      System.out.println("✗ FAIL: " + testName);
      System.out.println("  Expected: " + expected + ", Got: " + actual);
    }
  }

  private static void assertEquals(int expected, int actual, String testName) {
    testCount++;
    if (expected == actual) {
      passCount++;
      System.out.println("✓ PASS: " + testName);
    } else {
      failCount++;
      System.out.println("✗ FAIL: " + testName);
      System.out.println("  Expected: " + expected + ", Got: " + actual);
    }
  }

  private static void assertEquals(String expected, String actual, String testName) {
    testCount++;
    if (expected.equals(actual)) {
      passCount++;
      System.out.println("✓ PASS: " + testName);
    } else {
      failCount++;
      System.out.println("✗ FAIL: " + testName);
      System.out.println("  Expected: " + expected + ", Got: " + actual);
    }
  }

  private static void assertTrue(boolean condition, String testName) {
    testCount++;
    if (condition) {
      passCount++;
      System.out.println("✓ PASS: " + testName);
    } else {
      failCount++;
      System.out.println("✗ FAIL: " + testName);
      System.out.println("  Expected: true, Got: false");
    }
  }

  private static void assertFalse(boolean condition, String testName) {
    testCount++;
    if (!condition) {
      passCount++;
      System.out.println("✓ PASS: " + testName);
    } else {
      failCount++;
      System.out.println("✗ FAIL: " + testName);
      System.out.println("  Expected: false, Got: true");
    }
  }
}
