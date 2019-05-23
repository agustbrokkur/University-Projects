fun partition f nil      = (nil,nil)
  | partition f (x::xs) = 
          let val (xsa,xsb) = partition f xs 
          in if f x then (x::xsa,xsb)
             else        (xsa,x::xsb)
          end;
          