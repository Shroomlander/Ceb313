public class TrafficLightTest {
    
    public static void main(String[] args) {
        for (TrafficLight light : TrafficLight.values()) {
            System.out.println(light + " - Duration: " + light.getDuration() + " seconds");
        }
    }
}
