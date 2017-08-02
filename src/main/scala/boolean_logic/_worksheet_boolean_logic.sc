import boolean_logic._

Mux16(List(true,true,false), List(false,true,false), false).out
Mux16(List(true,true,false), List(false,true,false), false).out
Mux16(List(true,true,false), List(false,true,false), false).out
Mux16(List(true,true,false), List(false,true,false), false).out

Mux16(List(true,true,false), List(false,true,false), true).out
Mux16(List(true,true,false), List(false,true,false), true).out
Mux16(List(true,true,false), List(false,true,false), true).out
Mux16(List(true,true,false), List(false,true,false), true).out

Or8Way(List(false,false,false)).out

And8Way(List(false,false,true)).out

// Myx4Way
Mux4Way16(List(false,false), List(false,true),
  List(true,false),List(true,true),
  false, false).out

Mux4Way16(List(false,false), List(false,true),
  List(true,false),List(true,true),
  false, true).out

Mux4Way16(List(false,false), List(false,true),
  List(true,false),List(true,true),
  true, false).out

Mux4Way16(List(false,false), List(false,true),
  List(true,false),List(true,true),
  true, true).out

And8Way(List(true,true,true)).out

// DMux4Way

'a'
DMux8Way(in = true, List(false, false, false)).toString()
DMux8Way(in = false, List(false, false, false)).toString()

'b'
DMux8Way(in = true, List(false, false, true)).toString()
DMux8Way(in = false, List(false, false, true)).toString()

'c'
DMux8Way(in = true, List(false, true, false)).toString()
DMux8Way(in = false, List(false, true, false)).toString()

'd'
DMux8Way(in = true, List(false, true, true)).toString()
DMux8Way(in = false, List(false, true, true)).toString()

'e'
DMux8Way(in = true, List(true, false, false)).toString()
DMux8Way(in = false, List(true, false, false)).toString()

'f'
DMux8Way(in = true, List(true, false, true)).toString()
DMux8Way(in = false, List(true, false, true)).toString()

'g'
DMux8Way(in = true, List(true, true, false)).toString()
DMux8Way(in = false, List(true, true, false)).toString()

'h'
DMux8Way(in = true, List(true, true, true)).toString()
DMux8Way(in = false, List(true, true, true)).toString()

// Myx8Way
Mux8Way16(List(false,false,false), List(false,false,true),
  List(false,true,false),List(false,true,true),
  List(true,false,false), List(true,false,true),
  List(true,true,false),List(true,true,true),
  false, false, false).out

Mux8Way16(List(false,false,false), List(false,false,true),
  List(false,true,false),List(false,true,true),
  List(true,false,false), List(true,false,true),
  List(true,true,false),List(true,true,true),
  false, false, true).out

Mux8Way16(List(false,false,false), List(false,false,true),
  List(false,true,false),List(false,true,true),
  List(true,false,false), List(true,false,true),
  List(true,true,false),List(true,true,true),
  false, true, false).out

Mux8Way16(List(false,false,false), List(false,false,true),
  List(false,true,false),List(false,true,true),
  List(true,false,false), List(true,false,true),
  List(true,true,false),List(true,true,true),
  false, true, true).out

Mux8Way16(List(false,false,false), List(false,false,true),
  List(false,true,false),List(false,true,true),
  List(true,false,false), List(true,false,true),
  List(true,true,false),List(true,true,true),
  true, false, false).out

Mux8Way16(List(false,false,false), List(false,false,true),
  List(false,true,false),List(false,true,true),
  List(true,false,false), List(true,false,true),
  List(true,true,false),List(true,true,true),
  true, false, true).out

Mux8Way16(List(false,false,false), List(false,false,true),
  List(false,true,false),List(false,true,true),
  List(true,false,false), List(true,false,true),
  List(true,true,false),List(true,true,true),
  true, true, false).out

Mux8Way16(List(false,false,false), List(false,false,true),
  List(false,true,false),List(false,true,true),
  List(true,false,false), List(true,false,true),
  List(true,true,false),List(true,true,true),
  true, true, true).out