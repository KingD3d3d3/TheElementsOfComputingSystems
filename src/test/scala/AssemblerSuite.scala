import assembler._
import org.scalatest.FunSuite

class AssemblerSuite extends FunSuite {

  test("An empty Set should have size 0") {
    assert(Set.empty.size == 0)
  }

  test("Invoking head on an empty Set should produce NoSuchElementException") {
    assertThrows[NoSuchElementException] {
      Set.empty.head
    }
  }

  test("Dest mnemonic binary coding"){
    assert(Code.dest("") == "000")
    assert(Code.dest("M") == "001")
    assert(Code.dest("D") == "010")
    assert(Code.dest("MD") == "011")
    assert(Code.dest("A") == "100")
    assert(Code.dest("AM") == "101")
    assert(Code.dest("AD") == "110")
    assert(Code.dest("AMD") == "111")
    assertThrows[NoSuchElementException] {
      Code.dest(null)
    }
    assertThrows[NoSuchElementException] {
      Code.dest("dummy")
    }
  }

  test("Comp mnemonic binary coding"){

    // when a = 0
    assert(Code.comp("0") == "0101010")
    assert(Code.comp("1") == "0111111")
    assert(Code.comp("-1") == "0111010")
    assert(Code.comp("D") == "0001100")
    assert(Code.comp("A") == "0110000")
    assert(Code.comp("!D") == "0001101")
    assert(Code.comp("!A") == "0110001")
    assert(Code.comp("-D") == "0001111")
    assert(Code.comp("-A") == "0110011")

    assert(Code.comp("D+1") == "0011111")
    assert(Code.comp("A+1") == "0110111")
    assert(Code.comp("D-1") == "0001110")
    assert(Code.comp("A-1") == "0110010")
    assert(Code.comp("D+A") == "0000010")
    assert(Code.comp("D-A") == "0010011")
    assert(Code.comp("A-D") == "0000111")
    assert(Code.comp("D&A") == "0000000")
    assert(Code.comp("D|A") == "0010101")

    // when a = 1
    assert(Code.comp("M") == "1110000")
    assert(Code.comp("!M") == "1110001")
    assert(Code.comp("-M") == "1110011")
    assert(Code.comp("M+1") == "1110111")

    assert(Code.comp("M-1") == "1110010")
    assert(Code.comp("D+M") == "1000010")
    assert(Code.comp("D-M") == "1010011")
    assert(Code.comp("M-D") == "1000111")
    assert(Code.comp("D&M") == "1000000")
    assert(Code.comp("D|M") == "1010101")

    assertThrows[NoSuchElementException] {
      Code.comp(null)
    }
    assertThrows[NoSuchElementException] {
      Code.comp("dummy")
    }
  }

  test("Jump mnemonic binary coding"){
    assert(Code.jump("") == "000")
    assert(Code.jump("JGT") == "001")
    assert(Code.jump("JEQ") == "010")
    assert(Code.jump("JGE") == "011")
    assert(Code.jump("JLT") == "100")
    assert(Code.jump("JNE") == "101")
    assert(Code.jump("JLE") == "110")
    assert(Code.jump("JMP") == "111")
    assertThrows[NoSuchElementException] {
      Code.jump(null)
      Code.jump("dummy")
    }
  }

  test("Remove spaces characters and comment of a command"){
    assert(Parser.removeSpaceAndComment("D;JGT            // if D>0") == "D;JGT")
    assert(Parser.removeSpaceAndComment("// D;JGT") == "")
  }

  test("Command type"){
    assert(Parser.commandType("// This file is part of www.nand2tetris.org") == "")
    assert(Parser.commandType("") == "")
    assert(Parser.commandType("@2") == "A_COMMAND")
    assert(Parser.commandType("D=A") == "C_COMMAND")
    assert(Parser.commandType("(INFINITE_LOOP)") == "L_COMMAND")

    assert(Parser.commandType("D;JGT            // if D>0") == "C_COMMAND")
    assert(Parser.commandType("0;JMP            // goto output_d") == "C_COMMAND")
    assert(Parser.commandType("@R0            // A = R0") == "A_COMMAND")
    assert(Parser.commandType("(OUTPUT_FIRST)            // comment") == "L_COMMAND")
    assert(Parser.commandType("// D;JGT") == "")
  }

  test("Get symbol of an A_COMMAND or L_COMMAND"){
    assert(Parser.symbol("@R0") == "R0")
    assert(Parser.symbol("@R0 // comment") == "R0")
    assert(Parser.symbol("(OUTPUT_FIRST) // comment") == "OUTPUT_FIRST")

    assertThrows[IllegalArgumentException] {
      Parser.symbol("0;JMP")
      Parser.symbol("// comment")
      Parser.symbol(null)
    }
  }

  test("Get dest mnemonic of a C_COMMAND"){
    assert(Parser.dest("D=A") == "D")
    assert(Parser.dest("0;JMP") == "")
    assert(Parser.dest("D=M            // if D>0") == "D")

    assertThrows[IllegalArgumentException] {
      Parser.dest("@R0")
      Parser.dest("(OUTPUT_FIRST) // comment")
      Parser.dest("// comment")
      Parser.dest(null)
    }
  }

  test("Get comp mnemonic of a C_COMMAND"){
    assert(Parser.comp("D=M;JGT") == "M")
    assert(Parser.comp("1 // comment") == "1")
    assert(Parser.comp("D=A") == "A")
    assert(Parser.comp("0;JMP") == "0")
    assert(Parser.comp("D;JGT            // if D>0") == "D")

    assertThrows[IllegalArgumentException] {
      Parser.comp("@R0")
      Parser.comp("(OUTPUT_FIRST) // comment") == "OUTPUT_FIRST"
      Parser.comp("// comment")
      Parser.comp(null)
    }
  }

  test("Get jump mnemonic of a C_COMMAND"){
    assert(Parser.jump("D=A") == "")
    assert(Parser.jump("0;JMP") == "JMP")
    assert(Parser.jump("D;JGT            // if D>0") == "JGT")

    assertThrows[IllegalArgumentException] {
      Parser.jump("@R0")
      Parser.jump("(OUTPUT_FIRST) // comment") == "OUTPUT_FIRST"
      Parser.jump("// comment")
      Parser.jump(null)
    }
  }
}
