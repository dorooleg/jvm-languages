package ru.spbau.jvm.scala.calculator.expressions

class RightBracket(bracketName: String, backwardBracket: String) extends Expression {
  override def name(): String = bracketName

  override def precedence(): Int = Int.MaxValue

  override def associativity(): Associativity.Value = Associativity.NONE

  def isBackwardBracket(bracket: LeftBracket): Boolean = bracket.name().equals(backwardBracket)
}
