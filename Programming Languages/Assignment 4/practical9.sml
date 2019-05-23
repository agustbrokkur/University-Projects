(* 
	Practical 9: SML
*)

(* 
	1.
*)

fun member f [] = false
  | member f (x :: xs) = x = f orelse member f xs;
   
fun occursN a 0 c = true
  | occursN a b [] = false
  | occursN a b (x :: xs) = if a = x then occursN a (b - 1) xs else occursN a b xs;
  
(* 
	2.
*)

fun removeFirst y [] = []
  | removeFirst y (x :: xs) = if x = y then xs else x :: removeFirst y xs;
  
fun remove y [] = []
  | remove y (x :: xs) = if x = y then remove y xs else x :: remove y xs;
  
fun nub [] = []
  | nub (x :: xs) = x :: nub (remove x xs);
  
(* 
	3.
*)

fun countRemove y [] = (0, [])
  | countRemove y (x :: xs) =
      let
        val (a, b) = countRemove y xs
      in
        if y = x then (1 + a, b) else (a, x :: b)
      end;
	  
fun freqs [] = []
  | freqs (x :: xs) =
      let
        val (a, b) = countRemove x xs
      in
        (x, a + 1) :: freqs b
      end;
	  
(* 
	4. 
*)
	  
fun group3 [] = []
  | group3 [x] = [[x]]
  | group3 [x, y] = [[x, y]]
  | group3 (x :: y :: z :: xs) = [x, y, z] :: group3 xs;
  
(* 
	5.
*)

fun takeDrop y [] = ([], [])
  | takeDrop 0 xs = ([], xs)
  | takeDrop y (x :: xs) =
      let
        val (a, b) = takeDrop (y - 1) xs
      in
        (x :: a, b)
      end;
	  
fun groupsN n [] = []
  | groupsN n xs =
      let
        val (a, b) = takeDrop n xs
      in
        a :: groupsN n b
      end;
