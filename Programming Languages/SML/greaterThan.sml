fun greaterThan list item =
    case list of
    nil=>nil
      | xs::ys => if item >= xs then greaterThan ys item
          else xs::greaterThan ys item;
          