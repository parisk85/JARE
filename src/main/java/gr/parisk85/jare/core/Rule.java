package gr.parisk85.jare.core;

/**
 * Rule is an interface to provide abstraction for rule implementations.
 *
 * @author parisk85
 */
public interface Rule<T> {

    /**
     * The run method evaluates the rule.
     *
     * @param feed object provided as input to feed the rule.
     */
    void run(T feed);

}
