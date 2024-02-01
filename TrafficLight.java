public enum TrafficLight {
    RED(30),
    GREEN(45),
    YELLOW(5);

    private int duration;

    private TrafficLight(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }
}