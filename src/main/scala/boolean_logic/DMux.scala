package boolean_logic

/**
  * Created by silve on 18/01/2017.
  */
case class DMux(in : Boolean, sel: Boolean) {

  def a: Boolean = {
    And(in, Not(sel).out).out
  }

  def b: Boolean = {
    And(in, sel).out
  }

  override def toString(): String = {
    ("\n a : " + a
      + "\n b : " + b)
  }
}
