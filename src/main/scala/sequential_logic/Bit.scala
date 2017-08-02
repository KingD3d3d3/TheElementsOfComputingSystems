package sequential_logic

import boolean_logic._

/**
  * Created by silve on 28/01/2017.
  */

/**
  * 1-bit register.
  * If load[t] == 1 then out[t+1] = in[t]
  *                 else out[t+1] = out[t] (no change)
  */
case class Bit(var in: Boolean, var load: Boolean) {

  private var q: Boolean = false // output Q

  // Components
  private var dff = DFF(in)

  def out(clk: Boolean) : Boolean =  {
    val mux = Mux(q, in, load).out
    dff.in = mux
    q = dff.out(clk) // update Q
    q
  }
}
