% Assignment 5
% FML
% �g�st Helgi �rnason
%
% 1.
% (i)
second([_,X|_], X).

% (ii)
penultim([X,_], X).
penultim([_|Xs], Y) :-  penultim(Xs, Y).

% (iii)
replSecond([X,_|Xs], Y, [X,Y|Xs]).

% (iv)
replPenultim([_,Xs], Y, [Y,Xs]).
replPenultim([X|Xs], Y, [X|Ys]) :- replPenultim(Xs, Y, Ys).

% 2.
% ()
uniqueElems([_]).
uniqueElems([X, Y]) :- X \== Y.
uniqueElems([X, Y|Xs]) :- X \== Y  ,uniqueElems([X|Xs]), uniqueElems([Y|Xs]).

% 3.
% ()
shuffle([], [], []).
shuffle([X|Xs], [], [X|Zs]) :- shuffle(Xs, [], Zs).
shuffle([], [Y|Ys], [Y|Zs]) :- shuffle([], Ys, Zs).
shuffle([X|Xs], [Y|Ys], [X|Zs]) :- shuffle(Xs, [Y|Ys], Zs).
shuffle([X|Xs], [Y|Ys], [Y|Zs]) :- shuffle([X|Xs], Ys, Zs).

% 4.
% (i)
rowCheck(X, X, X, X).

winner(P, [A1, A2, A3,
          B1, B2, B3,
          C1, C2, C3]) :- (P == x ; P == y),
          (rowCheck(P, A1, A2, A3) ;
          rowCheck(P, B1, B2, B3) ;
          rowCheck(P, C1, C2, C3) ;
          rowCheck(P, A1, B1, C1) ;
          rowCheck(P, A2, B2, C2) ;
          rowCheck(P, A3, B3, C3) ;
          rowCheck(P, A1, B2, C3) ;
          rowCheck(P, C1, B2, A3)).

% (ii)
symbolCheck(D) :- (D == x; D == o; D == free).
symbolCheck(A, B, C) :- symbolCheck(A), symbolCheck(B), symbolCheck(C).

move(_, [], []).
move(X, [Y|Ys], [Z|Zs]) :- symbolCheck(X,Y,Z), X == Z, Y == free, Ys == Zs.
move(X, [Y|Ys], [Z|Zs]) :- symbolCheck(X,Y,Z), Y == Z, move(X, Ys, Zs).

