public class IntegerSetTest {
    
    public static void main(String[] args) {
        IntegerSet set1 = new IntegerSet();
        set1.insertElement(2);
        set1.insertElement(4);
        set1.insertElement(6);

        IntegerSet set2 = new IntegerSet();
        set2.insertElement(4);
        set2.insertElement(6);
        set2.insertElement(8);

        System.out.println("Set 1: " + set1);
        System.out.println("Set 2: " + set2);

        set1.union(set2);
        System.out.println("Union of Set 1 and Set 2: " + set1);

        set1 = new IntegerSet();
        set2 = new IntegerSet();

        set1.insertElement(1);
        set1.insertElement(2);
        set1.insertElement(3);

        set2.insertElement(2);
        set2.insertElement(3);
        set2.insertElement(4);

        set1.intersection(set2);
        System.out.println("Intersection of Set 1 and Set 2: " + set1);
    }
}

