package virtual_machine

import java.io.File

import virtual_machine.Parser._

import scala.Console.println
import scala.io.Source

/**
  * Created by silve on 09/03/2017.
  */
object VirtualMachine extends App {

  val inputFile = "SimpleFunction.vm"
  val outputFile = "SimpleFunction.asm"

  // Input file
  val inputDir ="D:\\Bibliotheques\\Documents\\Scala_Projects\\TheElements_of_ComputingSystems\\TheElementsOfComputingSystems\\src\\main\\scala\\virtual_machine\\TestPrograms_chap8\\"

  // Iterators
  val firstPass = Source.fromFile(inputDir + inputFile).getLines()

  // Output file
  val outputDir = "./src/main/scala/virtual_machine/TestPrograms_chap8/"

  // Program for 1 file
  // Setup writer
//  val vmFileName = inputFile.trim.split('.').head
 // val cw = CodeWriter(outputDir + outputFile, append = false, vmFileName)  // setup writer
//  println("Virtual Machine Translator")
  //
  //  println("Input : " + inputFile)
  //
  //  println("**********************")
  //  translate(firstPass, cw) // convert to machine code
  //  cw.close() // end of translation
  //
  //  println("Assembly code generated")
  //  println("Output : " + outputFile)
  //
  //  println("**********************")

   //Program all files //////////////////////////////////////////////

  val inDir ="D:\\Bibliotheques\\Documents\\Scala_Projects\\TheElements_of_ComputingSystems\\TheElementsOfComputingSystems\\src\\main\\scala\\virtual_machine\\StaticsTest\\"
  val outDir = "./src/main/scala/virtual_machine/StaticsTest/"

  // show vm files in the directory
  val vmFiles = new File(inDir).list().filter(_.endsWith(".vm"))
  for(file <- vmFiles){
    println("vm file : " + file)
  }

  // write to 1 file
  val outputResFile = "StaticsTest.asm"
  val cwRes = new CodeWriter(outDir + outputResFile, true, "")  // setup writer

  cwRes.writeInit() // bootstrap code
  for(file <- vmFiles){ // iterate through all files
    cwRes.VMFileName = file.trim.split('.').head // current vm file for variables
    val iterator = Source.fromFile(inDir + file).getLines()
    println("translate : " + file)
    translate(iterator, cwRes) // convert to machine code
  }
  cwRes.close() // end of translation

  println("Assembly code generated")
  println("Output : " + outputResFile)
  ///////////////////////////////////////////////////////////////////////

  /**
    * Main function : read the next line, determine the type of command and write its code into the file
    */
  def translate(input: Iterator[String], cw: CodeWriter): Unit = {

    if(hasMoreCommands(input)) {
      val currentLine = advance(input)
      commandType(currentLine) match {

        case "C_ARITHMETIC" =>
          val com = arg1(currentLine)
          cw.writeArithmetic(com)
          translate(input, cw)

        case "C_PUSH" =>
          val segment = arg1(currentLine)
          val index = arg2(currentLine)
          cw.writePushPop("C_PUSH", segment, index)
          translate(input, cw)

        case "C_POP" =>
          val segment = arg1(currentLine)
          val index = arg2(currentLine)
          cw.writePushPop("C_POP", segment, index)
          translate(input, cw)

        case "C_LABEL" =>
          val label = arg1(currentLine)
          cw.writeLabel(label)
          translate(input, cw)

        case "C_GOTO" =>
          val label = arg1(currentLine)
          cw.writeGoto(label)
          translate(input, cw)

        case "C_IF" =>
          val label = arg1(currentLine)
          cw.writeIf(label)
          translate(input, cw)

        case "C_CALL" =>
          val functionName = arg1(currentLine)
          val numArgs = arg2(currentLine)
          cw.writeCall(functionName, numArgs)
          translate(input, cw)

        case "C_FUNCTION" =>
          val functionName = arg1(currentLine)
          val numLocals = arg2(currentLine)
          cw.writeFunction(functionName, numLocals)
          translate(input, cw)

        case "C_RETURN" =>
          cw.writeReturn()
          translate(input, cw)

        case _ => translate(input, cw) // comment, blank line etc
      }
    }
  }
}
