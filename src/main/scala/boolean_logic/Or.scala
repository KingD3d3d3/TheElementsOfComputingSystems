package boolean_logic

/**
  * Created by silve on 18/01/2017.
  */
case class Or(a: Boolean, b: Boolean) {
  def out: Boolean = {
    val not1 = Not(a).out
    val not2 = Not(b).out
    Nand(not1,not2).out
  }
}
