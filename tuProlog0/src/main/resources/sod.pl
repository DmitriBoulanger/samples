
session(Id, EventIdSequence, Descriptor).
event(Id, SessionId, Descriptor).

anomalEvent(Id) :- event(Id,_,Descriptor), eventScore(Descriptor,Score), Score > 0.

anomalSession(Id) :- session(Id, _, Descriptor), sessionScore(Descriptor,Score), Score > 0.
normalSession(Id) :- session(Id, _, Descriptor), sessionIpAddrCounter(Descriptor, 1).
regularSession(Id) :- session(Id, EventIdSequence, _), anomalEventsRegular(EventIdSequence).

% A simple session has stable IP-Address and it is a regular session
simpleSession(Id) :- normalSession(Id),regularSession(Id).

% A sequence of events is regular if all its anomal events are the same modulo abstraction
anomalEventsRegular([],   _) :- !.
anomalEventsRegular([Id], _) :- anomalEvent(X), !.
anomalEventsRegular([Id|Ids], DescriptorAbstraction) :- anomalEvent(Id), !,
               event(Id,_,Descriptor), 
               eventAbstraction(Descriptor, DescriptorAbstraction),
               anomalEventsRegular(Ids, DescriptorAbstraction).
anomalEventsRegular([Id|Ids], DescriptorAbstraction) :- not( anomalEvent(Id)), !, 
               anomalEventsRegular(Ids, DescriptorAbstraction).
 
% Session Descriptor 
sessionScore(         sessionDescriptor(X, _, _), Score)  :- X = Score.
sessionIpAddrCounter( sessionDescriptor(_, Y, _), IpAddrCounter) :- Y = IpAddrCounter.

% Event Descriptor (element_h)
eventScore(       eventDescriptor(X, _, _), Score)        :- X = Score.
eventMessages(    eventDescriptor(_, Y, _), Messages)     :- Y = Messages.
eventAbstraction( eventDescriptor(X, Y, _), Abstraction)  :- eventDescriptor(X,Y) = Abstraction.

% Event Message
eventMessage( message(Id, EventId, OccurencesCnt, Text, TotalScore) ) :- 
	modSecurityMessage(Id, Score, Text, _), TotalScore is OccurencesCnt*Score.

% Catalog of the ModSecurity Messages 
modSecurityMessage(Id, Score, Text, _).







