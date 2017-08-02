package boolean_arithmetic

import boolean_logic._

/**
  * Created by silve on 22/01/2017.
  */
case class ALU(xs: List[Boolean], ys: List[Boolean], zx: Boolean, nx: Boolean, zy: Boolean, ny: Boolean, f: Boolean, no: Boolean) {

  def out: List[Boolean] = {

    // zx then x = 0
    val x1 = Mux16(xs, List.fill(xs.length)(false), zx).out

    // nx then x = !x
    val notX1 = Not16(x1).out
    val xRes = Mux16(x1, notX1, nx).out

    // zy then y = 0
    val y1 = Mux16(ys, List.fill(ys.length)(false), zy).out

    // ny then y = !y
    val notY1 = Not16(y1).out
    val yRes = Mux16(y1, notY1, ny).out

    // f = 1 then x sum y
    // f = 0 then x & y
    val sum = Adder(xRes, yRes).out
    val and = And16(xRes, yRes).out
    val res = Mux16(and, sum, f).out

    // no then out = !out
    val notRes = Not16(res).out

    // Final result
    Mux16(res, notRes, no).out
  }

  def zr: Boolean = {
    // out = 0 then zr = 1
    // out != 0 then zr = 0
    val res = Or8Way(out).out
    Mux(true, false, res).out
  }

  def ng: Boolean = {
    val isNeg = And(out.head, true).out
    Mux(false, true, isNeg).out
  }

  override def toString(): String = {
    ("\n out : " + out
      + "\n zr : " + zr
      + "\n ng : " + ng)
  }
}
