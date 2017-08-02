package sequential_logic

import boolean_logic._

/**
  * Created by silve on 28/01/2017.
  */

/**
  * 16-Bit register.
  * If load[t-1]=1 then out[t] = in[t-1]
  * else out does not change (out[t] = out[t-1])
  */
case class Register(var ins: List[Boolean], var load: Boolean) {

  // Components
  private var bits = List.fill(ins.length)(Bit(false,false))

  def out(clk: Boolean): List[Boolean] = {
    def outIt(acc : (List[Boolean], List[Bit])) : List[Boolean] = acc match {
      case (Nil, Nil) => Nil
      case (i :: is, b :: bs) => {
        b.in = i
        b.load = load
        b.out(clk) :: outIt((is, bs))
      }
      case _ => throw new java.util.NoSuchElementException("data size != number of bits inside register")
    }
    outIt((ins, bits))
  }

}
