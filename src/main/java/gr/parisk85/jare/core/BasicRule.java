package gr.parisk85.jare.core;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BasicRule<T> implements Rule<T> {
    private final Predicate<T> when;
    private final Consumer<T> then;
    private final Supplier<Exception> thenThrow;

    BasicRule(RuleBuilder<T> builder) {
        this.when = builder.when;
        this.then = builder.then;
        this.thenThrow = builder.thenThrow;
    }

    @Override
    public void run(T feed) {
        Optional.ofNullable(feed)
                .filter(when)
                .ifPresent(this::acceptOrThrow);
    }

    private void acceptOrThrow(T feed) {
        Optional.ofNullable(then).ifPresentOrElse(it -> it.accept(feed), () -> ThrowingSupplier.get(thenThrow));
    }
}
