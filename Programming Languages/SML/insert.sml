fun insert (elem, nil) = [elem]
|   insert (elem, (y::ys)) =
        if(elem:real) < (y:real)
        then elem :: y :: ys
        else y :: (insert(elem, ys));