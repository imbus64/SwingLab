package swing_demo;
import java.util.*;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>();

        list.add(new AbstractMap.SimpleEntry<>("a", 1));
        list.add(new AbstractMap.SimpleEntry<>("b", 2));

        list.get(0).setValue(3);

        System.out.println(new App().getGreeting());
    }
}
