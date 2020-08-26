package gr.parisk85.jare.core;

public interface Rule<T> {
    void run(final T feed);
}
