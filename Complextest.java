public class Complextest {
    public static void main(String[] args) {
        Complex complex1 = new Complex(2.5, 3.7);
        Complex complex2 = new Complex(1.8, 2.1);

        System.out.print("Complex1: ");
        complex1.print();

        System.out.print("Complex2: ");
        complex2.print();

        complex1.add(complex2);
        System.out.print("After addition: ");
        complex1.print();

        complex1.subtract(complex2);
        System.out.print("After subtraction: ");
        complex1.print();
    }
}

