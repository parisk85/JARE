package gr.parisk85.jare.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * An engine to fire the provided list of {@link Rule} for the given feed elements.
 *
 * @author parisk85
 */
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

    /**
     * Adds the rule created to {@link JAREngine#rules} list.
     *
     * @param function to provide lambda for generating a {@link Rule} from a {@link RuleBuilder}.
     *
     * @return an instance of the engine to chain more rule additions.
     */
    public JAREngine<T> addRule(final Function<RuleBuilder<T>, Rule<T>> function) {
        rules.add(function.apply(new RuleBuilder<>(type)));
        return this;
    }

    /**
     * Fires the engine to evaluate the rules {@link JAREngine#rules} for the given elements of {@link JAREngine#feed}.
     */
    public void fire() {
        rules.forEach(r -> feed.forEach(r::run));
    }

}
