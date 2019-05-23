(* Ágúst Helgi Árnason 
	FML
	Assignment 4*)

(*
	1. 
	(i) *)
fun segs3 [] 	 = []
  | segs3 [x] 	 = []
  | segs3 [x, y] = []
  | segs3 (x :: y :: z :: xs) = [x, y, z] :: segs3 (y :: z :: xs);

(*
	(ii)
*)
fun avgs3 [] = []
  | avgs3 y  =
      let
        val a = segs3 y
      in
        map(fn x => (hd x + hd (tl x) + hd (tl (tl x))) / 3.0) a
      end;
	  
(*  
	2.
	(i)
*)
fun replicate (x, 0) = []
  | replicate (x, y) = x :: replicate (x, y - 1);
  
(*  
	(ii)
*)
fun uncompress [] 		 = []
  | uncompress (x :: xs) =
      let
        val a = replicate x
      in
        a @ uncompress xs
      end;
	  
(* 
	(iii)
  fun checka y (x :: xs) = if y = x then y :: checka y xs else [];
*)
local 
  fun checka y [] = []
    | checka y (x :: xs) = if y = x then y :: checka y xs else checka y xs;
  fun remove y [] = []
    | remove y (x :: xs) = if x = y then remove y xs else x :: remove y xs;
in
  fun maxSegsEq []		  = []
    | maxSegsEq [x] 	  = [[x]]
	| maxSegsEq (x :: xs) =
		let
		  val a = checka x (x :: xs)
		  val b = remove x xs
		in
		  a :: maxSegsEq b
		end;
end;

(* 
	(iv)
*)
fun compress [] = []
  | compress x =
      let
        val a = maxSegsEq x
      in
        map (fn f => (hd f, length f)) a
      end;

(* 
	3.
	(i)
*)
fun follows f [] 	= true
  | follows f [x] 	= true
  | follows f (x :: y :: xs) = f (x, y) andalso follows f (y :: xs);

(* 
	(ii)
*)
fun validCompr [] = false
  | validCompr [(x1, x2)] 					= follows op< [0, x2]
  | validCompr ((x1, x2) :: (y1, y2) :: xs) =
  follows op<> [x1, y1] andalso follows op< [0, x2] andalso follows op< [0, y2] andalso validCompr ((y1, y2) :: xs);

(* 
	4.
	(i)
*)
local 
  fun powa a b = if b <= 0.0 then 1.0 else a * powa a (b - 1.0);
in
  fun evalPoly [] x = 0.0
    | evalPoly y x  =
		let
		  val r = rev y
		  val rr = rev (tl r)
		  val size = real ((length y) - 1)
		  val mult = powa x size
		in
		  if size >= 0.0 then ((hd r) * mult) + evalPoly rr x else 0.0
		end;
end;

(* 
	(ii)
*)
fun addPoly [] [] = []
  | addPoly [] x  = x
  | addPoly y []  = y
  | addPoly y x =
      let
        val a : real = hd y + hd x
      in
        a :: addPoly (tl y) (tl x)
      end;

(* 
	(iii)
*)
fun multiPoly [] [] = []
  | multiPoly y []  = []
  | multiPoly [] x  = []
  | multiPoly y (x :: xs) =
      let
	    val d = map (fn f => f * x) y
	  in
	    addPoly d (multiPoly ([0.0] @ y) xs)
	  end;













































