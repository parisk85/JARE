package gr.parisk85.jare.core;

public interface Rule<T> {
    Class<T> type();
    void run(T feed);
}
