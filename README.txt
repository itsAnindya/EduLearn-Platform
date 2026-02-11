QUICK REFERENCE GUIDE - RUNNING THE EXAMPLES
==============================================

Each folder contains a complete, study-friendly Java example project.
All examples compile and run successfully.

QUICK START:
============

# Go to any pattern folder and run:
cd [Pattern_Folder]
javac App.java
java App

---

1. COMPOSITE PATTERN (Lesson → Course → Module Hierarchy)
==========================================================

Location: Composite/App.java
Purpose:  Tree structure with uniform interface

Key Classes:
  - CourseComponent (interface)
  - Lesson (leaf)
  - Course (composite)
  - Module (composite)

What you'll see:
  ✓ Hierarchical display of courses and lessons
  ✓ Unified price/duration calculation at any level
  ✓ Transparent component visibility

Run:
  cd Composite
  javac App.java
  java App

Output: Displays the module tree with prices and durations at each level

---

2. DECORATOR PATTERN (Optional Add-ons)
=======================================

Location: Decorator/App.java
Purpose:  Dynamically add features (Practice Set, Mentor Support)

Key Classes:
  - Purchasable (interface)
  - BaseCourse (component)
  - CourseDecorator (abstract decorator)
  - PracticeQuestionSetDecorator (+$10)
  - LiveMentorSupportDecorator (+$20)
  - PremiumSupportDecorator (+$15)

What you'll see:
  ✓ Base course pricing
  ✓ Course with individual add-ons
  ✓ Course with multiple stacked add-ons
  ✓ Different combinations of features

Run:
  cd Decorator
  javac App.java
  java App

Output: Shows pricing for various decorator combinations

---

3. ADAPTER PATTERN (Multiple Discount Systems)
================================================

Location: Adapter/App.java
Purpose:  Integrate incompatible discount systems with unified interface

Key Classes:
  - DiscountCalculator (target interface)
  - LegacyFlatDiscountSystem (old API)
  - ThirdPartyDiscountService (third-party API)
  - LegacyFlatDiscountAdapter
  - ThirdPartyDiscountAdapter
  - DevelopingCountryDiscountAdapter
  - CheckoutSystem (uses adapters)

What you'll see:
  ✓ Different discount sources working together
  ✓ Multiple discounts combined in checkout
  ✓ Flexible discount composition

Run:
  cd Adapter
  javac App.java
  java App

Output: Shows checkout with various discount combinations

---

4. BRIDGE PATTERN (Flexible Pricing Strategies)
================================================

Location: Bridge/App.java
Purpose:  Separate course types from pricing algorithms

Key Classes:
  - CourseOffering (abstraction)
  - SingleCourse, CourseBundleOffering, AnnualSubscription
  - PricingStrategy (implementor interface)
  - StandardPricing, DurationBasedPricing, BulkDiscountPricing, PremiumDynamicPricing

What you'll see:
  ✓ Same courses with different pricing strategies
  ✓ Independent evolution of courses and pricing
  ✓ Easy to add new combinations

Run:
  cd Bridge
  javac App.java
  java App

Output: Shows how each pricing strategy affects different course types

---

FILE LOCATIONS:
===============

Composite/
├── App.java               ← Main file (has all classes)
├── App.class
├── CourseComponent.class
├── Lesson.class
├── Course.class
└── Module.class

Decorator/
├── App.java               ← Main file (has all classes)
├── App.class
├── Purchasable.class
├── BaseCourse.class
├── CourseDecorator.class
├── PracticeQuestionSetDecorator.class
├── LiveMentorSupportDecorator.class
└── PremiumSupportDecorator.class

Adapter/
├── App.java               ← Main file (has all classes)
├── App.class
├── DiscountCalculator.class
├── LegacyFlatDiscountSystem.class
├── ThirdPartyDiscountService.class
├── LegacyFlatDiscountAdapter.class
├── ThirdPartyDiscountAdapter.class
├── DevelopingCountryDiscountAdapter.class
└── CheckoutSystem.class

Bridge/
├── App.java               ← Main file (has all classes)
├── App.class
├── CourseOffering.class
├── SingleCourse.class
├── CourseBundleOffering.class
├── AnnualSubscription.class
├── PricingStrategy.class
├── StandardPricing.class
├── DurationBasedPricing.class
├── BulkDiscountPricing.class
└── PremiumDynamicPricing.class

---

LEARNING ROADMAP:
=================

Suggested order to study these patterns:

1. Start with COMPOSITE
   → Understand tree structures and recursive algorithms
   → See how unified interface works across levels
   → Foundation for the EduLearn assignment

2. Then study DECORATOR
   → See how to extend functionality without subclassing
   → Understand wrapper pattern
   → Important for optional add-ons in assignment

3. Next, explore ADAPTER
   → Learn interface conversion
   → See how to make incompatible things work together
   → Critical for discount system in assignment

4. Finally, examine BRIDGE (optional)
   → Understand dimension independence
   → Learn how to decouple abstraction from implementation
   → Advanced pattern for future scalability

---

COMMON COMMANDS:
================

# Compile all patterns:
for dir in Composite Decorator Adapter Bridge; do
  cd $dir
  javac App.java
  cd ..
done

# Run all patterns:
for dir in Composite Decorator Adapter Bridge; do
  echo "=== $dir Pattern ==="
  cd $dir
  java App
  echo ""
  cd ..
done

# Clean all .class files:
for dir in Composite Decorator Adapter Bridge; do
  cd $dir
  rm *.class
  cd ..
done

---

TIPS FOR LEARNING:
==================

1. Each App.java file has:
   ✓ Detailed header comments explaining the pattern
   ✓ Comments on each class explaining its role
   ✓ Clear method implementations
   ✓ Well-structured main() method with examples

2. Study the main() method first:
   ✓ Shows how to use the pattern
   ✓ Demonstrates the benefits
   ✓ Clear output format

3. Then study the classes in order:
   ✓ Interface/abstraction first
   ✓ Then concrete implementations
   ✓ Finally how they work together

4. Modify the examples:
   ✓ Add new subclasses
   ✓ Test different combinations
   ✓ See how the pattern handles changes

5. Compare patterns:
   ✓ How would Decorator implement composite?
   ✓ How would Adapter implement decorator?
   ✓ What are the tradeoffs?

---

DOCUMENTATION FILES:
====================

1. PATTERNS_SUMMARY.txt
   - Overview of each pattern
   - Use cases in EduLearn
   - How to run each example
   - Key concepts for study

2. ASSIGNMENT_ANALYSIS.txt
   - Maps assignment requirements to patterns
   - Explains which pattern solves which requirement
   - Scoring guidance
   - Recommended architecture

3. This file (README-like):
   - Quick reference for running examples
   - File locations
   - Learning roadmap
   - Common commands and tips

---

TROUBLESHOOTING:
================

If compilation fails:
  ✓ Make sure you're in the correct folder
  ✓ Use: javac App.java (not javac *.java)
  ✓ Check that filenames match class names

If execution fails:
  ✓ Ensure App.java is in current folder
  ✓ Use: java App (without .java extension)
  ✓ Check that App is public class in App.java

If you see encoding errors:
  ✓ The files have been fixed to use ASCII characters
  ✓ Try: javac -encoding UTF-8 App.java

---

NEXT STEPS:
===========

1. Compile and run all four examples
2. Study the code and understand each pattern
3. Modify examples to see how patterns adapt
4. Think about how to combine these for the EduLearn assignment
5. Design your own implementation using these patterns as guides

Good luck with your CSE 214 assignment!

