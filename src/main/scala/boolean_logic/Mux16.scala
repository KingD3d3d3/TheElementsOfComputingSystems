package boolean_logic

/**
  * Created by silve on 18/01/2017.
  */
case class Mux16(as: List[Boolean], bs: List[Boolean], sel: Boolean) {

  def out: List[Boolean] = {
    def outIt(acc : (List[Boolean],List[Boolean])) : List[Boolean] = acc match {
      case (Nil, Nil) => Nil

      case (x :: xs, Nil) => throw new java.util.NoSuchElementException("inputs don't have the same size"
        + "as size : " + as.length + " != bs size : " + bs.length)
      case (Nil, y :: ys) => throw new java.util.NoSuchElementException("inputs don't have the same size"
        + "as size : " + as.length + " != bs size : " + bs.length)

      case (x :: xs, y :: ys) => Mux(x, y, sel).out  :: outIt(xs, ys)
    }
    outIt(as,bs)
  }

}
