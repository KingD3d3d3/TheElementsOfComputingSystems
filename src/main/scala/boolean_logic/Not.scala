package boolean_logic

/**
  * Created by silve on 18/01/2017.
  */
case class Not(in: Boolean) {
  def out: Boolean = Nand(in, in).out
}
