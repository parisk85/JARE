package gr.parisk85.jare.core;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class RuleBuilder<T> {
    final Class<T> given;
    Predicate<T> when;
    Consumer<T> then;
    Supplier<Exception> thenThrow;

    private RuleBuilder(final Class<T> given) {
        this.given = given;
    }

    public static <T> RuleBuilder<T> given(final Class<T> given) {
        return new RuleBuilder<>(given);
    }

    public RuleBuilder<T> when(final Predicate<T> when) {
        this.when = when;
        return this;
    }

    public Rule<T> then(final Consumer<T> then) {
        this.then = then;
        return new BasicRule<>(this);
    }

    public Rule<T> thenThrow(final Supplier<Exception> thenThrow) {
        this.thenThrow = thenThrow;
        return new BasicRule<>(this);
    }
}
