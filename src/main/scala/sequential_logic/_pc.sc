import boolean_arithmetic.Incrementer
import sequential_logic.PC

val address1 = List(true, false ,true)
val pc = PC(address1, false, false ,false)
pc.out(false)
pc.out(true)
pc.out(false)
pc.out(true)

"load"
pc.load = true
pc.out(false)
pc.out(true)
pc.out(false)
pc.out(true)

"reset"
pc.reset = true
pc.out(false)
pc.out(true)
pc.out(false)
pc.out(true)

"increment"
pc.load = false
pc.reset = false
pc.inc = true
pc.out(false)
"1"
pc.out(true)
pc.out(false)
"2"
pc.out(true)
pc.out(false)
"3"
pc.out(true)
pc.out(false)
"4"
pc.out(true)
pc.out(false)
"5"
pc.out(true)
pc.out(false)

"nothing"
pc.inc = false
pc.out(false)
pc.out(true)
pc.out(false)
pc.out(true)

"reset"
pc.load = true
pc.inc = true
pc.reset = true
pc.out(false)
pc.out(true)
pc.out(false)
pc.out(true)