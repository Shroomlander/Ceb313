public class time2test {
    public static void main(String[] args) {
        time2 time = new time2(11, 59, 59);
        System.out.println("Initial time: " + time.toString());

        time.tick();
        System.out.println("After ticking: " + time.toString());

        time.incrementMinute();
        System.out.println("After incrementing minute: " + time.toString());

        time.incrementHour();
        System.out.println("After incrementing hour: " + time.toString());

        // Testing incrementing into the next day
        time.setTime(23, 59, 59);
        System.out.println("Initial time: " + time.toString());

        time.tick();
        System.out.println("After ticking: " + time.toString());
    }
}
