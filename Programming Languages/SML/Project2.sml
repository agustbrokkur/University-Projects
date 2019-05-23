(*Sml 1*)

fun zip nil lis2 = nil
 | zip lis1 nil = nil
 | zip(x::xs) (y::ys) = (x,y)::(zip xs ys);

fun greaterThan list item =
    case list of
    nil=>nil
      | xs::ys => if item >= xs then greaterThan ys item
          else xs::greaterThan ys item;

fun reduction f lis =
if length (tl lis) = 0
then (hd lis)
else f ((hd lis), reduction f (tl lis));

fun partition f nil      = (nil,nil)
  | partition f (x::xs) = 
          let val (xsa,xsb) = partition f xs 
          in if f x then (x::xsa,xsb)
             else        (xsa,x::xsb)
          end;
         

(*Sml 2*)

fun insert (x, nil) = [x]
|   insert (x, (y::ys)) =
        if(x:real) < (y:real)
        then x :: y :: ys
        else y :: (insert(x, ys));

fun insertsort nil = nil
|   insertsort (x::xs) = 
    insert (x, (insertsort xs));
    
fun find(list, n) = 
    if n = 0
    then (hd list)
    else find((tl list), (n - 1));

fun middle list =
    find(list, (length list div 2));
 
fun cartesian(xs, ys) =
   let fun frst(x,nil) = nil
       | frst(x, y::ys) = (x,y)::frst(x,ys);
        
fun scnd(nil, ys) = nil
      | scnd(x::xs, ys) = frst(x, ys) @ scnd(xs,ys)
   in scnd(xs,ys) end;

fun mymap(f) (x::z) = 
  f(x)::mymap(f) z
  | mymap(f) nil = nil;
    

(*Test Case*)
zip [1,2,3] ["a","b","c"];
zip [1,2] ["a"];
greaterThan [1,5,3,2,4] 3;
reduction op+ [1,3,5,7,9];
reduction op* [1,3,5,7,9];
partition Char.isLower [#"P",#"a",#"3",#"%",#"b"];

insert (3.3, [1.1, 2.2, 4.4, 5.5]);
insertsort [2.2, 4.4, 5.5, 3.3, 1.1];
middle [1,2,3,4,5];
middle [true,false];
cartesian ["a","b","c"] [1,2];
val mapsquare = map (fn x => x * x);
(mymap (fn x => x*x)) [1,2,3,4]
