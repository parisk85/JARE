package gr.parisk85.jare.core.visitor;

/**
 * Interface for visitable.
 *
 * @author parisk85
 */
public interface RuleFinalizer<T> {
    void accept(RuleVisitor<T> ruleVisitor);
}
