
There are two common approaches to making nonthreadsafe code 
safe for concurrent use—synchronization and segregated instances. 
Using synchronization means a single instance of the unsafe class 
may be used, but sacrifices "liveness" because only 
one thread can use the object at any one time. 
Segregated instances work by creating a new object for each client, 
which wastes more resources but does not sacrifice liveness. 
You choose which approach is better depending on the requirements 
of your application—do you want to sacrifice resources or liveness 
for thread safety? The thread-local approach offers a third way by 
creating an object per thread, so different threads cannot interfere 
with each other's objects. This approach maintains liveness, 
but not at the expense of resources because only so many instances of threads are created.


See http://www.drdobbs.com/jvm/using-thread-local-variables-in-java/jvm/using-thread-local-variables-in-java/184405382#rl2