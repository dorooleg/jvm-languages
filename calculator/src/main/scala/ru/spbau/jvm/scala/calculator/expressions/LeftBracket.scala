package ru.spbau.jvm.scala.calculator.expressions

case class LeftBracket(bracketName: String, backwardBracket: String) extends Expression {
  override def name = bracketName

  override def precedence = Int.MaxValue

  override def associativity = Associativity.NONE

  def isBackwardBracket(bracket: RightBracket): Boolean = bracket.name.equals(backwardBracket)
}
