package gr.parisk85.jare.core;

@FunctionalInterface
interface ThrowingSupplier<E extends Exception> {
    E get() throws Exception;

    static void throwingSupplierWrapper(final ThrowingSupplier<Exception> throwingSupplier) {
        try {
            throw throwingSupplier.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
