fun insert (x, nil) = [x]
|   insert (x, (y::ys)) =
        if(x:real) < (y:real)
        then x :: y :: ys
        else y :: (insert(x, ys));

fun insertsort nil = nil
|   insertsort (x::xs) = 
    insert (x, (insertsort xs));
    
