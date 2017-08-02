package boolean_logic

/**
  * Created by silve on 18/01/2017.
  */
case class Or8Way(ins: List[Boolean]) {

  def out: Boolean = {
    def outIt(acc : List[Boolean]) : Boolean = acc match {
      case Nil => false
      case c :: cs => Or(c, outIt(cs)).out
    }
    outIt(ins)
  }

}
