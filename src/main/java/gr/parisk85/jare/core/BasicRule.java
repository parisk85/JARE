package gr.parisk85.jare.core;

import gr.parisk85.jare.core.visitor.FinalizeVisitor;
import gr.parisk85.jare.core.visitor.RuleFinalizer;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public final class BasicRule<T> implements Rule<T> {
    private final Class<T> given;
    private final Predicate<T> when;
    private final List<RuleFinalizer<T>> finalizers;

    BasicRule(final RuleBuilder<T> builder) {
        this.given = builder.given;
        this.when = builder.when;
        this.finalizers = builder.finalizers;
    }

    public static <T> RuleBuilder<T> create() {
        return new RuleBuilder<>();
    }

    @Override
    public Class<T> type() {
        return given;
    }

    @Override
    public void run(final T feed) {
        Optional.ofNullable(feed)
                .filter(when)
                .ifPresent(this::acceptOrThrow);
    }

    private void acceptOrThrow(final T feed) {
        finalizers.stream()
                .filter(Objects::nonNull)
                .findFirst()
                .ifPresent(f -> f.accept(FinalizeVisitor.feed(feed)));
    }
}
