
// A class to represent a general employee
class Employee {
  private String firstName; // the first name of the employee
  private String lastName; // the last name of the employee
  private String socialSecurityNumber; // the social security number of the employee

  // A constructor to initialize the employee
  public Employee(String firstName, String lastName, String socialSecurityNumber) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.socialSecurityNumber = socialSecurityNumber;
  }

  // A getter method for the first name
  public String getFirstName() {
    return firstName;
  }

  // A getter method for the last name
  public String getLastName() {
    return lastName;
  }

  // A getter method for the social security number
  public String getSocialSecurityNumber() {
    return socialSecurityNumber;
  }

  // A method to return a string representation of the employee
  public String toString() {
    return String.format("%s: %s %s%n%s: %s", 
      "employee", getFirstName(), getLastName(), 
      "social security number", getSocialSecurityNumber());
  }
}