public class Customer {
  private String name;
  private boolean isFromDevelopingCountry;
  private boolean isStudent;

  // Constructors
  public Customer(String name, boolean isFromDevelopingCountry, boolean isStudent) {
    this.name = name;
    this.isFromDevelopingCountry = isFromDevelopingCountry;
    this.isStudent = isStudent;
  }

  // Getters and Setters
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isFromDevelopingCountry() {
    return isFromDevelopingCountry;
  }

  public void setFromDevelopingCountry(boolean fromDevelopingCountry) {
    isFromDevelopingCountry = fromDevelopingCountry;
  }

  public boolean isStudent() {
    return isStudent;
  }

  public void setStudentStatus(boolean studentStatus) {
    isStudent = studentStatus;
  }
}
