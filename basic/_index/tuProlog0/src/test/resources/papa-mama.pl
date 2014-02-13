 
 % person(Name, Age, Sex)
 person(smith,34, male).
 person(janna,32,female).
 person(jimmy,6,male).
 
 parent(smith,jimmy).
 
 child(X) :- person(X,Age,_), Age<10.
 
 mama(X) :- person(X,_,female), parent(X,Y), child(Y).
 papa(X) :- person(X,_,male), parent(X,Y), child(Y).