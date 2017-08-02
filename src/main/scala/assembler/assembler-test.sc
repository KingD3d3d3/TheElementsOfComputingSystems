import assembler.Parser.symbol
import assembler.Parser.advance

import scala.io.Source
import assembler.Code

import scala.Console.println

"(xx)".filterNot(c => c == '(' || c == ')')

" dest = comp ; jump ".filterNot(_ == ' ')

// Test with '=' for dest
"dest=comp;jump".split("=", -1).head
"=comp;jump".split("=", -1)
"=comp;jump".split("=", -1).head

// Test with ';' for jump
"dest=comp;jump".split(";", -1).last
"dest=comp;".split(";", -1)
"dest=comp;".split(";", -1).last

// Test with comp
"dest=comp;jump".contains('=') && "dest=comp;jump".contains(';')
"dest=comp;jump".split(Array('=',';'))
"=comp;jump".split(Array('=',';'))
"=comp;".split(Array('=',';')).tail.head

"comp;jump".split(";", -1).head

"D;JGT  1// comment".split("//", -1)

"Binary convert test"

def decimalToBinary(i: Int, digits: Int = 15) =
  String.format("%" + digits + "s", i.toBinaryString).replace(' ', '0')
def toAInstruction(binary: String) : String = {
  '0' + binary
}

val sym = symbol("@2")
val decimal = decimalToBinary(sym.toInt)
val code = toAInstruction(decimal)



def isAllDigits(x: String) = {
  (x!="") && (x forall Character.isDigit)
}
def isNotDecimal(x: String) = {
  !isAllDigits(x)
}
isAllDigits("ki")
isAllDigits("")
isAllDigits("02")

"025".toInt

isNotDecimal("res")
isNotDecimal("123")
isNotDecimal("^^")

"Test Map"
var m = Map("SP" -> 0,
  "LCL" -> 1,
  "ARG" -> 2)

m += ("R0" -> 0)

m.contains("LCL")
m.contains("R0")

m("SP")
m("ARG")

val inputFile = "Rect.asm"

// Input file
val inputDir ="D:\\Bibliotheques\\Documents\\Scala_Projects\\TheElements_of_ComputingSystems\\TheElementsOfComputingSystems\\src\\main\\scala\\assembler\\TestPrograms\\"
val it1 = Source.fromFile(inputDir + inputFile).getLines()
val it2 = Source.fromFile(inputDir + inputFile).getLines()
it1.next()
it1.next()
it2.next()