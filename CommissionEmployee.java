// A class to represent a commission employee, which is a subclass of employee
public class CommissionEmployee extends Employee {
  private double grossSales; // the gross sales of the commission employee
  private double commissionRate; // the commission rate of the commission employee

  // A constructor to initialize the commission employee
  public CommissionEmployee(String firstName, String lastName, 
    String socialSecurityNumber, double grossSales, double commissionRate) {
    super(firstName, lastName, socialSecurityNumber); // invoke the superclass constructor
    this.grossSales = grossSales;
    this.commissionRate = commissionRate;
  }

  // A getter method for the gross sales
  public double getGrossSales() {
    return grossSales;
  }

  // A getter method for the commission rate
  public double getCommissionRate() {
    return commissionRate;
  }

  // A method to calculate the earnings of the commission employee
  public double earnings() {
    return getCommissionRate() * getGrossSales();
  }

  // A method to return a string representation of the commission employee
  public String toString() {
    return String.format("%s %s%n%s: %.2f%n%s: %.2f", 
      "commission employee", super.toString(), // invoke the superclass toString method
      "gross sales", getGrossSales(), 
      "commission rate", getCommissionRate());
  }
}
