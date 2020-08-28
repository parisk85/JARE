package gr.parisk85.jare.core.visitor;

/**
 * Interface for visitor pattern.
 *
 * @author parisk85
 */
interface RuleVisitor<T> {
    void visit(ThenRuleFinalizer<T> visitable);
    void visit(ThenThrowRuleFinalizer<T> visitable);
}
