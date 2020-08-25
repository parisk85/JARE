package gr.parisk85.jare.core;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BasicRule<T> implements Rule<T> {
    private final Class<T> given;
    private final Predicate<T> when;
    private final Consumer<T> then;
    private final Supplier<? extends Exception> thenThrow;

    BasicRule(RuleBuilder<T> builder) {
        this.given = builder.given;
        this.when = builder.when;
        this.then = builder.then;
        this.thenThrow = builder.thenThrow;
    }

    @Override
    public void run(T feed) {
        if (when.test(feed)) {
            Optional.ofNullable(then).ifPresent(t -> t.accept(feed));
            Optional.ofNullable(thenThrow).ifPresent(t -> {
                try {
                    throw t.get();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
