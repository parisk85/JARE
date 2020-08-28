package gr.parisk85.jare.core;

import gr.parisk85.jare.core.visitor.FinalizeVisitor;
import gr.parisk85.jare.core.visitor.RuleFinalizer;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public final class BasicRule<T> implements Rule<T> {
    private final Predicate<T> when;
    private final List<RuleFinalizer<T>> finalizers;

    BasicRule(final RuleBuilder<T> builder) {
        this.when = builder.when;
        this.finalizers = builder.finalizers;
    }

    @Override
    public void run(final T feed) {
        Optional.ofNullable(feed)
                .filter(this::verifyWhen)
                .ifPresent(this::accept);
    }

    private boolean verifyWhen(final T feed) {
        return Optional.ofNullable(when)
                .orElse(f -> true)
                .test(feed);
    }

    private void accept(final T feed) {
        finalizers.stream()
                .filter(Objects::nonNull)
                .findFirst()
                .ifPresent(f -> f.accept(FinalizeVisitor.feed(feed)));
    }
}
