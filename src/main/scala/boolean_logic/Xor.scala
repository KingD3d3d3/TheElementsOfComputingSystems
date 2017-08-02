package boolean_logic

/**
  * Created by silve on 18/01/2017.
  */
case class Xor(a: Boolean, b: Boolean) {
  def out: Boolean = {
    val nand = Nand(a,b).out
    val or = Or(a,b).out
    And(nand,or).out
  }
}
