public class Complex {
    private double realPart;
    private double imaginaryPart;

    public Complex() {
        this(0.0, 0.0); // Default values
    }

    public Complex(double realPart, double imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    public void add(Complex number) {
        realPart += number.realPart;
        imaginaryPart += number.imaginaryPart;
    }

    public void subtract(Complex number) {
        realPart -= number.realPart;
        imaginaryPart -= number.imaginaryPart;
    }

    public void print() {
        System.out.println("(" + realPart + ", " + imaginaryPart + ")");
    }
}
