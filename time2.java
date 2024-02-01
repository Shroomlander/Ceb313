public class time2 {
    private int hour;
    private int minute;
    private int second;

    public time2(int hour, int minute, int second) {
        setTime(hour, minute, second);
    }

    public void setTime(int hour, int minute, int second) {
        if (hour >= 0 && hour < 24 && minute >= 0 && minute < 60 && second >= 0 && second < 60) {
            this.hour = hour;
            this.minute = minute;
            this.second = second;
        } else {
            throw new IllegalArgumentException("Invalid time value");
        }
    }

    public void tick() {
        if (second < 59) {
            second++;
        } else {
            second = 0;
            incrementMinute();
        }
    }

    public void incrementMinute() {
        if (minute < 59) {
            minute++;
        } else {
            minute = 0;
            incrementHour();
        }
    }

    public void incrementHour() {
        if (hour < 23) {
            hour++;
        } else {
            hour = 0;
        }
    }

    public String toString() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
}

