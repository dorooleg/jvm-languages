package ru.spbau.jvm.scala.calculator.expressions

case class RightBracket(bracketName: String, backwardBracket: String) extends Expression {
  override def name = bracketName

  override def precedence = Int.MaxValue

  override def associativity = Associativity.NONE

  def isBackwardBracket(bracket: LeftBracket): Boolean = bracket.name.equals(backwardBracket)
}
