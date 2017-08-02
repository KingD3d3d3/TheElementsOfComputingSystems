package boolean_arithmetic

/**
  * Created by silve on 22/01/2017.
  */
case class Adder(as: List[Boolean], bs: List[Boolean]) {

  def out: List[Boolean] = {
    def outIt(acc : (List[Boolean],List[Boolean], Boolean)) : List[Boolean] = acc match {
      case (Nil, Nil, _) => Nil

      case (x :: xs, y :: ys, c) => {
        val nextCarry = FullAdder(x, y, c).carry
        FullAdder(x, y, c).sum :: outIt(xs, ys, nextCarry)
      }

      case _ => throw new java.util.NoSuchElementException("inputs don't have the same size"
        + "as size : " + as.length + " != bs size : " + bs.length)
    }
    outIt(as.reverse, bs.reverse, false).reverse
  }
}
