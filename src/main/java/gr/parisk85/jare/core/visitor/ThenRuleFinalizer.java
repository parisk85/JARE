package gr.parisk85.jare.core.visitor;

import java.util.function.Consumer;

public class ThenRuleFinalizer<T> implements RuleFinalizer<T> {
    private final Consumer<T> consumer;

    public static <T> ThenRuleFinalizer<T> of(final Consumer<T> consumer) {
        return new ThenRuleFinalizer<>(consumer);
    }

    private ThenRuleFinalizer(final Consumer<T> consumer) {
        this.consumer = consumer;
    }

    public Consumer<T> getConsumer() {
        return consumer;
    }

    @Override
    public void accept(final RuleVisitor<T> ruleVisitor) {
        ruleVisitor.visit(this);
    }
}
