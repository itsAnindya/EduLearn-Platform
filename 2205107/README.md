# EduLearn Platform – 2205107

## Composite Design Pattern

### What is the Composite Pattern?

The **Composite pattern** is a structural design pattern that lets you compose objects into tree structures to represent part-whole hierarchies. The key idea is that clients treat individual objects (leaves) and groups of objects (composites) through the **same interface**, without needing to know which kind they are dealing with.

---

### Where is it applied?

The Composite pattern is implemented across five files inside `2205107/src/`:

| File | Role in the Pattern |
|------|---------------------|
| `Purchasable.java` | **Component interface** – the common contract |
| `Lesson.java` | **Leaf** – atomic content unit, no children |
| `Course.java` | **Composite (level 1)** – aggregates `Lesson` objects |
| `Module.java` | **Composite (level 2)** – aggregates `Course` objects |
| `Cart.java` | **Composite (root)** – aggregates any `Purchasable` item |

Together they form a content tree:

```
Cart  (Composite – root)
 └─ Module  (Composite – level 2)
     └─ Course  (Composite – level 1)
         └─ Lesson  (Leaf)
```

---

### How each role is implemented

#### 1. `Purchasable` – Component Interface

```java
public interface Purchasable {
    double calculatePrice();
    double calculateDuration();
    void   printDetails(String indent);
    int    getModuleCount();
}
```

Every node in the tree – whether leaf or composite – implements this interface. This is what allows client code to interact with the whole hierarchy through a single, uniform type.

---

#### 2. `Lesson` – Leaf

```java
public class Lesson implements Purchasable {
    private double pricePerHour;
    private double duration;

    @Override public double calculatePrice()    { return pricePerHour * duration; }
    @Override public double calculateDuration() { return duration; }
    @Override public int    getModuleCount()    { return 0; }
}
```

`Lesson` is the **smallest indivisible unit**. It owns its data directly and returns it from every method — there are no children to delegate to.

---

#### 3. `Course` – Composite (level 1)

```java
public class Course implements Purchasable {
    private final ArrayList<Lesson> lessons;

    @Override
    public double calculatePrice() {
        double total = 0;
        for (Lesson lesson : lessons) total += lesson.calculatePrice();
        return total;
    }
}
```

`Course` aggregates `Lesson` objects and **delegates** `calculatePrice()` and `calculateDuration()` to each child. The caller cannot tell whether it has a `Lesson` or a `Course` — both answer through the same `Purchasable` interface.

---

#### 4. `Module` – Composite (level 2)

```java
public class Module implements Purchasable {
    private final ArrayList<Course> courses;

    @Override
    public double calculatePrice() {
        double total = 0;
        for (Course course : courses) total += course.calculatePrice();
        return total;
    }
}
```

`Module` aggregates `Course` composites. When `calculatePrice()` is called on a `Module`, the call **propagates recursively** through every `Course` and every `Lesson` inside it. The caller writes no looping or tree-traversal logic; the pattern handles it transparently.

---

#### 5. `Cart` – Composite (shopping-cart root)

```java
public class Cart implements Purchasable {
    private List<Purchasable> items;   // accepts ANY Purchasable

    @Override
    public double calculatePrice() {
        double total = 0;
        for (Purchasable item : items) total += item.calculatePrice();
        return total;
    }

    @Override
    public int getModuleCount() {
        int count = 0;
        for (Purchasable item : items) count += item.getModuleCount();
        return count;
    }
}
```

`Cart` is the most flexible composite: its child list is typed as `List<Purchasable>`, so it can hold a bare `Lesson`, a `Course`, a `Module`, or a decorator-wrapped variant of any of these. The checkout logic in `App.java` only ever calls `calculatePrice()` on the cart — it never needs to know what is inside.

---

### Where the pattern pays off – client code in `App.java`

The checkout flow in `App.java` illustrates the core benefit:

```java
// Works whether the cart contains a Lesson, a Course, or a full Module
Cart finalCart = cartService.toCart();
double subtotal  = finalCart.calculatePrice();    // recursive sum
double duration  = finalCart.calculateDuration(); // recursive sum
int    modules   = finalCart.getModuleCount();    // recursive count
```

The same three lines work for any combination of items. There is no `instanceof` check, no special case for each level — the tree handles recursion internally.

Likewise, `printDetails()` is called uniformly at every level:

```java
module.printDetails("> ");   // prints module, all its courses, all their lessons
course.printDetails("> ");   // prints course and all its lessons
lesson.printDetails("> ");   // prints just the lesson
```

---

### Uniform treatment — the defining feature

```
Client code calls:  item.calculatePrice()
                         ↓
                    Purchasable
                    /    |    \
                Lesson Course  Module
                (leaf) (comp) (comp)
                         ↓       ↓
                       Lesson  Course → Lesson
```

The client never changes; only the runtime type of `item` determines how deep the recursion goes. This is exactly the promise of the Composite pattern.

---

### Summary

| Pattern concept | Implementation in 2205107 |
|-----------------|---------------------------|
| Component interface | `Purchasable` |
| Leaf | `Lesson` |
| Composite | `Course`, `Module`, `Cart` |
| Operations defined on component | `calculatePrice()`, `calculateDuration()`, `printDetails()`, `getModuleCount()` |
| Recursive delegation | Each composite iterates its child list and accumulates child results |
| Uniform client code | `App.java` checkout uses `Purchasable` everywhere, never inspects the concrete type |
