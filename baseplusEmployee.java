// A class to test the employee hierarchy
public class baseplusEmployee
 {
  public static void main(String[] args) {
    // Create some commission employees
    CommissionEmployee employee1 = new CommissionEmployee("Bob", "Lewis", "333-33-3333", 5000, 0.04);
    CommissionEmployee employee2 = new CommissionEmployee("Sue", "Jones", "222-22-2222", 10000, 0.06);

    // Output the information and earnings of each employee
    System.out.println("Employee 1 information:");
    System.out.println(employee1);
    System.out.println("Employee 1 earnings: " + employee1.earnings());

    System.out.println("Employee 2 information:");
    System.out.println(employee2);
    System.out.println("Employee 2 earnings: " + employee2.earnings());
  }
}
