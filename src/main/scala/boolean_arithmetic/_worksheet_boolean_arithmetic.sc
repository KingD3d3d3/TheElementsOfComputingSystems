import boolean_arithmetic._

"HalfAdder"
HalfAdder(false,false).toString()
HalfAdder(false,true).toString()
HalfAdder(true,false).toString()
HalfAdder(true,true).toString()

"FullAdder"
FullAdder(false,false,false).toString()
FullAdder(false,false,true).toString()
FullAdder(false,true,false).toString()
FullAdder(false,true,true).toString()

FullAdder(true,false,false).toString()
FullAdder(true,false,true).toString()
FullAdder(true,true,false).toString()
FullAdder(true,true,true).toString()

"Adder"
Adder(List(true,false,true,true), List(false,false,true,false)).out
Adder(List(true,false,false,true), List(false,true,false,true)).out
Adder(List(true,false,true,true), List(false,true,true,true)).out
Adder(List(false,false,false,false), List(false,false,false,false)).out

val one = (true :: List.fill(2)(false)).reverse

Incrementer(List(true,true,true,true)).out

"ALU"
val x = List.fill(16)(false)
val y = List.fill(16)(true)
ALU(x, y, true, false, true, false, true, false).toString()
ALU(x, y, true, true, true, true, true, true).toString()
ALU(x, y, true, true, true, false, true, false).toString()
ALU(x, y, false, false, true, true, false, false).toString()
ALU(x, y, true, true, false, false, false, false).toString()
ALU(x, y, false, false, true, true, false, true).toString()
ALU(x, y, true, true, false, false, false, true).toString()
ALU(x, y, false, false, true, true, true, true).toString()
ALU(x, y, true, true, false, false, true, true).toString()
ALU(x, y, false, true, true, true, true, true).toString()
ALU(x, y, true, true, false, true, true, true).toString()
ALU(x, y, false, false, true, true, true, false).toString()
ALU(x, y, true, true, false, false, true, false).toString()
ALU(x, y, false, false, false, false, true, false).toString()
ALU(x, y, false, true, false, false, true, true).toString()
ALU(x, y, false, false, false, true, true, true).toString()
ALU(x, y, false, false, false, false, false, false).toString()
ALU(x, y, false, true, false, true, false, true).toString()

val x1 = List(false,false,false,false,false,false,false,false
,false,false,false,true,false,false,false,true)
val y1 = List(false,false,false,false,false,false,false,false
  ,false,false,false,false,false,false,true,true)

ALU(x1, y1, true, false, true, false, true, false).toString()
ALU(x1, y1, true, true, true, true, true, true).toString()
ALU(x1, y1, true, true, true, false, true, false).toString()
ALU(x1, y1, false, false, true, true, false, false).toString()
ALU(x1, y1, true, true, false, false, false, false).toString()
ALU(x1, y1, false, false, true, true, false, true).toString()
ALU(x1, y1, true, true, false, false, false, true).toString()
ALU(x1, y1, false, false, true, true, true, true).toString()
ALU(x1, y1, true, true, false, false, true, true).toString()
ALU(x1, y1, false, true, true, true, true, true).toString()
ALU(x1, y1, true, true, false, true, true, true).toString()
ALU(x1, y1, false, false, true, true, true, false).toString()
ALU(x1, y1, true, true, false, false, true, false).toString()
ALU(x1, y1, false, false, false, false, true, false).toString()
ALU(x1, y1, false, true, false, false, true, true).toString()
ALU(x1, y1, false, false, false, true, true, true).toString()
ALU(x1, y1, false, false, false, false, false, false).toString()
ALU(x1, y1, false, true, false, true, false, true).toString()