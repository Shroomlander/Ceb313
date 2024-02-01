public class DateTest {
    
    public static void main(String[] args) {
        Date date1 = new Date(6, 14, 1992);
        date1.outputDate();

        Date date2 = new Date("June", 14, 1992);
        date2.outputDate();

  Date date3 = new Date(166, 1992);
    date3.outputDate();
    }
}

