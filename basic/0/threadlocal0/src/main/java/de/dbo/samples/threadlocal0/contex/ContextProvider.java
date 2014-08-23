package de.dbo.samples.threadlocal0.contex;

/**
 * this class acts as a container to our thread local variables.
 * @author vsundar
 *
 */
public class ContextProvider {

    public static final ThreadLocal<Context> userThreadLocal = new ThreadLocal<Context>();

    public static void set(Context user) {
        userThreadLocal.set(user);
    }

    public static void unset() {
        userThreadLocal.remove();
    }

    public static Context get() {
        return userThreadLocal.get();
    }
}
