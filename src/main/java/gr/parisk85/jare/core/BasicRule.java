package gr.parisk85.jare.core;

import gr.parisk85.jare.core.visitor.FinalizeRuleFinalizerVisitor;
import gr.parisk85.jare.core.visitor.RuleFinalizer;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class BasicRule<T> implements Rule<T> {
    private final Predicate<T> when;
    private final List<RuleFinalizer<T>> finalizers;

    BasicRule(RuleBuilder<T> builder) {
        this.when = builder.when;
        this.finalizers = builder.finalizers;
    }

    @Override
    public void run(T feed) {
        Optional.ofNullable(feed)
                .filter(when)
                .ifPresent(this::acceptOrThrow);
    }

    private void acceptOrThrow(T feed) {
        finalizers.stream()
                .filter(Objects::nonNull)
                .findFirst()
                .ifPresent(f -> f.accept(FinalizeRuleFinalizerVisitor.feed(feed)));
    }
}
