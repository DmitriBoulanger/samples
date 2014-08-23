package de.dbo.samples.threadlocal0.contex;

public class Service {

    public void businessMethod() {
        // get the context from thread local
        final Context context = ContextProvider.get();
        System.out.println(context.getTransactionId());
    }
}
