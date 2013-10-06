% Main

findCycle(X,Y, Path, MaxLength) :- 
	  find(X, Y, PathForward, MaxLength)
 	, find(Y, X, PathBackward, MaxLength)
 	, append(PathForward,PathBackward,Path).

find(X, Y, Path, MaxLength) :-
 	log( info("searching path from ",X," to ",Y,". MaxLenght=",MaxLength," ...") )
       , init(Timer,Counter)
 	   , path(X,Y,Path)
 	   , size(Path,Length)
 	   , Length < MaxLength
 	   , Counter <- incr returns Cnt
 	   , Timer <- elapsed returns Elapsed
 	   , log( info(Cnt ," path=", Path, ". Length=", Length, " Elapsed: ", Elapsed," ms.") )
 	   , Timer <- reset.
 
% path definition (arc is a database)
path(X,Y,[arc(X,Y)]) :- arc_forward(X,Y).
path(X,Y,[arc(X,Z)|P]) :- arc_forward(X,Z), path(Z,Y,P).

arc_forward(X,Y) :- arc(X,Y), X<Y.

% already defined in the library
%append([], X, X).
%append([X|Tail], Ys, [X|Zs]) :- append(Tail, Ys, Zs).
