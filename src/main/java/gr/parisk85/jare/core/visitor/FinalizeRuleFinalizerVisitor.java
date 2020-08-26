package gr.parisk85.jare.core.visitor;

public class FinalizeRuleFinalizerVisitor<T> implements RuleFinalizerVisitor<T> {
    private final T feed;

    public static <T> FinalizeRuleFinalizerVisitor<T> feed(final T feed) {
        return new FinalizeRuleFinalizerVisitor<>(feed);
    }

    private FinalizeRuleFinalizerVisitor(final T feed) {
        this.feed = feed;
    }

    @Override
    public void visit(final ThenRuleFinalizer<T> visitable) {
        visitable.getConsumer().accept(feed);
    }

    @Override
    public void visit(final ThenThrowRuleFinalizer<T> visitable) {
        ThrowingSupplier.get(visitable.getThenThrow());
    }
}
