package assembler

/**
  * Created by silve on 04/03/2017.
  */
object SymbolTable {

  val predefinedSymbols = Map(
    "SP" -> 0,
    "LCL" -> 1,
    "ARG" -> 2,
    "THIS" -> 3,
    "THAT" -> 4,
    "R0" -> 0,
    "R1" -> 1,
    "R2" -> 2,
    "R3" -> 3,
    "R4" -> 4,
    "R5" -> 5,
    "R6" -> 6,
    "R7" -> 7,
    "R8" -> 8,
    "R9" -> 9,
    "R10" -> 10,
    "R11" -> 11,
    "R12" -> 12,
    "R13" -> 13,
    "R14" -> 14,
    "R15" -> 15,
    "SCREEN" -> 16384,
    "KBD" -> 24576
  )

  var table: Map[String, Int] = predefinedSymbols // initialize with predifined symbols

  /**
    *  Adds the pair (symbol, address) to the table
    */
  def addEntry(symbol: String, address: Int): Map[String, Int] = {
    table += (symbol -> address)
    table
  }

  /**
    *  Does the symbol table contain the given symbol ?
    */
  def contains(symbol: String): Boolean = {
    table.contains(symbol)
  }

  /**
    *  Returns the address associated with the symbol
    */
  def getAddress(symbol: String): Int = {
    table(symbol)
  }
}
