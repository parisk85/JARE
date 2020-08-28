package gr.parisk85.jare.core;

import gr.parisk85.jare.core.visitor.FinalizeVisitor;
import gr.parisk85.jare.core.visitor.RuleFinalizer;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * The basic rule implementation.
 *
 * Can be evaluated by itself or used with a {@link JAREngine}.
 *
 * @author parisk85
 */
public final class BasicRule<T> implements Rule<T> {

    private final Predicate<T> when;
    private final List<RuleFinalizer<T>> finalizers;

    BasicRule(final RuleBuilder<T> builder) {
        this.when = builder.getWhen();
        this.finalizers = builder.getFinalizers();
    }

    /**
     * Evaluates the rule
     *
     * Evaluates the presence and truth of when predicate. <br/>
     * Fires the finalizers <br/>
     *
     * @param feed object provided as input to feed the rule
     */
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
