log(_).

init(timer,counter).
_ <- incr returns 0.
_ <- elapsed returns 0.
_ <- reset.

size([],0) :- !.
size([_|T],Total) :- !, size(T,N), Total is N+1.