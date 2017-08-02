package virtual_machine

/**
  * Created by silve on 07/03/2017.
  */
object Util {

  def removeCommentAndTrim(command: String) : String = {
    command.split("//", -1).head.trim
  }

  def removeSpaceAndComment(command: String) : String = {
    command.filterNot(_ == ' ').split("//", -1).head
  }

}
