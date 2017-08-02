package sequential_logic

import boolean_logic._

/**
  * Created by silve on 27/01/2017.
  */

/**
  * Data Flip Flop
  * out[t] = in[t-1]
  */
case class DFF(var in: Boolean) {

  private var q: Boolean = false // output Q

  def out(clk: Boolean) : Boolean = clk match {
    case true => {
      // Update Q on clock rising-edge
      q = in
      q
    }
    case false => q // previous state
  }
}
