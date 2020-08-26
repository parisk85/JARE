package gr.parisk85.jare.core.visitor;

public interface RuleVisitor<T> {
    void visit(ThenRuleFinalizer<T> visitable);
    void visit(ThenThrowRuleFinalizer<T> visitable);
}
