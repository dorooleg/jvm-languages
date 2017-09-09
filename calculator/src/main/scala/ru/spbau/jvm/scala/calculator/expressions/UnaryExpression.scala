package ru.spbau.jvm.scala.calculator.expressions

class UnaryExpression(operationName: String, operationPrecedence: Int, f: Double => Double) extends Expression{
  override def name(): String = operationName

  override def precedence(): Int = operationPrecedence

  override def associativity(): Associativity.Value = Associativity.RIGHT

  def evaluate(value: DoubleExpression): DoubleExpression = new DoubleExpression(f(value.value))
}
