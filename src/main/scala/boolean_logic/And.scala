package boolean_logic

/**
  * Created by silve on 18/01/2017.
  */
case class And(a: Boolean, b: Boolean) {
  def out: Boolean = {
    val tmp = Nand(a,b).out
    Not(tmp).out
  }
}
