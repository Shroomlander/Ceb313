public class IntegerSet {
    private boolean[] set;

    public IntegerSet() {
        set = new boolean[101];
    }

    public void insertElement(int element) {
        if (isValidElement(element)) {
            set[element] = true;
        } else {
            System.out.println("Invalid element! Please enter an integer between 0 and 100.");
        }
    }

    public void deleteElement(int element) {
        if (isValidElement(element)) {
            set[element] = false;
        } else {
            System.out.println("Invalid element! Please enter an integer between 0 and 100.");
        }
    }

    public boolean getElement(int element) {
        if (isValidElement(element)) {
            return set[element];
        } else {
            System.out.println("Invalid element! Please enter an integer between 0 and 100.");
            return false;
        }
    }

    public void union(IntegerSet set2) {
        for (int i = 0; i < set.length; i++) {
            set[i] = set[i] || set2.getElement(i);
        }
    }

    public void intersection(IntegerSet set2) {
        for (int i = 0; i < set.length; i++) {
            set[i] = set[i] && set2.getElement(i);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        for (int i = 0; i < set.length; i++) {
            if (set[i]) {
                sb.append(i).append(" ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    private boolean isValidElement(int element) {
        return element >= 0 && element <= 100;
    }

}