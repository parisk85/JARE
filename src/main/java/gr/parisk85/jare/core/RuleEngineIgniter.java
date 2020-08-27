package gr.parisk85.jare.core;

import java.util.function.Function;

interface RuleEngineIgniter<T> {
    RuleEngineIgniter<T> addRule(final Function<RuleBuilder<T>, Rule<T>> function);
    void fire();
}
