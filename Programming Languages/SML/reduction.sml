fun reduction f lis =
if length (tl lis) = 0
then (hd lis)
else f ((hd lis), reduction f (tl lis));