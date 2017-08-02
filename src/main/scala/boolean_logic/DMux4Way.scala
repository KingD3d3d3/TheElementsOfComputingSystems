package boolean_logic

/**
  * Created by silve on 18/01/2017.
  */
case class DMux4Way(in : Boolean, sel: List[Boolean]) {

  private val sel1 = sel.head
  private val sel0 = sel(1)

  def a: Boolean = {
    And8Way(List(in, Not(sel1).out, Not(sel0).out)).out
  }

  def b: Boolean = {
    And8Way(List(in, Not(sel1).out, sel0)).out
  }

  def c: Boolean = {
    And8Way(List(in, sel1, Not(sel0).out)).out
  }

  def d: Boolean = {
    And8Way(List(in, sel1, sel0)).out
  }

  override def toString(): String = {
    ("\n a : " + a
      + "\n b : " + b
      + "\n c : " + c
      + "\n d : " + d)
  }
}
