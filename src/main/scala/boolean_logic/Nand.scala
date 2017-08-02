package boolean_logic

/**
  * Created by silve on 18/01/2017.
  */
case class Nand(a: Boolean, b: Boolean) {

  def out: Boolean = !(a && b)
}
