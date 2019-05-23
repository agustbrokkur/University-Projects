fun find(list, n) = 
    if n = 0
    then (hd list)
    else find((tl list), (n - 1));

fun middle list =
    find(list, (length list div 2));
    