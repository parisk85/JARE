package gr.parisk85.jare.core.visitor;

/**
 * Visitor {@link RuleVisitor} implementation class
 *
 * @author parisk85
 */
public class FinalizeVisitor<T> implements RuleVisitor<T> {
    private final T feed;

    public static <T> FinalizeVisitor<T> feed(final T feed) {
        return new FinalizeVisitor<>(feed);
    }

    private FinalizeVisitor(final T feed) {
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
