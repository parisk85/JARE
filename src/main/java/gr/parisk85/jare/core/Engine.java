package gr.parisk85.jare.core;

import java.util.Arrays;

public class Engine {

    public static <T> void run(T feed, Rule<T>... rules) {
        Arrays.stream(rules).forEach(r -> r.run(feed));
    }
}
