public class SavingaccountTest {
    
    public static void main(String[] args) {
        Savingaccount saver1 = new Savingaccount(2000.00);
        Savingaccount saver2 = new Savingaccount(3000.00);

        Savingaccount.modifyInterestRate(0.04);
        System.out.println("Monthly balances for Saver 1 (4% interest rate):");
        for (int i = 1; i <= 12; i++) {
            saver1.calculateMonthlyInterest();
            System.out.printf("Month %d: $%.2f\n", i, saver1.getSavingsBalance());
        }
        System.out.println();

        System.out.println("Monthly balances for Saver 2 (4% interest rate):");
        for (int i = 1; i <= 12; i++) {
            saver2.calculateMonthlyInterest();
            System.out.printf("Month %d: $%.2f\n", i, saver2.getSavingsBalance());
        }
        System.out.println();

        Savingaccount.modifyInterestRate(0.05);
        saver1.calculateMonthlyInterest();
        saver2.calculateMonthlyInterest();
        System.out.println("Monthly balances after setting interest rate to 5%:");
        System.out.println("Saver 1: $" + saver1.getSavingsBalance());
        System.out.println("Saver 2: $" + saver2.getSavingsBalance());
    }
}

