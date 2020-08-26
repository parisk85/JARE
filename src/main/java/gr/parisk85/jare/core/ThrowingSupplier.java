package gr.parisk85.jare.core;

import java.util.function.Supplier;

interface ThrowingSupplier {
    static <E extends Exception> void get(final Supplier<E> supplier) {
        try {
            throw supplier.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
