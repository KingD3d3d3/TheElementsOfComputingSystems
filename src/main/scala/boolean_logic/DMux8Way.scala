package boolean_logic

/**
  * Created by silve on 18/01/2017.
  */
case class DMux8Way(in : Boolean, sel: List[Boolean]) {

  private val sel2 = sel.head
  private val sel1 = sel(1)
  private val sel0 = sel(2)

  def a: Boolean = {
    And8Way(List(in, Not(sel2).out,  Not(sel1).out, Not(sel0).out)).out
  }

  def b: Boolean = {
    And8Way(List(in, Not(sel2).out, Not(sel1).out, sel0)).out
  }

  def c: Boolean = {
    And8Way(List(in, Not(sel2).out, sel1, Not(sel0).out)).out
  }

  def d: Boolean = {
    And8Way(List(in, Not(sel2).out, sel1, sel0)).out
  }

  def e: Boolean = {
    And8Way(List(in, sel2, Not(sel1).out, Not(sel0).out)).out
  }

  def f: Boolean = {
    And8Way(List(in, sel2, Not(sel1).out, sel0)).out
  }

  def g: Boolean = {
    And8Way(List(in, sel2, sel1, Not(sel0).out)).out
  }

  def h: Boolean = {
    And8Way(List(in, sel2, sel1, sel0)).out
  }

  override def toString(): String = {
    ("\n a : " + a
    + "\n b : " + b
    + "\n c : " + c
    + "\n d : " + d
    + "\n e : " + e
    + "\n f : " + f
    + "\n g : " + g
    + "\n h : " + h)
  }
}
