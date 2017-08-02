import sequential_logic.Ram64

"Ram64"
val data1 = List(true, false, true)
val a0 = List(false, false, false, false, false, false)
val ram = Ram64(data1, a0, true)
ram.out(false)
ram.out(true)
ram.out(false)
ram.out(true)
ram.out(false)

"change data input"
val data2 = List(false, true, false)
ram.ins = data2
ram.load = true
ram.out(false)
ram.out(true)
ram.out(false)

"addres 1"
val data5 = List(true, false, false)
val a1 = List(false, false, false, false, false, true)
ram.address = a1
ram.ins = data5
ram.out(false)
ram.out(true)
ram.out(false)
ram.out(true)
ram.out(false)

"change data input"
val data6 = List(true, true, false)
ram.ins = data6
ram.load = true
ram.out(false)
ram.out(true)
ram.out(false)