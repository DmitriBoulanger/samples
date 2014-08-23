
What is Thread Local?

Thread Local can be considered as a scope of access, 
like a request scope or session scope. 
It's a thread scope. You can set any object in Thread Local 
and this object will be global and local to the specific 
thread which is accessing this object. Global and local!!? Let me explain:

    Values stored in Thread Local are global to the thread, 
    meaning that they can be accessed from anywhere inside that thread. 
    If a thread calls methods from several classes, 
    then all the methods can see the Thread Local variable 
    set by other methods (because they are executing in same thread). 
    The value need not be passed explicitly. It's like how you use global variables.
    
    Values stored in Thread Local are local to the thread, 
    meaning that each thread will have it's own Thread Local variable. 
    One thread can not access/modify other thread's Thread Local variables.
    
When to use Thread Local?

We saw what is thread local in the above section. 
Now let's talk about the use cases. i.e. when you'll be needing something like Thread Local.
I can point out one use case where I used thread local. 

Consider you have a Servlet which calls some business methods. 
You have a requirement to generate a unique transaction id for each and every request 
this servlet process and you need to pass this transaction id to the business methods, 
for logging purpose. One solution would be passing this transaction id as a parameter 
to all the business methods. But this is not a good solution 
as the code is redundant and unnecessary.

To solve that, you can use Thread Local. You can generate a transaction 
id (either in servlet or better in a filter) and set it in the Thread Local. 
After this, what ever the business method, that this servlet calls, 
can access the transaction id from the thread local.

This servlet might be servicing more that one request at a time. 
Since each request is processed in separate thread, 
the transaction id will be unique to each thread (local) and will be accessible 
from all over the thread's execution (global).

See http://veerasundar.com/blog/2010/11/java-thread-local-how-to-use-and-code-sample/