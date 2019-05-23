(* 
	Practical 8: SML
*)

(* 
	1.
*)
val f = fn n => n * 3 + 8;

fun f n = n * 3 + 8;

val g = fn m => f (m - 1);

fun g m = f (m - 1);

(* 
	2.
*)

val rec e2 = fn n => if n = 0 then 1 else 2 * e2 (n - 1);

fun e2 n = if n = 0 then 1 else 2 * e2 (n - 1);

(* 
	3.
*)

fun mc n = if n > 100 then n - 10 else mc (mc (n + 11));

(* 
	4.
*)

fun c91 (h : int -> int) k n m
	=if n > m then true else h n = k m andalso c91 h k (n + 1) m;
	
(* 
	5.
*)

feq mc (fn n => 91) 1 100;

feq mc (fn n => n - 10) 101 200;

(* 
	6.
*)

