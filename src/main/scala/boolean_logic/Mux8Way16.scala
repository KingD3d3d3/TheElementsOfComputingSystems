package boolean_logic

/**
  * Created by silve on 18/01/2017.
  */
case class Mux8Way16(as: List[Boolean], bs: List[Boolean], cs: List[Boolean], ds: List[Boolean],
                    es: List[Boolean], fs: List[Boolean], gs: List[Boolean], hs: List[Boolean],
                     sel: List[Boolean]) {

  private val sel2 = sel.head
  private val sel1 = sel(1)
  private val sel0 = sel(2)

  def out: List[Boolean] = {
    def outIt(acc : (List[Boolean], List[Boolean], List[Boolean], List[Boolean],
                      List[Boolean], List[Boolean], List[Boolean], List[Boolean])) : List[Boolean] = acc match {
      case (Nil, Nil, Nil, Nil, Nil, Nil, Nil, Nil) => Nil
      case (_a :: _as, _b :: _bs, _c :: _cs, _d :: _ds, _e :: _es, _f :: _fs, _g :: _gs, _h :: _hs) => {

        val res1 = And8Way(List(_a, Not(sel2).out, Not(sel1).out, Not(sel0).out)).out
        val res2 = And8Way(List(_b, Not(sel2).out, Not(sel1).out, sel0)).out
        val res3 = And8Way(List(_c, Not(sel2).out, sel1, Not(sel0).out)).out
        val res4 = And8Way(List(_d, Not(sel2).out, sel1, sel0)).out

        val res5 = And8Way(List(_e, sel2, Not(sel1).out, Not(sel0).out)).out
        val res6 = And8Way(List(_f, sel2, Not(sel1).out, sel0)).out
        val res7 = And8Way(List(_g, sel2, sel1, Not(sel0).out)).out
        val res8 = And8Way(List(_h, sel2, sel1, sel0)).out

        Or8Way(List(res1, res2, res3, res4, res5, res6, res7, res8)).out :: outIt(_as, _bs, _cs, _ds, _es, _fs, _gs, _hs)
      }

      case _ => throw new java.util.NoSuchElementException("inputs don't have the same size"
        + "\n as size : " + as.length
        + "\n bs size : " + bs.length
        + "\n cs size : " + cs.length
        + "\n ds size : " + ds.length
        + "\n es size : " + es.length
        + "\n fs size : " + fs.length
        + "\n gs size : " + gs.length
        + "\n hs size : " + hs.length)
    }
    outIt(as, bs, cs, ds, es, fs, gs, hs)
  }

}
