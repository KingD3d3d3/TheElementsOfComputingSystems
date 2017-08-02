import java.io.File

import virtual_machine.Util
import virtual_machine.Parser._

import scala.Console.println

val l = List("add",
  "sub",
  "neg",
  "eq",
  "gt",
  "lt",
  "and",
  "or",
  "not")
l.contains("add")

commandType("sub")
commandType("add //yolo")
commandType("// blabla lt")


val com = "          push        constant    7 //2 "
com.split(' ').tail.head
val c = com.last
val c2= Util.removeCommentAndTrim(com)
c2.split("\\s+").tail.head

c2.split("\\s+")(2).toInt

val inputDir ="D:\\Bibliotheques\\Documents\\Scala_Projects\\TheElements_of_ComputingSystems\\TheElementsOfComputingSystems\\src\\main\\scala\\virtual_machine\\TestPrograms_chap7\\"
val testFiles = new File(inputDir).list().filter(_.endsWith(".vm"))

Util.removeCommentAndTrim(" label         b").split("\\s+").length
commandType("call a 1")
commandType("     call      bererer   2 // ")

commandType("   return // eeee")

arg1("    label      a // comment")

val inputFile = "FibonacciSeries.vm"
val vmFileName = inputFile.trim.split('.').head

val header = "(HEAD)\n"
val body = "A = M\n"
val list = for(i <- 0 until 10) yield body
header + list.mkString("")

var listFunction = List("null")
listFunction.head

listFunction = "hello" :: listFunction
listFunction ::= "a"
listFunction

if(listFunction.head != "null"){
  listFunction = listFunction.tail
}

listFunction

if(listFunction.head != "null"){
  listFunction = listFunction.tail
}

listFunction

if(listFunction.head != "null"){
  listFunction = listFunction.tail
}

listFunction