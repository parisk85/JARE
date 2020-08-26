package gr.parisk85.jare.core.visitor;

import java.util.function.Supplier;

public class ThenThrowRuleFinalizer<T> implements RuleFinalizer<T> {
    private final Supplier<Exception> thenThrow;

    public ThenThrowRuleFinalizer(final Supplier<Exception> thenThrow) {
        this.thenThrow = thenThrow;
    }

    public Supplier<Exception> getThenThrow() {
        return thenThrow;
    }

    @Override
    public void accept(final RuleFinalizerVisitor<T> ruleFinalizerVisitor) {
        ruleFinalizerVisitor.visit(this);
    }
}
