package gr.parisk85.jare.core.visitor;

public interface RuleFinalizer<T> {
    void accept(RuleVisitor<T> ruleVisitor);
}
