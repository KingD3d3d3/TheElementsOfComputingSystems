package boolean_arithmetic

/**
  * Created by silve on 22/01/2017.
  */
case class Incrementer(ins: List[Boolean]) {

  def out: List[Boolean] = {
    val one = (true :: List.fill(ins.length - 1)(false)).reverse
    Adder(ins, one).out
  }

}
