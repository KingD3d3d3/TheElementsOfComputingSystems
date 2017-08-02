package boolean_logic

/**
  * Created by silve on 18/01/2017.
  */
case class Mux(a: Boolean, b: Boolean, sel: Boolean) {
  def out: Boolean = {
    val res1 = And(a, Not(sel).out).out
    val res2 = And(b, sel).out

    Or(res1, res2).out
  }
}
