package gr.parisk85.jare.core.visitor;

import java.util.function.Supplier;

/**
 * Provides a static wrapper method for re-throwing supplied exception.
 *
 * @author parisk85
 */
interface ThrowingSupplier {

    static <E extends Exception> void get(final Supplier<E> supplier) {
        try {
            throw supplier.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
