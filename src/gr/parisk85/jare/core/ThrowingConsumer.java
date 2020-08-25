package gr.parisk85.jare.core;

import java.util.function.Supplier;

interface ThrowingSupplier<E extends Exception> {
    static <E extends Exception> void get(Supplier<E> t) {
        try {
            throw t.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
