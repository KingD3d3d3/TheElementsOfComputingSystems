package boolean_logic

/**
  * Created by silve on 18/01/2017.
  */
case class Mux4Way16(as: List[Boolean], bs: List[Boolean], cs: List[Boolean], ds: List[Boolean], sel1 : Boolean, sel0: Boolean) {

  def out: List[Boolean] = {
    def outIt(acc : (List[Boolean], List[Boolean], List[Boolean], List[Boolean])) : List[Boolean] = acc match {
      case (Nil, Nil, Nil, Nil) => Nil
      case (_a :: _as, _b :: _bs, _c :: _cs, _d :: _ds) => {

        val res1 = And8Way(List(_a, Not(sel1).out, Not(sel0).out)).out
        val res2 = And8Way(List(_b, Not(sel1).out, sel0)).out
        val res3 = And8Way(List(_c, sel1, Not(sel0).out)).out
        val res4 = And8Way(List(_d, sel1, sel0)).out

        Or8Way(List(res1, res2, res3, res4)).out :: outIt(_as, _bs, _cs, _ds)
      }

      case _ => throw new java.util.NoSuchElementException("inputs don't have the same size"
        + "as size : " + as.length + " bs size : " + bs.length + " cs size : " + cs.length + " ds size : " + ds.length)
    }
    outIt(as, bs, cs, ds)
  }
}
