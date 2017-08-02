package virtual_machine

/**
  * Created by silve on 07/03/2017.
  */
object Parser {

  /**
    * Are there more commands in the input ?
    */
  def hasMoreCommands(input: Iterator[String]) : Boolean = {
    input.hasNext
  }

  /**
    * Reads the next command from the input and makes it the current command
    * Should be called only if hasMoreCommands() is true
    * Intiatially there is no current command
    */
  def advance(input: Iterator[String]) : String = {
    input.next()
  }

  /**
    * Returns the type of the current VM command:
    * -> C_ARITHMETIC
    * -> C_PUSH
    * -> C_POP
    * -> C_LABEL
    * -> C_GOTO
    * -> C_IF
    * -> C_FUNCTION
    * -> C_RETURN
    * -> C_CALL
    */
  def commandType(currentCommand: String) : String = {
    val arithmeticCommand = List(
      "add",
      "sub",
      "neg",
      "eq",
      "gt",
      "lt",
      "and",
      "or",
      "not")

    Util.removeCommentAndTrim(currentCommand) match { // remove spaces and discard comments
      case "" => ""                                                 // empty line
      case s if arithmeticCommand.contains(s) => "C_ARITHMETIC"
      case s if s.startsWith("push ") => "C_PUSH"
      case s if s.startsWith("pop ") => "C_POP"
      case s if s.startsWith("label") && s.split("\\s+").length == 2 => "C_LABEL"
      case s if s.startsWith("goto") && s.split("\\s+").length == 2 => "C_GOTO"
      case s if s.startsWith("if-goto") && s.split("\\s+").length == 2 => "C_IF"
      case s if s.startsWith("function") && s.split("\\s+").length == 3 => "C_FUNCTION"
      case s if s.startsWith("call") && s.split("\\s+").length == 3 => "C_CALL"
      case "return" => "C_RETURN"
      case _ => ???
    }
  }

  /**
    * Returns the first argument of the current command.
    * In the case of C_ARITHMETIC, the command itself (add, sub, etc.) is returned.
    * Should not be called if the current command is C_RETURN
    */
  def arg1(currentCommand: String): String = {
    val com = Util.removeCommentAndTrim(currentCommand)
    commandType(com) match {
      case "C_ARITHMETIC" => com // just return the command itself
      case "" => ???
      case "C_RETURN" => throw new IllegalArgumentException("Should not be called if the current command is C_RETURN")
      case _ => com.split("\\s+").tail.head // first argument of the command is returned
    }
  }

  /**
    * Returns the second argument of the current command.
    * Should be called only if the current command is C_PUSH, C_POP, C_FUNCTION or C_CALL
    */
  def arg2(currentCommand: String): Int = {
    val com = Util.removeCommentAndTrim(currentCommand)
    commandType(com) match {
      case t if t == "C_PUSH" ||
        t == "C_POP" ||
        t == "C_FUNCTION" ||
        t == "C_CALL"  => com.split("\\s+")(2).toInt // second argument of the command is returned
      case _ => ???
    }
  }

}
