public class Date {
    private int day;
    private int month;
    private int year;

    private static final String[] MONTH_NAMES = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    };

    public Date(int month, int day, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Date(String monthName, int day, int year) {
        this.day = day;
        this.month = getMonthIndex(monthName);
        this.year = year;
    }

    public Date(int dayOfYear, int year) {
        this.year = year;
        calculateDateFromDayOfYear(dayOfYear);
    }

    public void outputDate() {
        System.out.println("MM/DD/YYYY: " + String.format("%02d/%02d/%04d", month, day, year));
        System.out.println("Month DD, YYYY: " + MONTH_NAMES[month - 1] + " " + day + ", " + year);
        System.out.println("DDD YYYY: " + getDayOfYear() + " " + year);
    }

    private int getMonthIndex(String monthName) {
        for (int i = 0; i < MONTH_NAMES.length; i++) {
            if (MONTH_NAMES[i].equalsIgnoreCase(monthName)) {
                return i + 1;
            }
        }
        return -1;
    }

    private int getDayOfYear() {
        int dayOfYear = day;
        for (int i = 1; i < month; i++) {
            dayOfYear += getDaysInMonth(i, year);
        }
        return dayOfYear;
    }

    private void calculateDateFromDayOfYear(int dayOfYear) {
        month = 1;
        while (dayOfYear > getDaysInMonth(month, year)) {
            dayOfYear -= getDaysInMonth(month, year);
            month++;
        }
        day = dayOfYear;
    }

    private int getDaysInMonth(int month, int year) {
        if (month == 2) {
            return isLeapYear(year) ? 29 : 28;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        } else {
            return 31;
        }
    }

    private boolean isLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

}