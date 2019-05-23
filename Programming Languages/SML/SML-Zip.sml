fun zip nil lis2 = nil
 | zip lis1 nil = nil
 | zip(x::xs) (y::ys) = (x,y)::(zip xs ys);	