fun cartesian(xs, ys) =
    let fun frst(x,nil) = nil
        | frst(x, y::ys) = (x,y)::frst(x,ys);
        
    fun scnd(nil, ys) = nil
        | scnd(x::xs, ys) = frst(x, ys) @ scnd(xs,ys)
    in scnd(xs,ys) end;
    