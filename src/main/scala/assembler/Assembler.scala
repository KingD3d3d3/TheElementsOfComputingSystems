package assembler

import java.io._
import scala.io.Source
import assembler.Parser._
import java.util.Scanner
//import scala.io.StdIn._
import scala.Console._
import assembler.SymbolTable._

/**
  * Created by silve on 02/03/2017.
  */
object Assembler extends App {

  val inputFile = "RectL.asm"
  val outputFile = "RectL2.hack"

  println("Assembler program")
  println("Input : " + inputFile)

  // Input file
  val inputDir ="D:\\Bibliotheques\\Documents\\Scala_Projects\\TheElements_of_ComputingSystems\\TheElementsOfComputingSystems\\src\\main\\scala\\assembler\\TestPrograms\\"

  // Iterators
  val firstPass = Source.fromFile(inputDir + inputFile).getLines()
  val secondPass = Source.fromFile(inputDir + inputFile).getLines()
  val thirdPass = Source.fromFile(inputDir + inputFile).getLines()

  // Output file
  val outputDir = "./src/main/scala/assembler/TestPrograms/"

  buildLabelSymbolTable(firstPass, 0)
  buildVariableSymbolTable(secondPass, 16)

  println("**********************")
  val pw = new PrintWriter(new File(outputDir + outputFile)) // open writer
  assemble(thirdPass, pw) // convert to machine code
  pw.close // close the writer

  println("Binary code generated")
  println("Output : " + outputFile)

  /**
    * Build the symbol table for label symbols (Xxx)
    */
  def buildLabelSymbolTable(input: Iterator[String], romAddress: Int): Unit = {

    if(hasMoreCommands(input)) {

      val currentLine = advance(input)
      commandType(currentLine) match {
        case "L_COMMAND" => {
          val sym = symbol(currentLine) //get the symbol

          if(isNotDecimal(sym)){
            addEntry(sym, romAddress) // address refers to the instruction memory location of next command
            //println("L_COMMAND added (symbol, address) : " + (sym, romAddress))
          }

          buildLabelSymbolTable(input, romAddress)
        }
        case "" => {
          buildLabelSymbolTable(input, romAddress)
        }
        case _ => { // C_COMMAND or A_COMMAND
          buildLabelSymbolTable(input, romAddress + 1) // increment instruction address
        }
      }
    }
  }

  /**
    * Build the symbol table for variable symbols Xxx
    */
  def buildVariableSymbolTable(input: Iterator[String], ramAddress: Int): Unit = {

    if(hasMoreCommands(input)) {

      val currentLine = advance(input)
      commandType(currentLine) match {
        case "A_COMMAND" => {
          var nextRamAddress = ramAddress

          val sym = symbol(currentLine) //get the symbol
          if(isNotDecimal(sym)){
            if(!contains(sym)) {
              addEntry(sym, ramAddress)
              //println("A_COMMAND added (symbol, address) : " + (sym, ramAddress))
              nextRamAddress += 1 // increment variable address
            }
          }

          buildVariableSymbolTable(input, nextRamAddress)
        }
        case _ => {
          buildVariableSymbolTable(input, ramAddress)
        }
      }
    }
  }

  /**
    * Main function : read the next line, determine the type of command and write its code into the file
    */
  def assemble(input: Iterator[String], pw: PrintWriter): Unit = {

    if(hasMoreCommands(input)) {
      val currentLine = advance(input)
      commandType(currentLine) match {
        case "A_COMMAND" => {
          val sym = symbol(currentLine)
          var decimal = -1

          // Have a symbol
          if(isNotDecimal(sym)) {
            if(contains(sym)) {
              decimal = getAddress(sym) // get the address associated with the symbol from symbol table
            }
          }
          else { // No symbol
            decimal = sym.toInt
          }

          if(decimal < 0) throw new IllegalArgumentException("constants must be non-negative")
          val bin = decimalToBinary(decimal)
          val code = toAInstruction(bin)
          pw.write(code + '\n')
          assemble(input, pw)
        }
        case "C_COMMAND" => {
          val d = dest(currentLine)
          val c = comp(currentLine)
          val j = jump(currentLine)
          val code = toCInstruction(Code.comp(c) + Code.dest(d) + Code.jump(j))
          pw.write(code + '\n')
          assemble(input, pw)
        }
        case _ => assemble(input, pw) // L_COMMAND, comment, blank line etc
      }
    }
  }

  /**
    *  Convert an integer to binary string of 15 digits
    */
  def decimalToBinary(i: Int, digits: Int = 15) =
    String.format("%" + digits + "s", i.toBinaryString).replace(' ', '0')

  def toAInstruction(binary: String) : String = {
    '0' + binary
  }

  def toCInstruction(binary: String) : String = {
    "111" + binary
  }

  /**
    *  Check if the string is a decimal number
    */
  def isAllDigits(x: String) = {
    (x!="") && (x forall Character.isDigit)
  }

  /**
    *  Check if the string is not a decimal number
    */
  def isNotDecimal(x: String) = {
    !isAllDigits(x)
  }
}
