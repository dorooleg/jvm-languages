package ru.spbau.jvm.scala.calculator.expressions


class DoubleExpression(val value: Double) extends Expression {
  override def name(): String = value.toString

  override def precedence(): Int = Int.MaxValue

  override def associativity(): Associativity.Value = Associativity.NONE
}
