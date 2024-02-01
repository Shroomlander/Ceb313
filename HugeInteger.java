import java.util.Arrays;

public class HugeInteger {
    private int[] digits;

    public HugeInteger() {
        digits = new int[40];
    }

    public HugeInteger(String number) {
        this();
        parse(number);
    }

    public void parse(String number) {
        int length = number.length();
        int startIndex = 40 - length;

        for (int i = 0; i < length; i++) {
            digits[startIndex + i] = Character.getNumericValue(number.charAt(i));
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean leadingZero = true;

        for (int digit : digits) {
            if (digit != 0) {
                leadingZero = false;
            }

            if (!leadingZero) {
                sb.append(digit);
            }
        }

        return sb.toString();
    }

    public HugeInteger add(HugeInteger other) {
        HugeInteger result = new HugeInteger();
        int carry = 0;

        for (int i = 39; i >= 0; i--) {
            int sum = digits[i] + other.digits[i] + carry;
            result.digits[i] = sum % 10;
            carry = sum / 10;
        }

        return result;
    }

    public HugeInteger subtract(HugeInteger other) {
        HugeInteger result = new HugeInteger();
        int borrow = 0;

        for (int i = 39; i >= 0; i--) {
            int diff = digits[i] - other.digits[i] - borrow;

            if (diff < 0) {
                diff += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }

            result.digits[i] = diff;
        }

        return result;
    }

    public boolean isEqualTo(HugeInteger other) {
        return Arrays.equals(digits, other.digits);
    }

    public boolean isNotEqualTo(HugeInteger other) {
        return !isEqualTo(other);
    }

    public boolean isGreaterThan(HugeInteger other) {
        for (int i = 0; i < 40; i++) {
            if (digits[i] > other.digits[i]) {
                return true;
            } else if (digits[i] < other.digits[i]) {
                return false;
            }
        }

        return false;
    }

    public boolean isLessThan(HugeInteger other) {
        return !isEqualTo(other) && !isGreaterThan(other);
    }

    public boolean isGreaterThanOrEqualTo(HugeInteger other) {
        return isEqualTo(other) || isGreaterThan(other);
    }

    public boolean isLessThanOrEqualTo(HugeInteger other) {
        return isEqualTo(other) || isLessThan(other);
    }

    public boolean isZero() {
        for (int digit : digits) {
            if (digit != 0) {
                return false;
            }
        }

        return true;
    }

    public HugeInteger multiply(HugeInteger other) {
        // TODO: Implement multiplication
        return null;
    }

    public HugeInteger divide(HugeInteger other) {
        // TODO: Implement division
        return null;
    }

    public HugeInteger remainder(HugeInteger other) {
        // TODO: Implement remainder
        return null;
    }