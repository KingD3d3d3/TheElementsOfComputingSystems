package boolean_arithmetic

import boolean_logic._

/**
  * Created by silve on 22/01/2017.
  */
case class HalfAdder(a: Boolean, b: Boolean) {

  def sum: Boolean = {
    Xor(a,b).out
  }

  def carry: Boolean = {
    And(a, b).out
  }

  override def toString(): String = {
    ("\n carry : " + carry
      + "\n sum : " + sum)
  }
}
