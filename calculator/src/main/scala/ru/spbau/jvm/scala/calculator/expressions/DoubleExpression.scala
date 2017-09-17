package ru.spbau.jvm.scala.calculator.expressions


class DoubleExpression(val value: Double) extends Expression {
  override def name = value.toString

  override def precedence = Int.MaxValue

  override def associativity = Associativity.NONE
}
