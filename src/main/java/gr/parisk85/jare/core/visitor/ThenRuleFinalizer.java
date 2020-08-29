package gr.parisk85.jare.core.visitor;

import java.util.function.Consumer;

/**
 * Visitable {@link FinalizeVisitor} implementation class.
 *
 * @author parisk85
 */
public class ThenRuleFinalizer<T> implements RuleFinalizer<T> {
    private final Consumer<T> then;

    public static <T> ThenRuleFinalizer<T> of(final Consumer<T> consumer) {
        return new ThenRuleFinalizer<>(consumer);
    }

    private ThenRuleFinalizer(final Consumer<T> then) {
        this.then = then;
    }

    public Consumer<T> getThen() {
        return then;
    }

    @Override
    public void accept(final RuleVisitor<T> ruleVisitor) {
        ruleVisitor.visit(this);
    }
}
