package gr.parisk85.jare.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public final class JAREngine<T> {
    private final Class<?> type;
    private final List<T> feed;
    private final List<Rule<T>> rules;

    private JAREngine() {
        feed = new ArrayList<>();
        rules = new ArrayList<>();
        type = feed.getClass().getGenericSuperclass().getClass();
    }

    private JAREngine(final T input) {
        this();
        feed.add(input);
    }

    private JAREngine(final List<T> input) {
        this();
        feed.addAll(input);
    }

    @SafeVarargs
    private JAREngine(final T... input) {
        this(Arrays.asList(input));
    }

    public static <T> JAREngine<T> feed(final T input) {
        return new JAREngine<>(input);
    }

    public static <T> JAREngine<T> feed(final List<T> input) {
        return new JAREngine<>(input);
    }

    @SafeVarargs
    public static <T> JAREngine<T> feed(final T... input) {
        return new JAREngine<>(input);
    }

    public JAREngine<T> addRule(final Function<RuleBuilder<T>, Rule<T>> function) {
        rules.add(function.apply(new RuleBuilder<>(type)));
        return this;
    }

    public void fire() {
        rules.forEach(r -> feed.forEach(r::run));
    }
}
