package assembler

/**
  * Created by silve on 28/02/2017.
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
    * Returns the type of the current command:
    * -> A_COMMAND for @Xxx where Xxx is either a symbol or a decimal number
    * -> C_COMMAND for des=comp;jump
    * -> L_COMMAND (actually, pseudo-command) for (Xxx) where Xxx is a symbol
    */
  def commandType(currentCommand: String) : String =
    removeSpaceAndComment(currentCommand) match { // remove spaces and discard comments
    case "" => ""                                                 // empty line
    case c if c.startsWith("@") => "A_COMMAND"                    // addressing instruction
    case c if c.startsWith("(") && c.endsWith(")") => "L_COMMAND" // label symbols
    case _ => "C_COMMAND"                                         // compute instruction
  }

  def removeSpaceAndComment(command: String) : String = {
    command.filterNot(_ == ' ').split("//", -1).head
  }

  /**
    * Returns the symbol or decimal Xxx of the current command @Xxx or (Xxx)
    * Should be called only when commandType() is A_COMMAND or L_COMMAND
    */
  def symbol(currentCommand: String) : String = {
    val com = removeSpaceAndComment(currentCommand)
    commandType(com) match {
      case "A_COMMAND" => com.tail   // remove the first '@'
      case "L_COMMAND" => com.filterNot(c => c == '(' || c == ')') // remove '(' and ')'
      case _ => throw new IllegalArgumentException(com + " is neither an A_COMMAND nor L_COMMAND, command type : " + commandType(com))
    }
  }

  /**
    * Returns the dest mnemonic in the current C-command (8 possibilities)
    * Should be called only when commandType() is C_COMMAND
    */
  def dest(currentCommand: String) : String = {
    val com = removeSpaceAndComment(currentCommand)
    commandType(com) match {
      case "C_COMMAND" => {
        if(com.contains('=')) com.split("=", -1).head
        else "" // no dest mnemonic
      }
      case _ => throw new IllegalArgumentException(com + " isn't a C_COMMAND, command type : " + commandType(com))
    }
  }

  /**
    * Returns the comp mnemonic in the current C-command (28 possibilities)
    * Should be called only when commandType() is C_COMMAND
    */
  def comp(currentCommand: String) : String = {
    val com = removeSpaceAndComment(currentCommand)
    commandType(com) match {
      case "C_COMMAND" => {
        if(com.contains('=') && com.contains(';')) com.split(Array('=',';')).tail.head
        else if(com.contains('=')) com.split("=", -1).tail.head
        else if(com.contains(';')) com.split(";", -1).head
        else com
      }
      case _ => throw new IllegalArgumentException(com + " isn't a C_COMMAND, command type : " + commandType(com))
    }
  }

  /**
    * Returns the jump mnemonic in the current C-command (8 possibilities)
    * Should be called only when commandType() is C_COMMAND
    */
  def jump(currentCommand: String) : String = {
    val com = removeSpaceAndComment(currentCommand)
    commandType(com) match {
      case "C_COMMAND" => {
        if(com.contains(';')) com.split(";", -1).last
        else "" // no jump mnemonic
      }
      case _ => throw new IllegalArgumentException(com + " isn't a C_COMMAND, command type : " + commandType(com))
    }
  }
}
