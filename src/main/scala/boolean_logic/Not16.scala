package boolean_logic

/**
  * Created by silve on 18/01/2017.
  */
case class Not16(ins: List[Boolean]) {

  def out: List[Boolean] = {
    def outIt(acc : List[Boolean]) : List[Boolean] = acc match {
      case Nil => Nil
      case c :: cs => Not(c).out :: outIt(cs)
    }
    outIt(ins)
  }

}
