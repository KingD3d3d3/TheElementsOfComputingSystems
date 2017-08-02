package virtual_machine

import java.io.{BufferedWriter, File, FileWriter}

/**
  * Created by silve on 07/03/2017.
  */
case class CodeWriter(output: String, append: Boolean, var VMFileName: String) {

  /// Chapter 7 : Virtual Machine I : Stack Arithmetic ///

  val bw = new BufferedWriter(new FileWriter(new File(output), append)) // true for append

  var labelCounter = 0

  val incrementSP : String = "\t@SP\n" + // update SP
    "\tM=M+1\n"

  //var listFunctionName = List("null")
  var currentFuncName = "null"

  /**
    * Writes the assembly code that is the translation of the given arithmetic command
    */
  def writeArithmetic(command: String): Unit = {

    Util.removeSpaceAndComment(command) match {
      case "" => ???

      // 2 arguments operations
      case "add" =>
        val asm = twoArgArithmetic('+')
        bw.write(asm)

      case "sub" =>
        val asm = twoArgArithmetic('-')
        bw.write(asm)

      case "and" =>
        val asm = twoArgArithmetic('&')
        bw.write(asm)

      case "or" =>
        val asm = twoArgArithmetic('|')
        bw.write(asm)

      // 1 argument operation
      case "neg" =>
        val asm = oneArgArithmetic('-')
        bw.write(asm)

      case "not" =>
        val asm = oneArgArithmetic('!')
        bw.write(asm)

      // 2 arguments comparison
      case "eq" =>
        val asm = twoArgComparison("JEQ", labelCounter)
        labelCounter += 1
        bw.write(asm)

      case "gt" =>
        val asm = twoArgComparison("JGT", labelCounter) // JGT
        labelCounter += 1
        bw.write(asm)

      case "lt" =>
        val asm = twoArgComparison("JLT", labelCounter) // JLT
        labelCounter += 1
        bw.write(asm)

      case _ => throw new java.util.NoSuchElementException("invalid arithmetic command : " + command)
    }
  }

  def twoArgArithmetic(operator: Char) : String = {
      "\t@SP\n" +
      "\tM=M-1\n" +
      "\tA=M\n" +
      "\tD=M\n" +                 // store first arg
      "\t@SP\n" +
      "\tM=M-1\n" +
      "\tA=M\n" +
      "\tM=M"+ operator + "D\n" + //operation
      incrementSP
  }

  def oneArgArithmetic(operator: Char) : String = {
      "\t@SP\n" +
      "\tM=M-1\n" +
      "\tA=M\n" +
      "\tM=" + operator + "M\n" + // operation
      incrementSP
  }

  def twoArgComparison(operator: String, labelNum: Int) : String = {
      "\t@SP\n" +
      "\tM=M-1\n" +
      "\tA=M\n" +
      "\tD=M\n" +  // store first arg
      "\t@SP\n" +
      "\tM=M-1\n" +
      "\tA=M\n" +
      "\tD=M-D\n" + // output to compare (x - y)
      "\t@TRUE_" + labelNum + "\n" +
      "\tD;"+ operator + "\n" + // if x operator y => jump to (TRUE)
      "\t@SP\n" + // FALSE
      "\tA=M\n" +
      "\tM=0\n" + // x = false
      "\t@UPDATE_SP_" + + labelNum + "\n" +
      "\t0;JMP\n" +
      "(TRUE_" + labelNum + ")\n" + // TRUE
      "\t@SP\n" +
      "\tA=M\n"+
      "\tM=-1\n" + // x = true
      "(UPDATE_SP_" + labelNum + ")\n" + // Update SP
      incrementSP
  }

  /**
    * Writes the assembly code that is the translation of the given command, where command is either C_PUSH or C_POP
    */
  def writePushPop(command: String, segment: String, index: Int): Unit = {

    var asm = ""
    command match {

      case "C_PUSH" =>

        segment match {
          case "constant" =>
            asm = pushConstant("" + index)

          case "argument" =>
            asm = pushTemplate("ARG", index)

          case "local" =>
            asm = pushTemplate("LCL", index)

          case "this" =>
            asm = pushTemplate("THIS", index)

          case "that" =>
            asm = pushTemplate("THAT", index)

          case "static" =>
            asm = pushStatic(index)

          case seg if seg == "temp" && index >=0 && index <= 7 =>
            val tmpNum = index + 5
            asm = pushTemp("R", "" + tmpNum)

          case seg if seg == "pointer" && index == 0 =>
            asm = pushPointer("THIS")

          case seg if seg == "pointer" && index == 1 =>
            asm = pushPointer("THAT")

          case _ => throw new java.util.NoSuchElementException("not a valid segment : " + segment)
        }


      case "C_POP" =>

        segment match {
          case "argument" =>
            asm = popTemplate("ARG", index)

          case "local" =>
            asm = popTemplate("LCL", index)

          case "this" =>
            asm = popTemplate("THIS", index)

          case "that" =>
            asm = popTemplate("THAT", index)

          case "static" =>
            asm = popStatic(index)

          case seg if seg == "temp" && index >=0 && index <= 7 =>
            val tmpNum = index + 5
            asm = popTemp("R", "" + tmpNum)

          case seg if seg == "pointer" && index == 0 =>
            asm = popPointer("THIS")

          case seg if seg == "pointer" && index == 1 =>
            asm = popPointer("THAT")

          case _ => throw new java.util.NoSuchElementException("not a valid segment : " + segment)
        }


      case _ => throw new java.util.NoSuchElementException("not a push or pop command : " + command)
    }

    bw.write(asm)
  }


  /**
    * Push a constant onto stack
    */
  def pushConstant(index: String) : String = {
      "\t@" + index + "\n" + // store index
      "\tD=A\n" +
      "\t@SP\n" + // push onto stack
      "\tA=M\n" +
      "\tM=D\n" +
      incrementSP
  }

  /**
    * Push from segment ARG, LCL, THIS, THAT onto stack
    */
  def pushTemplate(segment: String, index: Int) : String = {
      "\t@" + index + "\n" + // store index
      "\tD=A\n" +
      "\t@" + segment + "\n" + // get segment[index]
      "\tA=M+D\n" +
      "\tD=M\n" +
      "\t@SP\n" + // push onto stack
      "\tA=M\n" +
      "\tM=D\n" +
      incrementSP
  }

  /**
    * Pop from stack onto segment ARG, LCL, THIS, THAT
    */
  def popTemplate(segment: String, index: Int) : String = {
      "\t@" + segment + "\n" +
      "\tD=M\n" + // get base
      "\t@" + index + "\n" +
      "\tD=D+A\n" + // base + index
      "\t@R13\n" +  // R13 store the segment[index] address
      "\tM=D\n" +
      "\t@SP\n" + // get value from stack
      "\tA=M-1\n" +
      "\tD=M\n" +
      "\t@SP\n" + // update SP (decrement)
      "\tM=M-1\n" +
      "\t@R13\n" + // pop to segment[index]
      "\tA=M\n" +
      "\tM=D\n"
  }

  /**
    * Push from Temp onto stack
    */
  def pushTemp(segment: String, index: String) : String = {
      "\t@" + segment + index + "\n" + // get segment value
      "\tD=M\n" +
      "\t@SP\n" + // push onto stack
      "\tA=M\n" +
      "\tM=D\n" +
      incrementSP
  }

  /**
    * Push segment address onto stack
    */
  def pushDirect(segment: String) : String = pushTemp(segment, "")

  /**
    * Pop segment address onto stack
    */
  def popDirect(segment: String) : String = popTemp(segment, "")

  /**
    * Pop from stack onto Temp
    */
  def popTemp(segment: String, index: String) : String = {
      "\t@SP\n" + // get value from stack
      "\tA=M-1\n" +
      "\tD=M\n" +
      "\t@SP\n" + // update SP (decrement)
      "\tM=M-1\n" +
      "\t@" + segment + index + "\n" + // change segment base value
      "\tM=D\n"
  }

  /**
    * Push from static onto stack
    */
  def pushStatic(index: Int) : String = pushTemp("", VMFileName + "." + index) // old method : pushTemp("", index + 16)
  /**
    * Pop from stack onto static
    */
  def popStatic(index: Int) : String =  popTemp("", VMFileName + "." + index) // old method : popTemp("", index + 16)

  /**
    * Push from pointer onto stack
    */
  def pushPointer(segment: String) : String = {
      "\t@" + segment + "\n" + // get segment value
      "\tD=M\n" +
      "\t@SP\n" + // push onto stack
      "\tA=M\n" +
      "\tM=D\n" +
      incrementSP
  }

  /**
    * Pop from stack onto pointer
    */
  def popPointer(segment: String) : String = {
      "\t@SP\n" + // get value from stack
      "\tA=M-1\n" +
      "\tD=M\n" +
      "\t@SP\n" + // update SP (decrement)
      "\tM=M-1\n" +
      "\t@" + segment + "\n" + // change segment base value
      "\tM=D\n"
  }

  /**
    * Close the output file
    */
  def close(): Unit = bw.close()

  /// Chapter 8 : Virtual Machine II : Program Control ///

  /**
    * Writes the assembly code that effects the VM initialization, also called bootstrap code
    * This code must be placed at the beginning of the output file
    */
  def writeInit(): Unit = {
    val asm =
      "\t@256\n" + // SP = 256
      "\tD=A\n" +
      "\t@SP\n" +
      "\tM=D\n" +
      callFunction("Sys.init", 0, labelCounter)
      labelCounter += 1
      bw.write(asm)
  }

  /**
    * Writes assembly code that effects the label command
    */
  def writeLabel(label: String): Unit = {
    //val asm = "(" + listFunctionName.head + '$' + label + ")\n"
    val asm = "(" + currentFuncName + '$' + label + ")\n"
    bw.write(asm)
  }

  /**
    * Writes assembly code that effects the goto command
    */
  def writeGoto(label: String): Unit = {
//    val asm = "\t@" + listFunctionName.head + '$' + label + "\n" +
//              "\t0;JMP\n"
    val asm = "\t@" + currentFuncName + '$' + label + "\n" +
                "\t0;JMP\n"
    bw.write(asm)
  }

  /**
    * Writes assembly code that effects the if-goto command
    */
  def writeIf(label: String): Unit = {
//    val asm = "\t@SP\n" + // get value from stack
//      "\tAM=M-1\n" + // go to address and decrement SP
//      "\tD=M\n" +
//      "\t@" + listFunctionName.head + '$' + label + "\n" +
//      "\tD;JNE\n" // jump if stack top value is not 0

    val asm = "\t@SP\n" + // get value from stack
      "\tAM=M-1\n" + // go to address and decrement SP
      "\tD=M\n" +
      "\t@" + currentFuncName + '$' + label + "\n" +
      "\tD;JNE\n" // jump if stack top value is not 0

    bw.write(asm)
  }

  /**
    * Writes assembly code that effects the call command
    */
  def callFunction(functionName: String, numArgs: Int, labelNum: Int): String = {
      pushConstant("RETURN_LABEL" + labelNum) +
      pushDirect("LCL") +
      pushDirect("ARG") +
      pushDirect("THIS") +
      pushDirect("THAT") +
      "\t@SP\n" + // ARG = SP - n - 5
      "\tD=M\n" +
      "\t@5\n" +
      "\tD=D-A\n" +
      "\t@" + numArgs + "\n" +
      "\tD=D-A\n" +
      "\t@ARG\n" +
      "\tM=D\n" +
      "\t@SP\n" +   // LCL = SP
      "\tD=M\n" +
      "\t@LCL\n" +
      "\tM=D\n" +
      "\t@" + functionName + "\n" + // goto f
      "\t0;JMP\n" +
      "(RETURN_LABEL" + labelNum + ")\n" // label of return-address
  }

  /**
    * Writes assembly code that effects the call command
    */
  def writeCall(functionName: String, numArgs: Int): Unit = {
    val asm = callFunction(functionName, numArgs, labelCounter)
    labelCounter += 1
    bw.write(asm)
  }
  /**
    * Writes assembly code that effects the function command
    */
  def writeFunction(functionName: String, numLocals: Int): Unit = {
    //listFunctionName ::= functionName // add at the beginning of the list
    currentFuncName = functionName // update function name
    val header = "(" + functionName + ")\n" // function label
    val body = pushConstant("" + 0) + popTemplate("LCL", 0) + incrementSP // initialize local variables onto stack

    val v = for(i <- 0 until numLocals) yield body

    val asm = header + v.mkString("")
    bw.write(asm)
  }

  /**
    * Writes assembly code that effects the return command
    * R11 for FRAME and R12 for RET
    */
  def writeReturn(): Unit = {

//    // Remove the last function
//    if(listFunctionName.head != "null"){
//      listFunctionName = listFunctionName.tail
//    }

    val asm =
      "\t@LCL\n" +            // FRAME = LCL //
      "\tD=M\n" +
      "\t@FRAME\n" +
      "\tM=D\n" +
      "\t@5\n" +              // RET = *(FRAME-5) //
      "\tA=D-A\n" + // A = FRAME - 5
      "\tD=M\n" + // D = *(FRAME - 5)
      "\t@RET\n" +
      "\tM=D\n" + // RET = *(FRAME - 5)
      popTemplate("ARG", 0) + // *ARG=pop()
      "\t@ARG\n" +            // SP = ARG + 1 //
      "\tD=M\n" +
      "\t@SP\n" +
      "\tM=D+1\n" +
      preFrameTemplate("THAT", 1) +
      preFrameTemplate("THIS", 2) +
      preFrameTemplate("ARG", 3) +
      preFrameTemplate("LCL", 4) +
      "\t@RET\n" +            // goto RET //
      "\tA=M\n" +
      "\t0;JMP\n"
    bw.write(asm)
  }

  def preFrameTemplate(segment: String, index: Int): String = {
      "\t@" + index + "\n" +
      "\tD=A\n" + // D = index
      "\t@FRAME\n" +
      "\tA=M-D\n" + // A = FRAME - index
      "\tD=M\n" + // D = *(FRAME - index)
      "\t@" + segment + "\n" +
      "\tM=D\n" // seg = *(FRAME - index)
  }


}
