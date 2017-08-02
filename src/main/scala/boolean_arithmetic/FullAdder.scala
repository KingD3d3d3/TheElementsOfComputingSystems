package boolean_arithmetic

import boolean_logic._

/**
  * Created by silve on 22/01/2017.
  */
case class FullAdder(a: Boolean, b: Boolean, c: Boolean) {

  def sum: Boolean = {
    val sum1 = HalfAdder(b,c).sum
    HalfAdder(a,sum1).sum
  }

  def carry: Boolean = {
    val sum1 = HalfAdder(b,c).sum
    val carry1 = HalfAdder(b,c).carry

    Or(HalfAdder(a,sum1).carry, carry1).out
  }

  override def toString(): String = {
    ("\n carry : " + carry
    + "\n sum : " + sum)
  }
}
