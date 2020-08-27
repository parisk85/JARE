package gr.parisk85.jare.core;

import gr.parisk85.jare.core.visitor.RuleFinalizer;
import gr.parisk85.jare.core.visitor.ThenRuleFinalizer;
import gr.parisk85.jare.core.visitor.ThenThrowRuleFinalizer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public final class RuleBuilder<T> {
    Class<T> given;
    Predicate<T> when;
    final List<RuleFinalizer<T>> finalizers = new ArrayList<>();

    @SuppressWarnings({"rawtypes", "unchecked"})
    RuleBuilder(final Class given) {
        this.given = given;
    }

    private RuleBuilder(final Predicate<T> predicate) {
        this.when = predicate;
    }

    public static <T> RuleBuilder<T> given(final Class<T> given) {
        return new RuleBuilder<>(given);
    }

    public RuleBuilder<T> when(final Predicate<T> when) {
        return new RuleBuilder<>(when);
    }

    public Rule<T> then(final Consumer<T> then) {
        this.finalizers.add(ThenRuleFinalizer.of(then));
        return new BasicRule<>(this);
    }

    public Rule<T> thenThrow(final Supplier<Exception> thenThrow) {
        this.finalizers.add(ThenThrowRuleFinalizer.of(thenThrow));
        return new BasicRule<>(this);
    }
}
