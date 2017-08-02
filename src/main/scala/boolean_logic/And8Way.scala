package boolean_logic

/**
  * Created by silve on 21/01/2017.
  */
case class And8Way(ins: List[Boolean]) {

  def out: Boolean = {
    def outIt(acc : List[Boolean]) : Boolean = acc match {
      case Nil => true
      case c :: cs => And(c, outIt(cs)).out
    }
    outIt(ins)
  }

}